package io.github.dug22.cipheralgorithms.impl;

import io.github.dug22.cipheralgorithms.AbstractCipherTestCase;
import io.github.dug22.cipheralgorithms.Test;
import io.github.dug22.lockspot.cipheralgorithms.impl.BaconianCipher;

public class BaconianCipherTest extends AbstractCipherTestCase {

    @Test
    public void testBaconianCipher(){
        String plaintext = "Baconian is a method of steganography";
        String key = "";
        BaconianCipher baconianCipher = (BaconianCipher) getCipher("Baconian Cipher");
        String ciphertext = baconianCipher.encrypt(plaintext, key);
        String decryptedText = baconianCipher.decrypt(ciphertext, key);
        assertTestResult(ciphertext,"AAAABAAAAAAAABAABBABABBAAABAAAAAAAAABBAABBBAAABAAABAAABBBBAAAAAAABBBAAABABBAABAABAABAAABBBABBABAAABBBBBAAABBABAABABBBBAABAAABBAABAAABAAAABBAAAAAAABBAAABBABAABBABAAAAAAAAAABBBAAABBBBABBA", "baconianEncryptTest");
        assertTestResult( decryptedText, "BACON|I or J|AN |I or J|S A METHOD OF STEGANOGRAPHY", "baconianDecryptTest");
    }
}
