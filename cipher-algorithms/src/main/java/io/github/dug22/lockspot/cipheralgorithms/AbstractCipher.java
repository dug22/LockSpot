package io.github.dug22.lockspot.cipheralgorithms;

public abstract class AbstractCipher {

    private final String ALPHABET = new String(CipherUtils.ALPHABET);

    public abstract String name();

    public abstract int id();

    public abstract String encrypt(String plaintext, String key);

    public abstract String decrypt(String ciphertext, String key);

    public abstract String generateRandomKey();

    public String getAlphabet() {
        return ALPHABET;
    }

    public String upperCase(String text) {
        return CipherUtils.upperCase(text);
    }
}
