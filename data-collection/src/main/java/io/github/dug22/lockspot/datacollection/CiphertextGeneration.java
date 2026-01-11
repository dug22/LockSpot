package io.github.dug22.lockspot.datacollection;

import io.github.dug22.carpentry.DataFrame;
import io.github.dug22.carpentry.column.impl.StringColumn;
import io.github.dug22.lockspot.cipheralgorithms.AbstractCipher;
import io.github.dug22.lockspot.cipheralgorithms.CipherRegistry;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CiphertextGeneration {

    private final static DataFrame dataFrame = DataFrame.create();
    private final static StringColumn cipherAlgorithmColumn = StringColumn.create("Cipher Algorithm");
    private final static StringColumn encryptedTextColumn = StringColumn.create("Encrypted Text");
    private final static StringColumn cipherTypeColumn = StringColumn.create("Cipher Type");
    private final static Map<String, AbstractCipher> cipherAlgorithsMap = CipherRegistry.getCipherAlgorithmsMap();

    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Java twenty five has entered its ramp down phase introducing enhanced performance and refined pattern matching for switch statements to streamline enterprise level backend development",
                "Python three point fifteen introduces a revolutionary no gil production ready mode allowing developers to achieve true multi core parallel processing without traditional threading bottlenecks",
                "The TIOBE Index for twenty twenty six reports that Python remains the most popular language globally though Mojo and Rust are rapidly gaining ground in the AI and systems sectors",
                "Oracle announced a new long term support roadmap for Java ensuring that legacy banking systems can safely migrate to cloud native architectures while maintaining high security standards",
                "A major breakthrough in Python C API has reduced overhead for machine learning libraries like PyTorch resulting in a twenty percent speed increase for LLM training on consumer hardware",
                "JetBrains released a study showing that seventy percent of Java developers have integrated AI pair programmers into their workflow significantly reducing boilerplate code in Spring Boot",
                "The Python Software Foundation secured new funding to improve the packaging ecosystem aiming to solve long standing dependency resolution issues with a unified toolset for all users",
                "Java Project Panama has finally reached a stable release allowing developers to easily call native libraries without the complexity of JNI opening new doors for high performance apps",
                "Cybersecurity experts are urging Python developers to audit their PyPI dependencies following a rise in automated typosquatting attacks targeting popular data science packages worldwide",
                "Google has officially shifted its Android internal development guidelines to favor Kotlin over Java though legacy support for JDK remains a core priority for the mobile ecosystem",
                "The rise of WebAssembly is allowing Python to run at near native speeds in the browser potentially challenging JavaScript for dominance in front end data visualization tools today",
                "New Java based microservices frameworks are trending toward native compilation via GraalVM reducing cold start times to milliseconds for serverless cloud deployments in the future",
                "Python Type Hinting system has evolved in twenty twenty six to include a Strict Mode giving developers the safety of a statically typed language while keeping the flexibility of a script",
                "The Jakarta EE community has released version eleven focusing on alignment with Java twenty one features and providing a modular approach to building scalable enterprise applications",
                "Microsoft latest VS Code update introduces deep integration for Python Ruff linter making it the fastest environment for real time code analysis and formatting in the world today"
        );

        int count = 0;
       for(AbstractCipher cipher : cipherAlgorithsMap.values()){
           String sentence = sentences.get(count);
           sentence = sentence.toUpperCase();
           sentence = sentence.replaceAll("[^A-Z0-9]", "");
           cipherAlgorithmColumn.append(cipher.name());
           encryptedTextColumn.append(cipher.encrypt(sentence, cipher.generateRandomKey()));
           cipherTypeColumn.append(cipher.cipherType());
           count++;
       }

       dataFrame.addColumns(cipherAlgorithmColumn, encryptedTextColumn, cipherTypeColumn);
       dataFrame.write().toCsv(new File("D:\\LockSpot\\data-collection\\src\\main\\resources\\ciphertext-samples.csv"));
    }
}
