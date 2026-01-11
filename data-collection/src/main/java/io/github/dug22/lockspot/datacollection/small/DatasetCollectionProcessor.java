package io.github.dug22.lockspot.datacollection.small;

import io.github.dug22.carpentry.DataFrame;
import io.github.dug22.carpentry.column.impl.DoubleColumn;
import io.github.dug22.carpentry.column.impl.IntegerColumn;
import io.github.dug22.carpentry.column.impl.StringColumn;
import io.github.dug22.carpentry.io.csv.CsvReadingProperties;
import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherRegistry;
import io.github.dug22.lockspot.cipheralgorithms.impl.*;
import io.github.dug22.lockspot.datacollection.FeatureExtractorUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.github.dug22.lockspot.datacollection.FeatureExtractorUtils.*;

public class DatasetCollectionProcessor {

    private static final CsvReadingProperties csvReadingProperties = new CsvReadingProperties()
            .setMaxColumnCharacterLength(Integer.MAX_VALUE)
            .setSource(new File("D:\\LockSpot\\data-collection\\src\\main\\resources\\50k-plaintext-dataset.csv"))
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
    private static final StringColumn cipherTypeColumn = StringColumn.create("Cipher Type");
    private static final StringColumn ciphertextColumn = StringColumn.create("Ciphertext");
    private static final StringColumn keyColumn = StringColumn.create("Key");
    private static final StringColumn decryptedPlaintextColumn = StringColumn.create("Decrypted Plaintext");
    private static final DoubleColumn indexOfCoincidenceColumn = DoubleColumn.create("Index of Coincidence");
    private static final IntegerColumn hasLetterJColumn = IntegerColumn.create("Has Letter J?");
    private static final IntegerColumn hasDigitsColumn = IntegerColumn.create("Contains Digits?");
    private static final IntegerColumn hasDoubleLettersOrNumbersColumn = IntegerColumn.create("Has Double Letters or Numbers");
    private static final List<DoubleColumn> characterFrequencyProbabilityVectorColumns = FeatureExtractorUtils.createEmptyFrequencyVectorColumns();

    public static void addCipherDetails() {
        AbstractCipher[] ciphers = {
                adfgxCipher,
                affineCipher,
                amscoCipher,
                atbashCipher,
                autokeyCipher,
                baconianCipher,
                beaufortCipher,
                bifidCipher,
                caesarCipher,
                gronsfeldCipher,
                playfairCipher,
                polybiusCipher,
                portacipher,
                railFenceCipher,
                vigenereCipher
        };

        int counter = 0;
        int cipherCount = ciphers.length;
        for (String originalText : dataFrame.stringColumn("Original Text").getValues()) {
            AbstractCipher cipher = ciphers[counter % cipherCount];
            retrieveCipherDetails(cipher, originalText);

            if (counter % 1000 == 0) {
                System.out.println("Encountered " + counter + " entries so far!");
            }
            counter++;
        }

        dataFrame.addColumns(
                cipherAlgorithmNameColumn,
                cipherAlgorithmIDColumn,
                cipherTypeColumn,
                ciphertextColumn,
                decryptedPlaintextColumn,
                keyColumn,
                indexOfCoincidenceColumn,
                hasLetterJColumn,
                hasDigitsColumn,
                hasDoubleLettersOrNumbersColumn
        );

        for (DoubleColumn column : characterFrequencyProbabilityVectorColumns) {
            dataFrame.addColumn(column);
        }
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
        cipherTypeColumn.append(cipherAlgorithm.cipherType());
        ciphertextColumn.append(ciphertext);
        decryptedPlaintextColumn.append(decryptedPlaintext);
        keyColumn.append(key);
        indexOfCoincidenceColumn.append(findIndexOfCoincidence(ciphertext));
        hasLetterJColumn.append(hasLetterJ(ciphertext));
        hasDigitsColumn.append(hasDigits(ciphertext));
        hasDoubleLettersOrNumbersColumn.append(hasDoubleLettersOrNumbers(ciphertext));
        appendFrequencyVectorToColumns(ciphertext, characterFrequencyProbabilityVectorColumns);
    }


    public static void main(String[] args) {
        dataFrame.info();
        addCipherDetails();
        dataFrame.head();
        dataFrame.write().toCsv(new File("D:\\LockSpot\\data-collection\\src\\main\\resources\\lockspot-50k-ciphertext-dataset.csv"));
        System.out.println("We have successfully saved the given dataset!");
    }
}
