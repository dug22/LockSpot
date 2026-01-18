package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.BifidCipher;
import io.github.dug22.lockspot.cipheralgorithms.impl.CaesarCipher;

public class BifidCipherTest extends AbstractCipherTestCase {

    @Test
    public void testBifidCipher(){
        String plaintext = "The Bifid cipher combines the Polybius square with transposition";
        String key = "BIFID";
        BifidCipher bifidCipher = (BifidCipher) getCipher("Bifid Cipher");
        String ciphertext = bifidCipher.encrypt(plaintext, key);
        String decryptedText = bifidCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"4424221112131214211235242242213432111233224344242235343154111245434341451542225212442444421533433534431244123433", "bifidEncryptTest");
        assertTestResult( decryptedText, "THEBIFIDCIPHERCOMBINESTHEPOLYBIUSSQUAREWITHTRANSPOSITION", "bifidDecryptTest");
    }
}
