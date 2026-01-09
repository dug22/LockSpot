## **What is LockSpot?**

<div style="text-align: justify">
LockSpot is a machine learning tool that allows users to input ciphertext and receive a conducted analysis of what cipher algorithm was
most likely used to encrypt it. LockSpot was trained to identify 15 classical cipher algorithms:
<ol>
    <li>ADFGVX Cipher</li>
    <li>Affine Cipher</li>
    <li>AMSCO Cipher</li>
    <li> Atbash Cipher</li>
    <li> Autokey Cipher</li>
    <li> Baconian Cipher</li>
    <li> Beaufort Cipher</li>
    <li> Bifid Cipher</li>
    <li> Caesar Cipher</li>
    <li> Gronsfeld Cipher</li>
    <li> Playfair Cipher</li>
    <li> Polybius Cipher</li>
    <li> Porta Cipher</li>
    <li> Railfence Cipher</li>
    <li> Vigenère Cipher</li>
</ol>
</div>

## **Dataset Collection**

<div style="text-align: justify;">
The AllenAI/c4 dataset was used to train LockSpot’s model. The dataset provided enough text data as a base to incorporate the following columns:

<ol>
<li>Ciphertext</li> 
<li>Decrypted Plaintext</li>
<li> Key</li>
<li> Cipher Algorithm Name/ID</li>
<li> Index of Coincidence [IF]</li> 
<li> Has the Letter J? [IF]</li> 
<li> Contains Letters and Digits? [IF]</li> 
<li> Contains Digits? [IF]</li> 
<li> Contains Double Letters or Numbers? [IF]</li> 
<li> Character Frequency Probabilities (A-Z 0-9) [IF] </li> 
</ol>
Some of these columns of data were provided as input features for our model to learn from to classify which cipher
algorithm was most likely used to encrypt the inputted ciphertext. Columns with an "[IF]" label indicate that their data were used as 
input features for our model.
</div>

## **Model Results**
<div style="text-align: justify;">
LockSpot uses a RandomForestClassifier trained on 50,000 records of ciphertext, representing tens of thousands of pages of encrypted text. 
In future work, additional model architectures will be explored to further improve classification performance.The following section below
presents the model’s performance metrics.

| Model Name          | Accuracy (%) | 
|---------------------| --- | 
| Random_Forest_Classifier_50k | 86.03 |
</div>

## **How To Use These Models?**
If you wish to use these models in production, locate the 'models' directory within LockSpot's GitHub repository and find the model type you wish
to use, in this case, 'random forest classifier 50k'. This directory will contain the following directories
    - pickle or whatever format these models were saved in
    - scripts

Download the appropriate files from the repository type the following command 
````
- !python /path/to/script/lockspot_rf_50k_model_script.py
````

You will be prompted to provide the path to the given pickle files necessary and after that provide the given ciphertext and our model
will conduct an overall analysis of its predictions. It's as simple as that. When newer models are released you will see other directories
in the model's directory.

## **License**

<div style="text-align: justify;">

MIT License

Copyright (c) 2026 dug22

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
</div>

## **Contributions**
If you'd like to contribute to this repository, feel free to open a pull request with your suggestions, bug fixes, or enhancements. Contributions are always welcome!







