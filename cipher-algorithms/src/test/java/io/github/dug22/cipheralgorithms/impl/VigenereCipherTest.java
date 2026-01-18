package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.VigenereCipher;

public class VigenereCipherTest extends AbstractCipherTestCase {

    @Test
    public void testVigenereCipher(){
        String plaintext = "The Vigenere cipher is a polyalphabetic substitution cipher";
        String key = "VIGENERE";
        VigenereCipher vigenereCipher = (VigenereCipher) getCipher("Vigenère Cipher");
        String ciphertext = vigenereCipher.encrypt(plaintext, key);
        String decryptedText = vigenereCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"OPK ZVKVRZZK GVTYIM QY E CSCCVTVLNFVXDK YYOWKMOCZMBR TMKPKV", "vigenereEncryptTest");
        assertTestResult( decryptedText, "THE VIGENERE CIPHER IS A POLYALPHABETIC SUBSTITUTION CIPHER", "vigenereDecryptTest");
    }
}
