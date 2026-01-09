package io.github.dug22.lockspot.datacollection;

import io.github.dug22.carpentry.DataFrame;
import io.github.dug22.carpentry.column.impl.DoubleColumn;
import io.github.dug22.carpentry.column.impl.IntegerColumn;
import io.github.dug22.carpentry.column.impl.StringColumn;
import io.github.dug22.carpentry.io.csv.CsvReadingProperties;
import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherRegistry;
import io.github.dug22.lockspot.cipheralgorithms.impl.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DatasetCollectionProcessor {

    private static final CsvReadingProperties csvReadingProperties = new CsvReadingProperties()
            .setMaxColumnCharacterLength(Integer.MAX_VALUE)
            .setSource(new File("D:\\LockSpot\\data-collection\\src\\main\\resources\\text-dataset.csv"))
            .setDelimiter(',');

    private static final DataFrame dataFrame = DataFrame.read().csv(csvReadingProperties);
    private static final Map<String, AbstractCipher> cipherAlgorithmsMap = CipherRegistry.getCipherAlgorithmsMap();
    private static final ADFGVXCipher adfgxCipher = (ADFGVXCipher) cipherAlgorithmsMap.get("ADFGVX Cipher");
    private static final AffineCipher affineCipher = (AffineCipher) cipherAlgorithmsMap.get("Affine Cipher");
    private static final AMSCOCipher amscoCipher = (AMSCOCipher) cipherAlgorithmsMap.get("AMSCO Cipher");
    private static final AtbashCipher atbashCipher = (AtbashCipher) cipherAlgorithmsMap.get("Atbash Cipher");
    private static final AutokeyCipher autokeyCipher = (AutokeyCipher) cipherAlgorithmsMap.get("Autokey Cipher");
    private static final BaconianCipher baconianCipher = (BaconianCipher) cipherAlgorithmsMap.get("Baconian Cipher");
    private static final BeaufortCipher beaufortCipher = (BeaufortCipher) cipherAlgorithmsMap.get("Beaufort Cipher");
    private static final BifidCipher bifidCipher = (BifidCipher) cipherAlgorithmsMap.get("Bifid Cipher");
    private static final CaesarCipher caesarCipher = (CaesarCipher) cipherAlgorithmsMap.get("Caesar Cipher");
    private static final GronsfeldCipher gronsfeldCipher = (GronsfeldCipher) cipherAlgorithmsMap.get("Gronsfeld Cipher");
    private static final PlayfairCipher playfairCipher = (PlayfairCipher) cipherAlgorithmsMap.get("Playfair Cipher");
    private static final PolybiusCipher polybiusCipher = (PolybiusCipher) cipherAlgorithmsMap.get("Polybius Cipher");
    private static final PortaCipher portacipher = (PortaCipher) cipherAlgorithmsMap.get("Porta Cipher");
    private static final RailFenceCipher railFenceCipher = (RailFenceCipher) cipherAlgorithmsMap.get("Rail Fence Cipher");
    private static final VigenereCipher vigenereCipher = (VigenereCipher) cipherAlgorithmsMap.get("Vigenère Cipher");
    private static final StringColumn cipherAlgorithmNameColumn = StringColumn.create("Cipher Algorithm");
    private static final IntegerColumn cipherAlgorithmIDColumn = IntegerColumn.create("Cipher Algorithm ID");
    private static final StringColumn ciphertextColumn = StringColumn.create("Ciphertext");
    private static final StringColumn keyColumn = StringColumn.create("Key");
    private static final StringColumn decryptedPlaintextColumn = StringColumn.create("Decrypted Plaintext");
    private static final DoubleColumn indexOfCoincidenceColumn = DoubleColumn.create("Index of Coincidence");
    private static final IntegerColumn hasLetterJColumn = IntegerColumn.create("Has Letter J?");

    public static void addCipherDetails() {
        int counter = 0;
        for (String originalText : dataFrame.stringColumn("Original Text").getValues()) {
            if (counter <= 3332) {
                retrieveCipherDetails(adfgxCipher, originalText);
            } else if (counter <= 6665) {
                retrieveCipherDetails(affineCipher, originalText);
            } else if (counter <= 9998) {
                retrieveCipherDetails(amscoCipher, originalText);
            } else if (counter <= 13331) {
                retrieveCipherDetails(atbashCipher, originalText);
            } else if (counter <= 16664) {
                retrieveCipherDetails(autokeyCipher, originalText);
            } else if (counter <= 19997) {
                retrieveCipherDetails(baconianCipher, originalText);
            } else if (counter <= 23330) {
                retrieveCipherDetails(beaufortCipher, originalText);
            } else if (counter <= 26663) {
                retrieveCipherDetails(bifidCipher, originalText);
            } else if (counter <= 29996) {
                retrieveCipherDetails(caesarCipher, originalText);
            } else if (counter <= 33329) {
                retrieveCipherDetails(gronsfeldCipher, originalText);
            } else if (counter <= 36662) {
                retrieveCipherDetails(playfairCipher, originalText);
            } else if (counter <= 39995) {
                retrieveCipherDetails(polybiusCipher, originalText);
            } else if (counter <= 43328) {
                retrieveCipherDetails(portacipher, originalText);
            } else if (counter <= 46662) {
                retrieveCipherDetails(railFenceCipher, originalText);
            } else {
                retrieveCipherDetails(vigenereCipher, originalText);
            }
            if (counter % 1000 == 0) {
                System.out.println("Encountered " + counter + " entries so far!");
            }
            counter++;
        }

        dataFrame.addColumns(cipherAlgorithmNameColumn, cipherAlgorithmIDColumn, ciphertextColumn, decryptedPlaintextColumn, keyColumn, indexOfCoincidenceColumn, hasLetterJColumn);
    }

    private static void retrieveCipherDetails(AbstractCipher cipherAlgorithm, String plaintext) {
        String key = cipherAlgorithm.generateRandomKey();
        String ciphertext = cipherAlgorithm.encrypt(plaintext, key);
        String decryptedPlaintext;
        if (cipherAlgorithm instanceof ADFGVXCipher) {
            decryptedPlaintext = plaintext;
        } else {
            decryptedPlaintext = cipherAlgorithm.decrypt(ciphertext, key);
        }

        cipherAlgorithmNameColumn.append(cipherAlgorithm.name());
        cipherAlgorithmIDColumn.append(cipherAlgorithm.id());
        ciphertextColumn.append(ciphertext);
        decryptedPlaintextColumn.append(decryptedPlaintext);
        keyColumn.append(key);
        indexOfCoincidenceColumn.append(findIndexOfCoincidence(ciphertext));
        hasLetterJColumn.append(hasLetterJ(ciphertext) ? 1 : 0);

    }


    private static double findIndexOfCoincidence(String ciphertext) {
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

    private static boolean hasLetterJ(String ciphertext) {
        return ciphertext.contains("J");
    }


    public static void main(String[] args) {
        dataFrame.info();
        addCipherDetails();
        dataFrame.head();
        dataFrame.write().toCsv(new File("D:\\LockSpot\\data-collection\\src\\main\\resources\\lock-spot-cipher-dataset-2.csv"));
        System.out.println("We have successfully saved the given dataset!");
    }
}
