package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.PortaCipher;

public class PortaCipherTest extends AbstractCipherTestCase {

    @Test
    public void testPortaCipher(){
        String plaintext = "The Porta cipher uses a 13x13 tableau.";
        String key = "PORTA";
        PortaCipher portaCipher = (PortaCipher) getCipher("Porta Cipher");
        String ciphertext = portaCipher.encrypt(plaintext, key);
        String decryptedText = portaCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"MOZ CHKLW WPHQRK MJRL V 13D13 MUWURUA.", "portaEncryptTest");
        assertTestResult( decryptedText, "THE PORTA CIPHER USES A 13X13 TABLEAU.", "portaDecryptTest");
    }
}
