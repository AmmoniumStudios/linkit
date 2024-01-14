package org.ammonium.linkit.util.env;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EnvUtil {

    private static final Logger LOGGER = Logger.getLogger("EnvUtil");

    private EnvUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> readYamlFile() {

        Map<String, String> env = new HashMap<>();
        String path = System.getProperty("user.home") + "/.linkit/env.yaml";

        try (InputStreamReader is = new InputStreamReader(new FileInputStream(path))) {

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