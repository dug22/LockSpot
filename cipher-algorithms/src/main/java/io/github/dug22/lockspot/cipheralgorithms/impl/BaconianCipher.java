package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;

import java.util.Map;

import static java.util.Map.entry;

public class BaconianCipher extends AbstractCipher {

    private final Map<String, String> baconianEncryptionMap = Map.ofEntries(
            entry("A", "AAAAA"),
            entry("B", "AAAAB"),
            entry("C", "AAABA"),
            entry("D", "AAABB"),
            entry("E", "AABAA"),
            entry("F", "AABAB"),
            entry("G", "AABBA"),
            entry("H", "AABBB"),
            entry("I", "ABAAA"),
            entry("J", "ABAAA"),
            entry("K", "ABAAB"),
            entry("L", "ABABA"),
            entry("M", "ABABB"),
            entry("N", "ABBAA"),
            entry("O", "ABBAB"),
            entry("P", "ABBBA"),
            entry("Q", "ABBBB"),
            entry("R", "BAAAA"),
            entry("S", "BAAAB"),
            entry("T", "BAABA"),
            entry("U", "BAABB"),
            entry("V", "BAABB"),
            entry("W", "BABAA"),
            entry("X", "BABAB"),
            entry("Y", "BABBA"),
            entry("Z", "BABBB"),
            entry(" ", "BBBAA")
    );

    private final Map<String, String> baconianDecryptionMap = Map.ofEntries(
            entry("AAAAA", "A"),
            entry("AAAAB", "B"),
            entry("AAABA", "C"),
            entry("AAABB", "D"),
            entry("AABAA", "E"),
            entry("AABAB", "F"),
            entry("AABBA", "G"),
            entry("AABBB", "H"),
            entry("ABAAA", "|I or J|"),
            entry("ABAAB", "K"),
            entry("ABABA", "L"),
            entry("ABABB", "M"),
            entry("ABBAA", "N"),
            entry("ABBAB", "O"),
            entry("ABBBA", "P"),
            entry("ABBBB", "Q"),
            entry("BAAAA", "R"),
            entry("BAAAB", "S"),
            entry("BAABA", "T"),
            entry("BAABB", "|U or V|"),
            entry("BABAA", "W"),
            entry("BABAB", "X"),
            entry("BABBA", "Y"),
            entry("BABBB", "Z"),
            entry("BBBAA", " ")
    );


    @Override
    public String name() {
        return "Baconian Cipher";
    }

    @Override
    public int id() {
        return 6;
    }


    @Override
    public String encrypt(String plaintext, String key) {
        plaintext = upperCase(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char character = plaintext.charAt(i);
            if (baconianEncryptionMap.containsKey(String.valueOf(character))) {
                ciphertext.append(baconianEncryptionMap.get(String.valueOf(character)));
            }else {
                ciphertext.append(baconianEncryptionMap.get(" "));
            }
        }
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i+=5) {
            plaintext.append(baconianDecryptionMap.get(ciphertext.substring(i, i+5)));
        }
        return plaintext.toString();
    }

    @Override
    public String generateRandomKey() {
        return "";
    }
}
