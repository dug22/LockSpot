package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.RailFenceCipher;

public class RailFenceCipherTest extends AbstractCipherTestCase {

    @Test
    public void testRailFenceCipher(){
        String plaintext = "The rail fence cipher is a classical type of transposition cipher";
        String key = "8";
        RailFenceCipher railFenceCipher = (RailFenceCipher) getCipher("Rail Fence Cipher");
        String ciphertext = railFenceCipher.encrypt(plaintext, key);
        String decryptedText = railFenceCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"T LOOHECCA FINECI SE T  NPASPTICREH IYRSIAFESCTAOPI RIA NPHRL LSE", "railFenceEncryptTest");
        assertTestResult( decryptedText, "THE RAIL FENCE CIPHER IS A CLASSICAL TYPE OF TRANSPOSITION CIPHER", "railFenceDecryptTest");
    }
}
