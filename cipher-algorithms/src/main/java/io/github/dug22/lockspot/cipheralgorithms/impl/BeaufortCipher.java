package io.github.dug22.lockspot.cipheralgorithms.impl;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherType;
import io.github.dug22.lockspot.cipheralgorithms.CipherUtils;

public class BeaufortCipher extends AbstractCipher {

    private final String alphabet = getAlphabet();

    @Override
    public String name() {
        return "Beaufort Cipher";
    }

    @Override
    public int id() {
        return 7;
    }

    @Override
    public String cipherType(){
        return CipherType.POLYALPHABETIC_SUBSTITUTION.name();
    }

    @Override
    public String encrypt(String plaintext, String key) {
        return encryptOrDecrypt(plaintext.toUpperCase(), key.toUpperCase());
    }

    @Override
    public String decrypt(String ciphertext, String key) {
        return encryptOrDecrypt(ciphertext, key);
    }

    @Override
    public String generateRandomKey() {
        return CipherUtils.getRandomKeyFromFile();
    }

    private String encryptOrDecrypt(String text, String key) {
        StringBuilder resultingText = new StringBuilder();
        int textLength = text.length();
        int keyLength = key.length();
        int alphabetLength = alphabet.length();
        int unknownCharacters = 0;
        for (int i = 0; i < textLength; i++) {
            char character = text.charAt(i);
            if (!alphabet.contains(String.valueOf(character))) {
                resultingText.append(character);
                unknownCharacters++;
            } else {
                int keyIndex = (i - unknownCharacters) % keyLength;
                char keyCharacter = key.charAt(keyIndex);
                int textValue = alphabet.indexOf(character);
                int keyValue = alphabet.indexOf(keyCharacter);
                int resultingValue = (keyValue - textValue + alphabetLength) % alphabetLength;
                resultingText.append(getAlphabet().charAt(resultingValue));
            }
        }

        return resultingText.toString();
    }
}
