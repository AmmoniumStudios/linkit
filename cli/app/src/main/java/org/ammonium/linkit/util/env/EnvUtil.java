package org.ammonium.linkit.util.env;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EnvUtil {

    private static final Logger LOGGER = Logger.getLogger("EnvUtil");

    private static final Path linkitDirectory = Path.of(System.getProperty("user.home") + "/.linkit");
    private static final Path filePath = Paths.get(linkitDirectory.toString(), "env.yaml");

    private EnvUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean checkFiles() {

        if (!Files.exists(filePath)) {
            System.out.println("No env.yaml file found, creating one for you");
            System.out.println("Please enter your details, using the provided example file");

            try {
                Files.createDirectories(linkitDirectory);
                Files.createFile(filePath);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to create YAML file.", e);
            }

            return false;
        }
        return true;
    }

    public static Map<String, String> readYamlFile() {

        Map<String, String> env = new HashMap<>();

        try (InputStreamReader is = new InputStreamReader(new FileInputStream(filePath.toString()))) {

            Yaml yaml = new Yaml();
            env.putAll(yaml.load(is));

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found. Please create file 'env.yaml'", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problem reading YAML file. Check stacktrace", e.getMessage());
        }

        return env;
    }
}