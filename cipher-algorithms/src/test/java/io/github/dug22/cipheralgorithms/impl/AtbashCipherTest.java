package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.AtbashCipher;

public class AtbashCipherTest extends AbstractCipherTestCase {

    @Test
    public void testAtbashCipher(){
        String plaintext = "The Atbash cipher is a substitution cipher with one specific key where all the letters are reversed. For example, A to Z and Z to A, B to Y, Y to B, etc.";
        String key = "";
        AtbashCipher atbashCipher = (AtbashCipher) getCipher("Atbash Cipher");
        String ciphertext = atbashCipher.encrypt(plaintext, key);
        String decryptedText = atbashCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"GSV ZGYZHS XRKSVI RH Z HFYHGRGFGRLM XRKSVI DRGS LMV HKVXRURX PVB DSVIV ZOO GSV OVGGVIH ZIV IVEVIHVW. ULI VCZNKOV, Z GL A ZMW A GL Z, Y GL B, B GL Y, VGX.", "atbashEncryptTest");
        assertTestResult( decryptedText, "THE ATBASH CIPHER IS A SUBSTITUTION CIPHER WITH ONE SPECIFIC KEY WHERE ALL THE LETTERS ARE REVERSED. FOR EXAMPLE, A TO Z AND Z TO A, B TO Y, Y TO B, ETC.", "atbashDecryptTest");
    }
}
