package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.PlayfairCipher;

public class PlayfairCipherTest extends AbstractCipherTestCase {

    @Test
    public void testPlayfairCipher(){
        String plaintext = "The Playfair Cipher contains a set of rules that must be followed.";
        String key = "PLAYFAIR";
        PlayfairCipher playfairCipher = (PlayfairCipher) getCipher("Playfair Cipher");
        String ciphertext = playfairCipher.encrypt(plaintext, key);
        String decryptedText = playfairCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"QMNIAYFPPBBDEIKGBDQOQFEUQYNKNQLDVPKNQMFQEZTNIHLTYVRVUHCZ", "playfairEncryptTest");
        assertTestResult( decryptedText, "THEPLAYFAIRCIPHERCONTAINSASETOFRULESTHATMUSTBEFOLXLOWEDX", "playfairDecryptTest");
    }
}
