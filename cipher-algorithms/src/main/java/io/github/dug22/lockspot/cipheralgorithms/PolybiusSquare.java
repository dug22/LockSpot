package io.github.dug22.lockspot.cipheralgorithms;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PolybiusSquare {


    public static char[][] createPolybiusSquare() {
        int size = 5;
        char[][] polybiusSquare = new char[size][size];
        final char[] letter = {'A'};
        final char disallowedCharacter = 'J';
        IntStream.range(0, size).forEach(i -> IntStream.range(0, size).forEach(j -> {
            if (letter[0] == disallowedCharacter) {
                letter[0]++;
            }
            polybiusSquare[i][j] = letter[0];
            letter[0]++;
        }));
        return polybiusSquare;
    }

    public static char[][] createPolybiusSquareWithKey(String key) {
        char[][] polybiusSquare = new char[5][5];
        final char disallowedChar = 'J';
        Set<Character> uniqueCharacters = new LinkedHashSet<>();
        for (char character : key.toCharArray()) {
            if (Character.isLetter(character) && character != disallowedChar) {
                uniqueCharacters.add(character);
            }
        }

        String alphabet = new String(CipherUtils.ALPHABET);

        for (char character : alphabet.toCharArray()) {
            if (Character.isLetter(character) && character != disallowedChar) {
                uniqueCharacters.add(character);
            }
        }

        Iterator<Character> iterator = uniqueCharacters.iterator();
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5).forEach(j -> {
            if (iterator.hasNext()) {
                polybiusSquare[i][j] = iterator.next();
            }
        }));
        return polybiusSquare;
    }
}
