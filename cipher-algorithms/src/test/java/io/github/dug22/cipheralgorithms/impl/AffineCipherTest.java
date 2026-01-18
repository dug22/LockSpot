package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.AffineCipher;

import java.util.Random;

public class AffineCipherTest extends AbstractCipherTestCase {

    @Test
    public void testAffineCipher() {
        String plaintext = "The Affine cipher is monoalphabetic substitution cipher, where each letter in a given alphabet is mapped to its numerical equivalent.";
        Random random = new Random(42);
        String key = 17 + " " + (random.nextInt(26 - 1));
        AffineCipher affineCipher = (AffineCipher) getCipher("Affine Cipher");
        String ciphertext = affineCipher.encrypt(plaintext, key);
        String decryptedText = affineCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext, "QUV FMMLSV NLAUVI LZ BJSJFKAUFWVQLN ZHWZQLQHQLJS NLAUVI, PUVIV VFNU KVQQVI LS F DLYVS FKAUFWVQ LZ BFAAVE QJ LQZ SHBVILNFK VRHLYFKVSQ.", "affineEncryptTest");
        assertTestResult(decryptedText, "THE AFFINE CIPHER IS MONOALPHABETIC SUBSTITUTION CIPHER, WHERE EACH LETTER IN A GIVEN ALPHABET IS MAPPED TO ITS NUMERICAL EQUIVALENT.", "affineDecryptTest");
    }
}
