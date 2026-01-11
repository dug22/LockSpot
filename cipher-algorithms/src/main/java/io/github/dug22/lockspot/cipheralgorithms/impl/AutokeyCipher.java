package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

public class AutokeyCipher extends AbstractCipher {

    private final String alphabet = getAlphabet();
    private final int alphabetLength = alphabet.length();

    @Override
    public String name() {
        return "Autokey Cipher";
    }

    @Override
    public int id() {
        return 5;
    }

    @Override
    public String cipherType(){
        return CipherType.POLYALPHABETIC_SUBSTITUTION.name();
    }


    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        key = upperCase(key);
        StringBuilder ciphertext = new StringBuilder();
        StringBuilder keyStream = new StringBuilder(key);

        int currentKeyIndex = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char plaintextCharacter = plaintext.charAt(i);
            char keyCharacter = keyStream.charAt(currentKeyIndex);
            int plaintextIndex = alphabet.indexOf(plaintextCharacter);
            if (plaintextIndex == -1) {
                ciphertext.append(plaintextCharacter);
                continue;
            }
            int keyIndex = alphabet.indexOf(keyCharacter);
            int ciphertextIndex = (plaintextIndex + keyIndex + alphabetLength) % alphabetLength;
            char ciphertextCharacter = alphabet.charAt(ciphertextIndex);
            keyStream.append(plaintextCharacter);
            ciphertext.append(ciphertextCharacter);
            currentKeyIndex++;
        }


        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = upperCase(ciphertext);
        key = upperCase(key);
        StringBuilder plaintext = new StringBuilder();
        StringBuilder keyStream = new StringBuilder(key);
        int currentKeyIndex = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            char ciphertextCharacter = ciphertext.charAt(i);
            char keyCharacter = keyStream.charAt(currentKeyIndex);
            int ciphertextIndex = alphabet.indexOf(ciphertextCharacter);
            if (ciphertextIndex == -1) {
                plaintext.append(ciphertextCharacter);
                continue;
            }
            int keyIndex = alphabet.indexOf(keyCharacter);
            int plaintextIndex = (ciphertextIndex - keyIndex + alphabetLength) % alphabetLength;
            char plaintextCharacter = alphabet.charAt(plaintextIndex);
            keyStream.append(plaintextCharacter);
            plaintext.append(plaintextCharacter);
            currentKeyIndex++;
        }

        return plaintext.toString();
    }

    @Override
    public String generateRandomKey(){
        return CipherUtils.getRandomKeyFromFile();
    }
}
