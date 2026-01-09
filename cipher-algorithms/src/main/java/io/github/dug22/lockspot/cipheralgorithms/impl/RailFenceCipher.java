package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

public class RailFenceCipher extends AbstractCipher {


    @Override
    public String name() {
        return "RailFence Cipher";
    }

    @Override
    public int id() {
        return 14;
    }

    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        String ciphertext;
        int keyAsInt = Integer.parseInt(key);
        int plaintextLength = plaintext.length();
        char[][] matrix = buildMatrix(keyAsInt, plaintextLength);
        int matrixLength = matrix.length;
        int rowIncrement = 1;
        for (int row = 0, col = 0; col < matrix[row].length; col++) {
            if (row + rowIncrement == matrixLength || row + rowIncrement == -1) {
                rowIncrement *= -1;
            }

            matrix[row][col] = plaintext.charAt(col);
            row += rowIncrement;
        }
        ciphertext = buildStringFromMatrix(matrix);
        return ciphertext;
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = upperCase(ciphertext);
        String plaintext;
        int ciphertextLength = ciphertext.length();
        int keyAsInt = Integer.parseInt(key);
        char[][] matrix = buildMatrix(keyAsInt, ciphertextLength);
        int matrixLength = matrix.length;
        int rowIncrement = 1;
        int textIndex = 0;
        for (int selectedRow = 0; selectedRow < matrixLength; selectedRow++) {
            for (int row = 0, col = 0; col < matrix[row].length; col++) {
                if (row + rowIncrement == matrixLength || row + rowIncrement == -1) {
                    rowIncrement *= -1;
                }

                if (row == selectedRow) {
                    matrix[row][col] = ciphertext.charAt(textIndex++);
                }

                row += rowIncrement;
            }
        }

        matrix = transposeMatrix(matrix);
        plaintext = buildStringFromMatrix(matrix);
        return plaintext;
    }

    @Override
    public String generateRandomKey(){
        int low = 3;
        int high = 10;
        return String.valueOf(CipherUtils.generateRandomKeyAsNumber(low, high));
    }

    private char[][] transposeMatrix(char[][] matrix) {
        char[][] result = buildMatrix(matrix[0].length, matrix.length);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                result[col][row] = matrix[row][col];
            }
        }

        return result;
    }

    private char[][] buildMatrix(int rows, int cols) {
        char[][] matrix = new char[rows][];
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = new char[cols];
        }
        return matrix;
    }

    private String buildStringFromMatrix(char[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (char[] row : matrix) {
            for (char current : row) {
                if (current != '\0') {
                    result.append(current);
                }
            }
        }

        return result.toString();
    }
}
