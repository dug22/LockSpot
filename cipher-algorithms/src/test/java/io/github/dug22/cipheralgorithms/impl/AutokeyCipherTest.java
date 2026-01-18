package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.AutokeyCipher;

public class AutokeyCipherTest extends AbstractCipherTestCase {

    @Test
    public void testAutokeyCipher(){
        String plaintext = "The autokey cipher is a cipher tha incorporates the plaintext into the key";
        String key = "AUTOKEY";
        AutokeyCipher autokeyCipher = (AutokeyCipher) getCipher("Autokey Cipher");
        String ciphertext = autokeyCipher.encrypt(plaintext, key);
        String decryptedText = autokeyCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"TBX OEXMDLC CCIVOV GU I RPTYMJ TJI XUGFKWOZNVSJ IVV PEEAGAIME IVGH XEX SRR", "autokeyEncryptTest");
        assertTestResult( decryptedText, "THE AUTOKEY CIPHER IS A CIPHER THA INCORPORATES THE PLAINTEXT INTO THE KEY", "autokeyDecryptTest");
    }
}
