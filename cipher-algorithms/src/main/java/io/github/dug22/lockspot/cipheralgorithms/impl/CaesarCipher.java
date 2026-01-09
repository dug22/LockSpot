package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;

public class CaesarCipher extends AbstractCipher {

    private final String alphabet = getAlphabet();

    @Override
    public String name() {
        return "Caesar Cipher";
    }

    @Override
    public int id() {
        return 9;
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        int shift = Integer.parseInt(key);
        StringBuilder ciphertext = new StringBuilder();
        for(int i = 0; i < plaintext.length(); i++){
            char character = plaintext.charAt(i);
            if(!alphabet.contains(String.valueOf(character))){
                ciphertext.append(character);
            }else{
                ciphertext.append((char) ((character - 'A' + shift) % 26 + 'A'));
            }
        }

        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        int shift = Integer.parseInt(key);
        StringBuilder plaintext = new StringBuilder();
        for(int i = 0; i < ciphertext.length(); i++){
            char character = ciphertext.charAt(i);
            if(!alphabet.contains(String.valueOf(character))){
                plaintext.append(character);
            }else{
                plaintext.append((char) ((character - 'A' - shift + 26) % 26 + 'A'));
            }
        }

        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        int low = 2;
        int high = 25;
        int key = (int) (Math.random() * (high - low) + low);
        return String.valueOf(key);
    }
}
