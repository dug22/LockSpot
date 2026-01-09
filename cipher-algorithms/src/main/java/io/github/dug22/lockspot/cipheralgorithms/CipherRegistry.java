package io.github.dug22.lockspot.cipheralgorithms;

import io.github.dug22.lockspot.cipheralgorithms.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CipherRegistry {

    private static final Map<String, AbstractCipher> cipherAlgorithmsMap = new HashMap<>();

    static {
        cipherAlgorithmsMap.put("ADFGVX Cipher", new ADFGVXCipher());
        cipherAlgorithmsMap.put("Affine Cipher", new AffineCipher());
        cipherAlgorithmsMap.put("AMSCO Cipher", new AMSCOCipher());
        cipherAlgorithmsMap.put("Atbash Cipher", new AtbashCipher());
        cipherAlgorithmsMap.put("Autokey Cipher", new AutokeyCipher());
        cipherAlgorithmsMap.put("Baconian Cipher", new BaconianCipher());
        cipherAlgorithmsMap.put("Beaufort Cipher", new BeaufortCipher());
        cipherAlgorithmsMap.put("Bifid Cipher", new BifidCipher());
        cipherAlgorithmsMap.put("Caesar Cipher", new CaesarCipher());
        cipherAlgorithmsMap.put("Gronsfeld Cipher", new GronsfeldCipher());
        cipherAlgorithmsMap.put("Playfair Cipher", new PlayfairCipher());
        cipherAlgorithmsMap.put("Polybius Cipher", new PolybiusCipher());
        cipherAlgorithmsMap.put("Porta Cipher", new PortaCipher());
        cipherAlgorithmsMap.put("Rail Fence Cipher", new RailFenceCipher());
        cipherAlgorithmsMap.put("Vigenère Cipher", new VigenereCipher());
    }

    public static Map<String, AbstractCipher> getCipherAlgorithmsMap() {
        return cipherAlgorithmsMap;
    }
}
