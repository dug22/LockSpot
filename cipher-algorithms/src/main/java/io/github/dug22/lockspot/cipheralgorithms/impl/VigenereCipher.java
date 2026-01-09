package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

public class VigenereCipher extends AbstractCipher {

    @Override
    public String name() {
        return "Vigenère Cipher";
    }

    @Override
    public int id() {
        return 15;
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        key = upperCase(key);
        int plaintextLength = plaintext.length();
        int keyLength = key.length();
        StringBuilder ciphertext = new StringBuilder();
        int unknownCharacters = 0;
        for (int i = 0; i < plaintextLength; i++) {
            char plaintextCharacter = plaintext.charAt(i);
            if (!getAlphabet().contains(String.valueOf(plaintextCharacter))) {
                ciphertext.append(plaintextCharacter);
                unknownCharacters++;
            } else {
                int keyIndex = (i - unknownCharacters) % keyLength;
                int normalizedPlain = plaintextCharacter - 'A';
                int normalizedKey = key.charAt(keyIndex) - 'A';
                char cipheredCharacter = (char) ('A' + (normalizedPlain + normalizedKey) % 26);
                ciphertext.append(cipheredCharacter);
            }
        }

        return ciphertext.toString();
    }


    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = upperCase(ciphertext);
        key = upperCase(key);
        int ciphertextLength = ciphertext.length();
        int keyLength = key.length();
        StringBuilder plaintext = new StringBuilder();
        int unknownCharacters = 0;
        for (int i = 0; i < ciphertextLength; i++) {
            char ciphertextCharacter = ciphertext.charAt(i);
            if (!getAlphabet().contains(String.valueOf(ciphertextCharacter))) {
                plaintext.append(ciphertextCharacter);
                unknownCharacters++;
            } else {
                int keyIndex = (i - unknownCharacters) % keyLength;
                int normalizedCipher = ciphertextCharacter - 'A';
                int normalizedKey = key.charAt(keyIndex) - 'A';
                char plaintextCharacter = (char) ('A' + (normalizedCipher - normalizedKey + 26) % 26);
                plaintext.append(plaintextCharacter);
            }
        }

        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        return CipherUtils.getRandomKeyFromFile();
    }
}
