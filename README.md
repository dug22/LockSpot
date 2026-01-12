<p align="center">
<img width="341" height="327" alt="image" align="center" src="https://github.com/user-attachments/assets/b6a07b2f-426a-4c28-a20f-d879e85170ff" />
</p>

<div align="center">

[![License](https://img.shields.io/badge/license-MIT-green)](./LICENSE) [![Stars](https://img.shields.io/github/stars/dug22/LockSpot.svg)](https://github.com/dug22/LockSpot/stargazers)  [![Downloads](https://img.shields.io/github/downloads/dug22/LockSpot/total.svg)](https://github.com/dug22/LockSpot/releases)  [![Python](https://img.shields.io/badge/python-3.11-blue)](#)  [![Java](https://img.shields.io/badge/java-23-red)](#)
</div>

## **What is LockSpot?**

<div style="text-align: justify">
LockSpot is a machine learning tool that allows users to input ciphertext and receive a conducted analysis of the cipher algorithm most likely used to encrypt it, along with the cipher category it most likely belongs to (such as monoalphabetic substitution, polyalphabetic substitution, transposition, and more).
<p></p>
LockSpot was trained to identify 15 classical cipher algorithms:
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

In future works, LockSpot will be trained to identify more cipher algorithms.
</div>

## **Dataset Collection**

<div style="text-align: justify;">
The AllenAI/c4 dataset was used to train LockSpot’s model. The dataset provided enough text data as a base to incorporate the following columns:

<ol>
<li>Ciphertext</li> 
<li>Decrypted Plaintext</li>
<li> Key</li>
<li> Cipher Algorithm Name/ID/Type</li>
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
In future work, additional model architectures will be explored to further improve classification performance. The following section below
presents the model’s performance metrics.
</div>

<br>

| Model Name          | Accuracy (%) | 
|---------------------|--------------| 
| LockSpot_RandomForest_SMALL_50k-ciphertext | 93.26        |

## **Model Types**
LockSpot follows a specific nomenclature for its specific models.

### **SMALL**
If you see models with the word SMALL in their name, this indicates the model was trained on the 15 initial cipher algorithms, which are: 

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

### **LARGE**
Even though this hasn't been implemented yet, models with the word LARGE in their name indicate the model was trained on the initial 15 cipher algorithms, along with 15 others
which are:

<ol>
    <li>ADFGVX Cipher</li>
    <li>Affine Cipher</li>
    <li>Alberti Cipher</li>
    <li>AMSCO Cipher</li>
    <li>Atbash Cipher</li>
    <li>Autokey Cipher</li>
    <li>Baconian Cipher</li>
    <li>Bazeries</li>
    <li>Beaufort Cipher</li>
    <li>Bifid Cipher</li>
    <li>Cadenus</li>
    <li>Caesar Cipher</li>
    <li>Columnar Cipher</li>
    <li>Grandpré Cipher</li>
    <li>Gronsfeld Cipher</li>
    <li>Hill Cipher</li>
    <li>Myszkowski Cipher</li>
    <li>Nihilist Substitution Cipher</li>
    <li>Nihilist Transposition Cipher</li>
    <li>Playfair Cipher</li>
    <li>Polybius Cipher</li>
    <li>Porta Cipher</li>
    <li>Railfence Cipher</li>
    <li>ROT13</li>
    <li>Route Cipher</li>
    <li>Running Key Cipher</li>
    <li>Trifid Cipher</li>
    <li>Trithemius Cipher</li>
    <li>Two Square Cipher</li>
    <li>Vigenère Cipher</li>
</ol>


## **How To Use These Models?**
If you wish to use these models in production, locate the 'models' directory within LockSpot's GitHub repository and find the model type you wish
to use, in this case, 'random forest classifier'. This directory will contain the following directories

<ul>
    <li>pickle/h5 (check the releases.md file to download the appropriate pickle/h5 files as these are how are our machine learning models are saved)</li>
    <li>scripts</li>
    <li>notebooks</li>
</ul>

Download the appropriate files from the appropriate model directory and type the following command to run the script.
````
- python /path/to/script/LockSpot_RandomForest_script.py
````

You will be prompted to provide the path to the given pickle/h5 files necessary, and after that, provide the given ciphertext, and our model
will output an overall analysis of its predictions. It's as simple as that. When newer models are released, you will see other directories
in the given models directory.

<div align="center">
  <video src="https://github.com/user-attachments/assets/819b7e55-1c52-4d5e-b3ef-6dd5b7c6bedb" controls></video>
</div>

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







