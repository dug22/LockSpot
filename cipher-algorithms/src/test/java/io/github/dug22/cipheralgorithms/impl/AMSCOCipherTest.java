package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.AMSCOCipher;

public class AMSCOCipherTest extends AbstractCipherTestCase {

    @Test
    public void testAMSCOCipher(){
        String plaintext = "The AMSCO cipher is a transposition/column swap cipher. The key dictates the number of columns to use.";
        String key = "532146";
        AMSCOCipher amscoCipher = (AMSCOCipher) getCipher("AMSCO Cipher");
        String ciphertext = amscoCipher.encrypt(plaintext, key);
        String decryptedText = amscoCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"AMHETRTIMNIPE TA NF  T P IUCHCEOSHECI AOSOLP  TDITHR MNE.SRAO HKTUCOT SPCA.  EUSCO INSN/SWEREYESMBOL U", "amscoEncryptTest");
        assertTestResult( decryptedText, "THE AMSCO CIPHER IS A TRANSPOSITION/COLUMN SWAP CIPHER. THE KEY DICTATES THE NUMBER OF COLUMNS TO USE.", "amscoDecryptTest");
    }
}
