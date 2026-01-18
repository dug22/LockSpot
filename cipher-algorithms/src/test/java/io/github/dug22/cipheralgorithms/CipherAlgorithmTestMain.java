package io.github.dug22.cipheralgorithms;

import io.github.dug22.cipheralgorithms.impl.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CipherAlgorithmTestMain {

    private static final List<AbstractCipherTestCase> cipherTests = Arrays.asList(
            new ADFGVXCipherTest(),
            new AffineCipherTest(),
            new AMSCOCipherTest(),
            new AtbashCipherTest(),
            new AutokeyCipherTest(),
            new BaconianCipherTest(),
            new BeaufortCipherTest(),
            new BifidCipherTest(),
            new CaesarCipherTest(),
            new GronsfeldCipherTest(),
            new PlayfairCipherTest(),
            new PolybiusCipherTest(),
            new PortaCipherTest(),
            new RailFenceCipherTest(),
            new VigenereCipherTest());

    public static void main(String[] args) throws Exception {
        for (AbstractCipherTestCase cipherTest : cipherTests) {
            Class<? extends AbstractCipherTestCase> cipherClass = cipherTest.getClass();
            Method[] methods = cipherClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    method.setAccessible(true);
                    method.invoke(cipherTest);
                }
            }
        }
    }
}