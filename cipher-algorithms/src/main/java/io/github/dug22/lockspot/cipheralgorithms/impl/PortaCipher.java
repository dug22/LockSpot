package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

public class PortaCipher extends AbstractCipher {

    @Override
    public String name() {
        return "Porta Cipher";
    }

    @Override
    public int id() {
        return 13;
    }

    @Override
    public String cipherType(){
        return CipherType.POLYALPHABETIC_SUBSTITUTION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        return encryptOrDecrypt(plaintext, key);
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        return encryptOrDecrypt(ciphertext, key);
    }

    @Override
    public String generateRandomKey() {
        return CipherUtils.getRandomKeyFromFile();
    }

    private String encryptOrDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        text = text.toUpperCase();
        key = key.toUpperCase();

        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            char currentCharacter = text.charAt(i);
            char currentKeyCharacter = key.charAt(i % key.length());

            if (currentCharacter < 65 || currentCharacter > 90) {
                result.append(currentCharacter);

            } else {

                int characterNumber = currentCharacter - 65;
                int keyNumber = (currentKeyCharacter - 65) / 2;

                if (currentCharacter >= 'N') {
                    currentCharacter = (char) ('A' + (characterNumber - keyNumber + 13) % 13);
                } else {
                    currentCharacter = (char) ( 'N' + (characterNumber + keyNumber) % 13);
                }

                result.append(currentCharacter);
            }
        }

        return result.toString();
    }
}
