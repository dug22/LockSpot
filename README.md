<p align="center">
<img width="341" height="327" alt="image" align="center" src="https://github.com/user-attachments/assets/b6a07b2f-426a-4c28-a20f-d879e85170ff" />
</p>

<div align="center">

[![License](https://img.shields.io/badge/license-MIT-green)](./LICENSE) [![Stars](https://img.shields.io/github/stars/dug22/LockSpot.svg)](https://github.com/dug22/LockSpot/stargazers)  [![Downloads](https://img.shields.io/github/downloads/dug22/LockSpot/total.svg)](https://github.com/dug22/LockSpot/releases)  [![Python](https://img.shields.io/badge/python-3.11-blue)](#)  [![Java](https://img.shields.io/badge/java-23-red)](#)
</div>



<p align="center">
  <a href="https://github.com/dug22/LockSpot/issues">
    <strong style="font-size: 1.5em; text-decoration: underline;">Create An Issue</strong>
  </a>
  &nbsp;&nbsp;&nbsp;
  <a href="https://github.com/dug22/LockSpot/pulls">
    <strong style="font-size: 1.5em; text-decoration: underline;">Create A Pull Request</strong>
  </a>
</p>

## Overview
LockSpot is a machine learning tool that allows users to input ciphertext and receive a
conducted analysis of the cipher algorithm most likely used to encrypt it, along with the
cipher category it most likely belongs to (such as monoalphabetic substitution, polyalphabetic
substitution, transposition, and more).

This tool is designed for academic researches, and cryptography enthusiasts.

## LockSpot Features
- Predicts cipher algorithm from ciphertext.
- Determines cipher type (substitution, transposition, polyalphabetic, etc.).
- Supports multiple classical cipher algorithms.
- Provides confidence scores for predictions.
- Easy-to-use CLI interface.
- All models and scripts included in releases.

## Releases
Releases contain the appropriate pre-trained models and the scripts necessary to run the tool. Latest releases of LockSpot
can be found **[HERE](https://github.com/dug22/LockSpot/releases)** . Note! All future releases will be distributed in two separate
ZIP files, labeled "LARGE" and "SMALL", meaning the "LARGE" zip release will contain all large models, while the "SMALL" 
zip release will contain all small models, along with a main client script to interact with our tool.

## Getting Started

### Prerequisites
- Ensure you have Python 3.11 installed.

### Installation & Quick Start
The installation process is quite simple and easy. If you wish to easily get a hold of all of LockSpot's latest models and CLI script,
you can simply do the following:
1. Simply copy and paste the given script down below into a given .sh file. This script is compatible with both Windows and Linux.
   <details>
   <summary>Click To Expand</summary>

   ```
       #!/usr/bin/env bash
       # ======================================
       # LockSpot Auto-Downloader (Cross-Platform)
       # Works on Linux, Mac, and Windows (WSL or Git Bash)
       # ======================================
       
       echo "Detecting OS..."
       OS="$(uname -s)"
       echo "Detected OS: $OS"

       GITHUB_API="https://api.github.com/repos/dug22/LockSpot/releases/latest"
       
       # Create output directory
       OUT_DIR="LockSpot_Model"
       mkdir -p "$OUT_DIR"
       
       if [[ "$OS" == "Linux" || "$OS" == "Darwin" ]]; then
           echo "Using Linux/Mac downloader..."
           ZIP_URLS=$(curl -s "$GITHUB_API" | grep "browser_download_url" | grep ".zip" | grep -v "source" | cut -d '"' -f 4)
       
           if [ -z "$ZIP_URLS" ]; then
               echo "No release ZIPs found."
               exit 1
           fi
       
           for URL in $ZIP_URLS; do
               FILE_NAME=$(basename "$URL")
               echo "Downloading $FILE_NAME..."
               curl -L -o "$FILE_NAME" "$URL"
               echo "Extracting $FILE_NAME..."
               unzip -o "$FILE_NAME" -d "$OUT_DIR"
           done
       
       elif [[ "$OS" == "MINGW"* || "$OS" == "CYGWIN"* || "$OS" == "MSYS"* ]]; then
           echo "Using Windows downloader via PowerShell..."
           powershell -Command "
           \$release = Invoke-RestMethod -Uri '$GITHUB_API';
           \$zips = \$release.assets | Where-Object { \$_.name -like '*.zip' -and \$_.name -notlike '*source*' };
           \$dir = '$OUT_DIR';
           New-Item -ItemType Directory -Force -Path \$dir | Out-Null;
           foreach (\$zip in \$zips) {
               \$url = \$zip.browser_download_url;
               \$file = Split-Path \$url -Leaf;
               Write-Host 'Downloading ' \$file;
               Invoke-WebRequest -Uri \$url -OutFile \$file;
               Write-Host 'Extracting ' \$file;
               Expand-Archive -Path \$file -DestinationPath \$dir -Force;
           }
           Write-Host 'All downloads and extractions complete. Files are in .\$dir';
           pause
           "
       else
           echo "Unsupported OS: $OS"
           exit 1
       fi
       
       echo "Done."
   ```
   </details>
2. Save the given shell file.
3. If you're on Windows use the following command ```sh path/to/lockspot_autodownloader.sh```. If you're on Linux, use the following
   command ```bash path/to/lockspot_autodownloader.sh```


If you don’t want to download every model, simply go to the release of your choice.  We provide simple instructions on how to
download the appropriate .zip file using the curl and wget commands.

Once you’ve installed your chosen models, unzip the corresponding file and run the main script included with each model.
For example, to use LockSpot’s SMALL models, enter the following command:

```python /path/to/LockSpot_Client_Script.py```

After running the command, you will be prompted to provide the path to the given pickle/h5 files necessary, and after that, provide the given ciphertext, and our model
will output an overall analysis of its predictions. It's as simple as that. Please refer to the video down below, as a reference:

<div align="center">
  <video src="https://github.com/user-attachments/assets/819b7e55-1c52-4d5e-b3ef-6dd5b7c6bedb" controls></video>
</div>

## **License**
LockSpot is released under the [MIT License](https://github.com/dug22/LockSpot/blob/master/LICENSE)
```
MIT License

Copyright (c) 2026 dug22

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associateddocumentation
files (the "Software"),to deal in the Software without restriction,
including without limitationthe rights to use, copy, modify, merge,
publish, distribute, sublicense,and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

## **Contributions**
Contributions are welcome! If you have suggestions, bug fixes, or enhancements, please open an issue to share them.