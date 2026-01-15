package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;
import io.github.dug22.lockspot.cipheralgorithms.PolybiusSquare;

import java.util.stream.IntStream;

public class PlayfairCipher extends AbstractCipher {


    @Override
    public String name() {
        return "Playfair Cipher";
    }

    @Override
    public String cipherType(){
        return CipherType.POLYGRAPHIC_SUBSTITUTION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = getPair(plaintext);
        return encryptOrDecrypt(plaintext, key, true);
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = getPair(ciphertext);
        return encryptOrDecrypt(ciphertext, key, false);
    }

    @Override
    public String generateRandomKey() {
        return CipherUtils.getRandomKeyFromFile();
    }


    private String getPair(String text) {
        text = upperCase(text).replaceAll("[^A-Z]", "");
        StringBuilder pair = new StringBuilder();
        char primaryFiller = 'X';
        char secondaryFiller = 'Z';
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            char firstCharacter = text.charAt(i);
            char secondCharacter;

            if (i + 1 < textLength) {
                secondCharacter = text.charAt(i + 1);
            } else {
                secondCharacter = primaryFiller;
            }

            if (firstCharacter != secondCharacter) {
                pair.append(firstCharacter);
                pair.append(secondCharacter);
                i++;
            } else {
                char filler = (firstCharacter == 'X') ? secondaryFiller : primaryFiller;
                pair.append(firstCharacter);
                pair.append(filler);
            }
        }

        return pair.toString();
    }


    private String encryptOrDecrypt(String pair, String key, boolean isEncrypt) {
        char[][] polybiusSquare = PolybiusSquare.createPolybiusSquareWithKey(key);
        int firstRow = 0, firstColumn = 0, secondRow = 0, secondColumn = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i + 1 < pair.length(); i += 2) {
            boolean foundX = false;
            boolean foundY = false;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (pair.charAt(i) == polybiusSquare[j][k]) {
                        firstRow = j;
                        firstColumn = k;
                        foundX = true;
                    }
                    if (pair.charAt(i + 1) == polybiusSquare[j][k]) {
                        secondRow = j;
                        secondColumn = k;
                        foundY = true;
                    }
                }
            }

            if (foundX && foundY) {
                int shift = isEncrypt ? 1 : 4;
                if (firstRow == secondRow) {
                    result.append(polybiusSquare[firstRow][(firstColumn + shift) % 5]);
                    result.append(polybiusSquare[secondRow][(secondColumn + shift) % 5]);
                } else if (firstColumn == secondColumn) {
                    result.append(polybiusSquare[(firstRow + shift) % 5][firstColumn]);
                    result.append(polybiusSquare[(secondRow + shift) % 5][secondColumn]);
                } else {
                    result.append(polybiusSquare[firstRow][secondColumn]);
                    result.append(polybiusSquare[secondRow][firstColumn]);
                }
            }
        }
        return result.toString();
    }
}