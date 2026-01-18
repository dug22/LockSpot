package io.github.dug22.cipheralgorithms;

import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherRegistry;

import java.util.Map;

public abstract class AbstractCipherTestCase {

    public void assertTestResult(String actual, String expected, String testName){
        if(actual.equals(expected)){
            System.out.println(testName + " Passed");
        }else{
            System.out.println(testName + " Failed! Expected: " + expected);
        }
    }

    public AbstractCipher getCipher(String cipherName){
        return CipherRegistry.getCipherAlgorithmsMap().get(cipherName);
    }
}
