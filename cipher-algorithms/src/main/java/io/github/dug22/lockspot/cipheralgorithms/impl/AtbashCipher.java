package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;

public class AtbashCipher extends AbstractCipher {

    @Override
    public String name() {
        return "Atbash Cipher";
    }

    @Override
    public String cipherType(){
        return CipherType.MONOALPHABETIC_SUBSTITUTION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for(char character : plaintext.toCharArray()){
            if(character >= 'A' && character <= 'Z'){
                ciphertext.append((char) ('A' + 'Z' - character));
            }else{
                ciphertext.append(character);
            }
        }
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        return encrypt(ciphertext, key);
    }

    @Override
    public String generateRandomKey() {
        return "";
    }
}
