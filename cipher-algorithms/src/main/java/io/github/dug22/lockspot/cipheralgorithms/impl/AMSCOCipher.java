package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;

import java.util.*;
import java.util.stream.IntStream;

public class AMSCOCipher extends AbstractCipher {

    @Override
    public String name() {
        return "AMSCO Cipher";
    }

    @Override
    public String cipherType(){
        return CipherType.TRANSPOSITION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {

        plaintext = plaintext.toUpperCase();
        int keyLength = key.length();

        Integer[] columnOrder = IntStream.range(0, keyLength).boxed()
                .sorted(Comparator.comparingInt(key::charAt))
                .toArray(Integer[]::new);

        StringBuilder[] columns = IntStream.range(0, keyLength)
                .mapToObj(_ -> new StringBuilder())
                .toArray(StringBuilder[]::new);

        int textIndex = 0;
        int chunkSize = 1;
        int columnIndex = 0;

        while (textIndex < plaintext.length()) {
            int charsToTake = Math.min(chunkSize, plaintext.length() - textIndex);
            columns[columnIndex].append(
                    plaintext, textIndex, textIndex + charsToTake
            );
            textIndex += charsToTake;

            chunkSize = chunkSize == 1 ? 2 : 1;
            columnIndex = (columnIndex + 1) % keyLength;
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int col : columnOrder) {
            ciphertext.append(columns[col]);
        }

        return ciphertext.toString();
    }


    @Override
    public String decrypt(String ciphertext, String key) {

        int keyLength = key.length();
        int[] columnSizes = searchColumnIndex(ciphertext, key);

        Integer[] columnOrder = IntStream.range(0, keyLength).boxed()
                .sorted(Comparator.comparingInt(key::charAt))
                .toArray(Integer[]::new);

        StringBuilder[] columns = new StringBuilder[keyLength];
        int cipherIndex = 0;

        for (int i = 0; i < keyLength; i++) {
            int column = columnOrder[i];
            columns[column] = new StringBuilder(ciphertext.substring(cipherIndex, cipherIndex + columnSizes[column]));
            cipherIndex += columnSizes[column];
        }

        StringBuilder plaintext = new StringBuilder();
        int chunkSize = 1;

        while (true) {
            boolean extracted = false;

            for (int column = 0; column < keyLength; column++) {
                if (columns[column].length() >= chunkSize) {
                    plaintext.append(columns[column], 0, chunkSize);
                    columns[column].delete(0, chunkSize);
                    extracted = true;
                }
                chunkSize = chunkSize == 1 ? 2 : 1;
            }

            if (!extracted) break;
        }

        return plaintext.toString();
    }


    @Override
    public String generateRandomKey() {
        String keyCombination = "0123456789";
        List<String> digits = Arrays.asList(keyCombination.split(""));
        Collections.shuffle(digits);
        Random rand = new Random();
        int keyLength = 5 + rand.nextInt(6);
        StringBuilder resultingKeyCombination = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            resultingKeyCombination.append(digits.get(i));
        }
        return resultingKeyCombination.toString();
    }

    private int[] searchColumnIndex(String text, String key) {
        int keyLength = key.length();
        int remainingTextLength = text.length();
        int[] columnSizes = new int[keyLength];
        int currentColumnIndex = 0;
        int chunkSize = 1;
        while (remainingTextLength > 0) {
            columnSizes[currentColumnIndex] += chunkSize;
            remainingTextLength -= chunkSize;

            currentColumnIndex = (currentColumnIndex + 1) % keyLength;

            if (chunkSize == 1) {
                chunkSize = 2;
            } else {
                chunkSize = 1;
            }

            if (remainingTextLength == 1 && chunkSize == 2) {
                chunkSize = 1;
            }
        }

        return columnSizes;
    }
}
