package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.PolybiusCipher;

public class PolybiusCipherTest extends AbstractCipherTestCase {

    @Test
    public void testPolybiusCipher(){
        String plaintext = "The Polybius cipher uses a 5x5 grid.";
        String key = "";
        PolybiusCipher polybiusCipher = (PolybiusCipher) getCipher("Polybius Cipher");
        String ciphertext = polybiusCipher.encrypt(plaintext, key);
        String decryptedText = polybiusCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"442315353431541224454313243523154245431543115322422414", "polybiusEncryptTest");
        assertTestResult( decryptedText, "THEPOLYBIUSCIPHERUSESAXGRID", "polybiusDecryptTest");
    }
}
