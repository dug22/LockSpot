import pickle
import pandas as pd
import re
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder
from collections import Counter
import os

ALNUM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

def has_double_letters_or_numbers(text):
    for i in range(1, len(text)):
        if text[i] == text[i - 1]:
            return 1
    return 0

def get_frequency_vector_columns():
    return [f"Frequency_{c}" for c in ALNUM_CHARACTERS]

def get_ioc(text):
    text_length = len(text)
    character_frequency_dict = {}
    for c in text:
        if c.isalpha():
            character_frequency_dict[c] = character_frequency_dict.get(c, 0) + 1
    numerator = sum(f * (f - 1) for f in character_frequency_dict.values())
    denominator = text_length * (text_length - 1) if text_length > 1 else 1
    return numerator / denominator

def process_user_input(text):
    clean_text = re.sub(r"[^A-Z0-9]", "", text.upper())
    text_have_letter_j = int("J" in clean_text)
    text_contain_digits = int(any(c.isdigit() for c in clean_text))
    text_length = len(clean_text)
    ioc = get_ioc(clean_text)
    frequency_counts = Counter(clean_text)
    frequency_vectors = [frequency_counts.get(c, 0) / text_length for c in ALNUM_CHARACTERS]
    contains_double_letters_or_numbers = has_double_letters_or_numbers(clean_text)
    columns = ["Index of Coincidence", "Has Letter J?", "Ciphertext Contain Digits?", "Has Double Letters or Numbers?"] + get_frequency_vector_columns()
    return pd.DataFrame([[ioc, text_have_letter_j, text_contain_digits, contains_double_letters_or_numbers] + frequency_vectors], columns=columns)

def fill_in_circle(prob, total=10):
    filled = round(prob * total)
    return "●" * filled + "○" * (total - filled)

def predict_cipher_algorithm_used(model, encoder, user_text):
    features = process_user_input(user_text)
    probs = model.predict_proba(features)[0]
    labels = encoder.inverse_transform(range(len(probs)))
    print("\n🔓 LockSpot Cipher Algorithm Confidence Report\n")
    for label, prob in sorted(zip(labels, probs), key=lambda x: x[1], reverse=True):
        print(f"{label:<25} {fill_in_circle(prob)} {prob*100:5.1f}%")
    print("\n")

def load_model_from_user():
    while True:
        model_path = input("Enter RandomForest model path: ").strip()
        if os.path.exists(model_path):
            with open(model_path, "rb") as f:
                model = pickle.load(f)
            break
        print("File not found. Try again.")
    while True:
        encoder_path = input("Enter LabelEncoder path: ").strip()
        if os.path.exists(encoder_path):
            with open(encoder_path, "rb") as f:
                encoder = pickle.load(f)
            break
        print("File not found. Try again.")
    return model, encoder

def main():
    print("Welcome to LockSpot CLI!")
    print("You are currently using the: LockSpot Random Forest 50k Model Script")
    model, encoder = load_model_from_user()
    
    print("Type ciphertext to classify it. Type 'exit' to quit.\n")
    while True:
        user_input = input("Ciphertext> ").strip()
        if user_input.lower() in ("exit", "quit"):
            print("Exiting LockSpot. Goodbye!")
            break
        if user_input:
            predict_cipher_algorithm_used(model, encoder, user_input)


if __name__ == "__main__":
    main()
