import pandas as pd
from tqdm import tqdm
from datasets import load_dataset
from collections import Counter

MAX_SAMPLES = 50_000
processed = 0
text_collection = []
dataset = load_dataset("allenai/c4", name='en', split="train", streaming=True)
for sample in tqdm(dataset, unit=" samples", leave=False):
  if processed > MAX_SAMPLES:
    break
  text_collection.append(sample.get('text'))
  processed += 1
new_text_collection = []
for txt in text_collection:
  txt = txt.upper()
  new_text_collection.append(txt)
df = pd.DataFrame({"Original Text" : new_text_collection})
df["Original Text"] = (
    df["Original Text"]
    .str.replace(r"[\r\n]+", " ", regex=True) 
    .str.replace(r"[^A-Za-z0-9]", "", regex=True)
    .str.strip()
)
df.to_csv("text-dataset.csv", sep=",", index=False)
