package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.CaesarCipher;

public class CaesarCipherTest extends AbstractCipherTestCase {

    @Test
    public void testCaesarCipher(){
        String plaintext = "There are only 26 possible shifts with the Caesar cipher";
        String key = "18";
        CaesarCipher caesarCipher = (CaesarCipher) getCipher("Caesar Cipher");
        String ciphertext = caesarCipher.encrypt(plaintext, key);
        String decryptedText = caesarCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"LZWJW SJW GFDQ 26 HGKKATDW KZAXLK OALZ LZW USWKSJ UAHZWJ", "caesarEncryptTest");
        assertTestResult( decryptedText, "THERE ARE ONLY 26 POSSIBLE SHIFTS WITH THE CAESAR CIPHER", "caesarDecryptTest");
    }
}
