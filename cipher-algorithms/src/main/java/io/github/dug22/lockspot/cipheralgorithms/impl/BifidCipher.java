package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;
import io.github.dug22.lockspot.cipheralgorithms.PolybiusSquare;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

// Implementation of this BifidCipher Class  comes from here https://github.com/mouli-dutta/Bifid-Cipher/ with modifications applied
public class BifidCipher extends AbstractCipher {


    @Override
    public String name() {
        return "Bifid Cipher";
    }

    @Override
    public int id() {
        return 8;
    }

    @Override
    public String encrypt(String plaintext, String key) {
        key = upperCase(key);
        key = key.replace("J", "I");
        plaintext = plaintext.replace("J", "I");
        char[][] polybiusSquare = PolybiusSquare.createPolybiusSquareWithKey(key);
        StringBuilder fullSquare = new StringBuilder();
        for (char[] chars : polybiusSquare) {
            for (char character : chars) {
                if (Character.isLetter(character)) {
                    fullSquare.append(character);
                }
            }
        }
        Map<Character, String> characterPositionsMap = IntStream.range(0, fullSquare.length())
                .boxed()
                .collect(Collectors.toMap(fullSquare::charAt,
                        i -> (i / 5 + 1) + "" + (i % 5 + 1),
                        (_, second) -> second, LinkedHashMap::new));

        return plaintext.chars()
                .mapToObj(c -> characterPositionsMap.get((char) c))
                .filter(Objects::nonNull)
                .collect(joining());
    }


    @Override
    public String decrypt(String ciphertext, String key) {
        char[][] polybiusSquare = PolybiusSquare.createPolybiusSquareWithKey(key);
        StringBuilder fullSquare = new StringBuilder();
        int ciphertextLength = ciphertext.length();

        for (char[] chars : polybiusSquare) {
            for (char character : chars) {
                if (Character.isLetter(character)) {
                    fullSquare.append(character);
                }
            }
        }
        int fullSquareLength = fullSquare.length();

        Map<String, Character> ciphertextPositionsMap = IntStream.range(0, fullSquareLength)
                .boxed()
                .collect(Collectors.toMap(i -> (i / 5 + 1) + "" + (i % 5 + 1),
                        fullSquare::charAt,
                        (a, b) -> b, LinkedHashMap::new));

        return IntStream.iterate(0, i -> i < ciphertextLength, i -> i + 2)
                .mapToObj(i -> ciphertextPositionsMap.get(ciphertext.substring(i, i + 2)))
                .map(String::valueOf)
                .collect(joining());
    }


    @Override
    public String generateRandomKey() {
        return CipherUtils.getRandomKeyFromFile();
    }
}
