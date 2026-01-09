import nltk
from nltk.corpus import words
import random
nltk.download('words')
english_words = words.words()
filtered_words = set(w.upper() for w in english_words if len(w) >= 3 and len(w) <= 9)
filtered_words = list(filtered_words)
with open("random-english-keywords.txt", "w") as f:
    for word in filtered_words[:100000]:
        f.write(f"{word},\n")
print(f"Saved 100,000 words to random-english-keywords.txt")