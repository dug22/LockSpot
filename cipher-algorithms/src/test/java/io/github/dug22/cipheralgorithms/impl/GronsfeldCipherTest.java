package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.GronsfeldCipher;

public class GronsfeldCipherTest extends AbstractCipherTestCase {

    @Test
    public void testGronsfeldCipher(){
        String plaintext = "The Gronsfeld cipher is a Vigenere-style polyalphabetic cipher";
        String key = "3902154";
        GronsfeldCipher gronsfeldCipher = (GronsfeldCipher) getCipher("Gronsfeld Cipher");
        String ciphertext = gronsfeldCipher.encrypt(plaintext, key);
        String decryptedText = gronsfeldCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"WQE HWSQBFGMI FRPJFW LB C AMJNNGSJ-VCYNF TRUYCMULDKEVJH FRPJFW", "gronsfeldEncryptTest");
        assertTestResult( decryptedText, "THE GRONSFELD CIPHER IS A VIGENERE-STYLE POLYALPHABETIC CIPHER", "gronsfeldDecryptTest");
    }
}
