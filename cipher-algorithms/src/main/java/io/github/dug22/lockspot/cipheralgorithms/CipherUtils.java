package io.github.dug22.lockspot.cipheralgorithms;

import io.github.dug22.lockspot.cipheralgorithms.impl.VigenereCipher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CipherUtils {

    public static byte[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();

    public static String upperCase(String text) {
        text = text.toUpperCase();
        return text;
    }

    public static int generateRandomKeyAsNumber(int low, int high){
        return (int)  (Math.random() * (high - low)) + high;
    }

    public static String getRandomKeyFromFile(){
        InputStream inputStream = VigenereCipher.class.getResourceAsStream("/random-english-keywords.txt");
        List<String> keys = new ArrayList<>();
        try {
            assert inputStream != null;
            try (InputStreamReader isReader = new InputStreamReader(inputStream)) {
                BufferedReader bufferedReader = new BufferedReader(isReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Collections.addAll(keys, line.split(","));
                }
            }
            return keys.get((int) (Math.random() * keys.size()));
        } catch (Exception ignore) {
        }

        return "KEY";
    }
}
