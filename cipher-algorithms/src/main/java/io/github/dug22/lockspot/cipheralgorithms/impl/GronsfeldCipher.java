package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GronsfeldCipher extends AbstractCipher {

    private final String alphabet = getAlphabet();
    private final int alphabetLength = alphabet.length();

    @Override
    public String name() {
        return "Gronsfeld Cipher";
    }

    @Override
    public int id() {
        return 10;
    }

    @Override
    public String cipherType(){
        return CipherType.POLYALPHABETIC_SUBSTITUTION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        StringBuilder ciphertext = new StringBuilder();
        int plaintextLength = plaintext.length();
        int keyLength = key.length();
        for (int i = 0; i < plaintextLength; i++) {
            if (!alphabet.contains(String.valueOf(plaintext.charAt(i)))) {
                ciphertext.append(plaintext.charAt(i));
            } else {
                int cipherCharacterIndex =
                        (alphabet.indexOf(plaintext.charAt(i))
                         + (key.charAt(i % keyLength) - '0'))
                        % alphabetLength;
                ciphertext.append(alphabet.charAt(cipherCharacterIndex));
            }
        }
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();
        StringBuilder plaintext = new StringBuilder();
        int ciphertextLength = ciphertext.length();
        int keyLength = key.length();
        for (int i = 0; i < ciphertextLength; i++) {
            if (!alphabet.contains(String.valueOf(ciphertext.charAt(i)))) {
                plaintext.append(ciphertext.charAt(i));
            } else {
                int plainCharacterIndex =
                        (alphabet.indexOf(ciphertext.charAt(i))
                         - (key.charAt(i % keyLength) - '0') + alphabetLength)
                        % alphabetLength;
                plaintext.append(alphabet.charAt(plainCharacterIndex));
            }
        }
        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        int low = 3;
        int high = 10;
        int keyLength = new Random().nextInt(high - low) + low;
        List<Integer> keyList = new ArrayList<>();
        for (int i = 0; i < keyLength; i++) {
            keyList.add(i);
        }

        Collections.shuffle(keyList);
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            key.append(keyList.get(i));
        }

        return key.toString();
    }
}
