package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.ADFGVXCipher;

public class ADFGVXCipherTest extends AbstractCipherTestCase {

    @Test
    public void testADFGVXCipher() {
        String plaintext = "The ADFGVX is a German encryption system dating from WWI.";
        String key = "ADFGVX SECRET";
        ADFGVXCipher adfgvxCipher = (ADFGVXCipher) getCipher("ADFGVX Cipher");
        String ciphertext = adfgvxCipher.encrypt(plaintext, key);
        assertTestResult(ciphertext, "DAAGDA FVDGGADAFDGAG VGF FD GGFFGGVXDAAAG FDFFVDAFGGGDVFFAGAV FGDVFFVGAADAFD GGFGFGAFGFFXADG DXVAFAGDX .", "adfgvxEncryptTest");
        System.out.println("ADFGVX doesn't contain a decryption method. Skipping!");
    }
}
