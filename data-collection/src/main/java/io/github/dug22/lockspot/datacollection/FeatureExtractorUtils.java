package io.github.dug22.lockspot.datacollection;

import io.github.dug22.carpentry.DataFrame;
import io.github.dug22.carpentry.column.impl.DoubleColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorUtils {

    private static final String ALNUM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static double findIndexOfCoincidence(String ciphertext) {
        int ciphertextLength = ciphertext.length();
        HashMap<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (char character : ciphertext.toCharArray()) {
            if (Character.isLetter(character)) {
                characterFrequencyMap.merge(character, 1, Integer::sum);
            }
        }

        long numerator = 0;
        for (int frequency : characterFrequencyMap.values()) {
            numerator += (long) frequency * (frequency - 1);
        }

        double denominator = (double) ciphertextLength * (ciphertextLength - 1);
        return numerator / denominator;
    }


    public static int hasLetterJ(String ciphertext) {
        return ciphertext.contains("J") ? 1 : 0;
    }

    public static int hasDigits(String ciphertext) {
        return ciphertext.matches(".*\\d.*") ? 1 : 0;
    }

    public static int hasDoubleLettersOrNumbers(String ciphertext) {
        for (int i = 0; i < ciphertext.length() - 1; i++) {
            if (ciphertext.charAt(i) == ciphertext.charAt(i + 1)) {
                return 1;
            }
        }

        return 0;
    }



    public static List<Double> getFrequencyProbabilityVectors(String ciphertext) {
        Map<Character, Integer> frequencies = new HashMap<>();
        int validCharCount = 0;

        for (char character : ciphertext.toCharArray()) {
            if (Character.isLetter(character)) {
                character = Character.toUpperCase(character);
            }
            if (Character.isLetterOrDigit(character)) {
                if(frequencies.get(character) != null){
                    frequencies.put(character, frequencies.get(character) + 1);
                }else{
                    frequencies.put(character, 1);
                }
                validCharCount++;
            }
        }

        List<Double> frequencyProbabilityVector = new ArrayList<>();

        for (char character : ALNUM_CHARACTERS.toCharArray()) {
            int count = frequencies.getOrDefault(character, 0);
            frequencyProbabilityVector.add(validCharCount == 0 ? 0.0 : (double) count / validCharCount);
        }

        return frequencyProbabilityVector;
    }


    public static List<DoubleColumn> createEmptyFrequencyVectorColumns() {
        List<DoubleColumn> columns = new ArrayList<>();
        for (char c : ALNUM_CHARACTERS.toCharArray()) {
            columns.add(DoubleColumn.create("Frequency_" + c));
        }
        return columns;
    }

    public static void appendFrequencyVectorToColumns(String ciphertext, List<DoubleColumn> columns) {
        List<Double> vector = getFrequencyProbabilityVectors(ciphertext);
        for (int i = 0; i < vector.size(); i++) {
            columns.get(i).append(vector.get(i));
        }
    }
}
