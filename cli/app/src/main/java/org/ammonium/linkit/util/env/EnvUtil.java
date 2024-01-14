package org.ammonium.linkit.util.env;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class EnvUtil {

    private EnvUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> readYamlFile() {

        Map<String, String> env = new HashMap<>();
        String path = System.getProperty("user.home") + "/.linkit/env.yaml";

        try (InputStreamReader is = new InputStreamReader(new FileInputStream(path))) {

            Yaml yaml = new Yaml();
            env.putAll(yaml.load(is));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return env;
    }
}