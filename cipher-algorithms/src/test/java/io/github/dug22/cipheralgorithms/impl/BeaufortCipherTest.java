package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.BeaufortCipher;

public class BeaufortCipherTest extends AbstractCipherTestCase {

    @Test
    public void testBeaufortCipher(){
        String plaintext = "The Beaufort cipher, created by Sir Francis Beaufort, is a substitution cipher similar to the Vigenere cipher";
        String key = "BEAUFORT";
        BeaufortCipher beaufortCipher = (BeaufortCipher) getCipher("Beaufort Cipher");
        String ciphertext = beaufortCipher.encrypt(plaintext, key);
        String decryptedText = beaufortCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"IXW TBOXONNH SXZKPK, CJQFVNQ AG IMO JATOCSC EKRZWQJB, XW R BHDIBXVXATQN SXZKPK MSIXDRC IQ HNB TJNXRWDB MJEUAJ", "beaufortEncryptTest");
        assertTestResult( decryptedText, "THE BEAUFORT CIPHER, CREATED BY SIR FRANCIS BEAUFORT, IS A SUBSTITUTION CIPHER SIMILAR TO THE VIGENERE CIPHER", "beaufortDecryptTest");
    }
}
