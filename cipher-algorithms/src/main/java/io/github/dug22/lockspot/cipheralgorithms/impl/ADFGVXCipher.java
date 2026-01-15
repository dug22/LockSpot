package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

import java.util.*;
import java.util.stream.IntStream;

public class ADFGVXCipher extends AbstractCipher {

    private final String[] adfgvxRowLabels = new String[]{"A", "D", "F", "G", "V", "X"};

    @Override
    public String name() {
        return "ADFGVX Cipher";
    }

    @Override
    public String cipherType(){
        return CipherType.FRACTIONATING.name() + " " + CipherType.TRANSPOSITION.name();
    }


    @Override
    public String encrypt(String plaintext, String key) {
        String[] keys = key.split(" ");
        String gridKey = keys[0];
        String transpositionKey = keys[1];

        char[][] cipherGrid = createGrid(gridKey);

        Map<Character, String> lookup = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                lookup.put(cipherGrid[i][j], adfgvxRowLabels[i] + adfgvxRowLabels[j]);
            }
        }

        plaintext = plaintext.toUpperCase();

        StringBuilder substitutedText = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            substitutedText.append(lookup.getOrDefault(c, String.valueOf(c)));
        }

        StringBuilder allowedCharacters = new StringBuilder();
        Map<Integer, Character> preservedCharacters = new HashMap<>();
        for (int i = 0; i < substitutedText.length(); i++) {
            char c = substitutedText.charAt(i);
            if ("ADFGVX".indexOf(c) >= 0) {
                allowedCharacters.append(c);
            } else {
                preservedCharacters.put(i, c);
            }
        }

        List<String> table = createTranspositionTable(allowedCharacters.toString(), transpositionKey);
        String transposed = transposeAndEncrypt(table, transpositionKey);
        StringBuilder finalCiphertext = new StringBuilder(transposed);
        for (Map.Entry<Integer, Character> e : preservedCharacters.entrySet()) {
            if (e.getKey() <= finalCiphertext.length()) {
                finalCiphertext.insert(e.getKey(), (Object) e.getValue());
            } else {
                finalCiphertext.append(e.getValue());
            }
        }

        return finalCiphertext.toString();
    }



    @Override
    public String decrypt(String ciphertext, String key) {
        throw new UnsupportedOperationException("No decryption method implemented!");
    }

    @Override
    public String generateRandomKey() {
        String firstKey = CipherUtils.getRandomKeyFromFile();
        String secondKey = CipherUtils.getRandomKeyFromFile();
        Set<Character> firstUniqueKey = new LinkedHashSet<>();
        for (char c : firstKey.toCharArray()) firstUniqueKey.add(c);
        StringBuilder firstCleanKey = new StringBuilder();
        for (char c : firstUniqueKey) firstCleanKey.append(c);
        Set<Character> secondUniqueKey = new LinkedHashSet<>();
        for (char c : secondKey.toCharArray()) secondUniqueKey.add(c);
        StringBuilder secondCleanKey = new StringBuilder();
        for (char c : secondUniqueKey) secondCleanKey.append(c);
        return firstCleanKey + " " + secondCleanKey;
    }


    private char[][] createGrid(String key) {
        key = key + getAlphabet() + "0123456789";
        int ROW = 6;
        int COL = 6;
        char[][] cipherGrid = new char[ROW][COL];
        Set<Character> alphabetList = new LinkedHashSet<>();
        for (char c : key.toCharArray()) {
            alphabetList.add(c);
        }

        Iterator<Character> iterator = alphabetList.iterator();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (iterator.hasNext()) {
                    cipherGrid[i][j] = iterator.next();

                }

            }
        }


        return cipherGrid;
    }

    private List<String> createTranspositionTable(String text, String key) {
        int columns = key.length();
        int textLength = text.length();
        int rows = (int) Math.ceil((double) textLength / columns);
        List<String> table = new ArrayList<>();
        IntStream.range(0, rows).forEach(i -> {
            int start = i * columns;
            int end = Math.min(start + columns, textLength);
            table.add(text.substring(start, end));
        });
        return table;
    }

    private static String transposeAndEncrypt(List<String> table, String key) {
        char[] keyArray = key.toCharArray();
        Integer[] columnIndices = new Integer[keyArray.length];

        for (int i = 0; i < keyArray.length; i++) {
            columnIndices[i] = i;
        }

        Arrays.sort(columnIndices, Comparator.comparingInt(o -> keyArray[o]));

        StringBuilder ciphertext = new StringBuilder();

        for (int col : columnIndices) {
            for (String row : table) {
                if (col < row.length()) {
                    ciphertext.append(row.charAt(col));
                }
            }
        }

        return ciphertext.toString();
    }
}
