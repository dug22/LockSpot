package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.PolybiusSquare;

public class PolybiusCipher extends AbstractCipher {

    private final char[][] polybiusSquare = PolybiusSquare.createPolybiusSquare();

    @Override
    public String name() {
        return "Polybius Cipher";
    }

    @Override
    public int id() {
        return 12;
    }

    @Override
    public String cipherType(){
        return CipherType.FRACTIONATING.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        int size = 5;
        int plaintextLength = plaintext.length();
        char[] plaintextCharacter = plaintext.toCharArray();
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintextLength; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (plaintextCharacter[i] == 'J') {
                        ciphertext.append("24");
                        break;
                    }


                    if (polybiusSquare[j][k] == plaintextCharacter[i]) {
                        ciphertext.append(j + 1).append(k + 1);
                        break;
                    }
                }
            }
        }

        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        char[] ciphertextCharacters = ciphertext.toCharArray();
        int ciphertextLength = ciphertext.length();
        for (int i = 0; i < ciphertextLength; i = i + 2) {
            int row = Integer.parseInt(String.valueOf(ciphertextCharacters[i]));
            int col = Integer.parseInt(String.valueOf(ciphertextCharacters[i + 1]));
            plaintext.append(polybiusSquare[row - 1][col - 1]);
        }

        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        return "";
    }
}
