package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;

public class AffineCipher extends AbstractCipher {

    private final String alphabet = getAlphabet();
    private final int[] possibleCoprimes = new int[]{1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
    private final int alphabetLength = alphabet.length();

    @Override
    public String name() {
        return "Affine Cipher";
    }

    @Override
    public int id() {
        return 2;
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        int a = Integer.parseInt(key.split(" ")[0]);
        int b = Integer.parseInt(key.split(" ")[1]);
        StringBuilder ciphertext = new StringBuilder();
        int plaintextLength = plaintext.length();
        for (int i = 0; i < plaintextLength; i++) {
            char character = plaintext.charAt(i);
            if (!alphabet.contains(String.valueOf(character))) {
                ciphertext.append(character);
            } else {
                ciphertext.append((char) ((((a * (character - 'A')) + b) % alphabetLength) + 'A'));
            }
        }
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        int a = Integer.parseInt(key.split(" ")[0]);
        int b = Integer.parseInt(key.split(" ")[1]);
        int aInverse = modInverse(a, alphabetLength);
        StringBuilder plaintext = new StringBuilder();
        int ciphertextLength = ciphertext.length();
        for (int i = 0; i < ciphertextLength; i++) {
            char character = ciphertext.charAt(i);

            if (!alphabet.contains(String.valueOf(character))) {
                plaintext.append(character);
            } else {
                plaintext.append((char) ((aInverse * (character - 'A' - b + alphabetLength) % alphabetLength) + 'A'));
            }
        }
        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        StringBuilder key = new StringBuilder();
        int randomIndex = (int) (Math.random() * possibleCoprimes.length);
        key.append(possibleCoprimes[randomIndex]).append(" ");
        key.append((int) (Math.random() * (alphabetLength - 1)));
        return key.toString();
    }

    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x <= m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }
}
