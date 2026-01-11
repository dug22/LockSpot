import pickle
import pandas as pd
import re
from sklearn.ensemble import RandomForestClassifier
from collections import Counter
import os

ALNUM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

def has_double_letters_or_numbers(text):
  for i in range(len(text) - 1):
    if text[i] == text[i+1]:
      return 1
  return 0

def get_ioc(text):
  text_length = len(text)
  character_frequency_dict = {}
  for c in text:
    if c.isalpha():
      if c in character_frequency_dict:
        character_frequency_dict[c] += 1
      else:
        character_frequency_dict[c] = 1
  numerator = 0
  for frequency in character_frequency_dict.values():
    numerator += frequency * (frequency - 1)
  denominator = text_length *(text_length - 1)
  return numerator/denominator


def process_user_input(text):
    clean_text = text.upper()
    clean_text = re.sub(r"[^A-Z0-9]", "", clean_text)

    text_have_letter_j = int("J" in clean_text)
    text_contain_digits = int(any(c.isdigit() for c in clean_text))
    contains_double_letters_or_numbers = int(has_double_letters_or_numbers(clean_text))

    text_length = len(clean_text)
    ioc = get_ioc(clean_text) if text_length > 0 else 0.0

    frequency_counts = Counter(clean_text)

    frequency_vectors = [
        frequency_counts.get(c, 0) / text_length if text_length > 0 else 0.0
        for c in ALNUM_CHARACTERS
    ]


    values = (
        [ioc, text_have_letter_j, text_contain_digits, contains_double_letters_or_numbers]
        + frequency_vectors
    )

    return pd.DataFrame([values], columns=features)

def fill_in_circle(prob, total=10):
    filled = round(prob * total)
    return "●" * filled + "○" * (total - filled)


def predict_cipher_algorithm_used(model, user_input_text):
    features = process_user_input(user_input_text)
    predicted_probabilities_for_each_category = model.predict_proba(features)
    class_labels_for_each_category = model.classes_

    print("\n🔓 LockSpot Cipher Type & Algorithm Confidence Report\n")
    print("=" * 60)

    def print_predictions_for_category(category_name, category_labels, category_probabilities):
        labels_with_confidence = [
            (label, confidence)
            for label, confidence in zip(category_labels, category_probabilities)
            if confidence > 0
        ]

        labels_with_confidence.sort(key=lambda item: item[1], reverse=True)

        print(f"{category_name}:")
        for label, confidence in labels_with_confidence:
            confidence_percentage = confidence * 100
            print(f"  {label:<25} {fill_in_circle(confidence)} {confidence_percentage:5.1f}%")
        print("-" * 60)

    predicted_probabilities_for_cipher_type = predicted_probabilities_for_each_category[0][0]
    class_labels_for_cipher_type = class_labels_for_each_category[0]
    print_predictions_for_category(
        "Cipher Type",
        class_labels_for_cipher_type,
        predicted_probabilities_for_cipher_type
    )

    predicted_probabilities_for_cipher_algorithm = predicted_probabilities_for_each_category[1][0]
    class_labels_for_cipher_algorithm = class_labels_for_each_category[1]
    print_predictions_for_category(
        "Cipher Algorithm",
        class_labels_for_cipher_algorithm,
        predicted_probabilities_for_cipher_algorithm
    )

def load_model_from_user():
    while True:
        model_path = input("Specify the path to the RandomForest model:").strip()
        if os.path.exists(model_path):
            with open(model_path, "rb") as f:
                model = pickle.load(f)
            break
        print("File not found. Try again.")
    return model

def main():
    print(r"""
 ██       ██████   ██████ ██   ██  ██████  ██████   ██████  ████████
 ██      ██    ██ ██      ██  ██  ██      ██   ██ ██    ██     ██
 ██      ██    ██ ██      █████    █████  ██████  ██    ██     ██
 ██      ██    ██ ██      ██  ██       ██ ██      ██    ██     ██
 ███████  ██████   ██████ ██   ██ ██████  ██       ██████      ██
    """)
    print("Welcome to The LockSpot Command Line Tool!")
    print()
    print("Keep a look out for updates on GitHub: https://github.com/dug22/LockSpot")
    print()
    print("You are currently using the: LockSpot Random Forest Model Script")
    print()
    model = load_model_from_user()
    print()
    print("Type the following ciphertext you wish to analyze!. Type 'exit' to quit.\n")
    while True:
        user_input = input("Ciphertext> ").strip()
        if user_input.lower() in ("exit", "quit"):
            print("Quitting LockSpot. Goodbye!")
            break
        if user_input:
            predict_cipher_algorithm_used(model, user_input)
if __name__ == "__main__":
    main()
