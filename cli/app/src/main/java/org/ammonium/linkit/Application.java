package org.ammonium.linkit;

import org.ammonium.linkit.cli.Linkit;

import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.SchemaUtils;
import org.ammonium.linkit.util.env.EnvUtil;
import org.ammonium.linkit.util.json.Body;
import picocli.CommandLine;

import java.io.InputStream;
import java.util.List;

public final class Application {

    public static void main(String[] args) {

        if (!EnvUtil.checkFiles()) {
            return;
        }

        try (InputStream inputStream = Application.class.getResourceAsStream("/schema/schema.sql")) {
            List<String> schema = SchemaUtils.createSchema(inputStream);
            for (String query : schema) {
                HttpUtil.createShort(new Body(query, null)).thenAccept(response -> {
                    // TODO: Handle response
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int exitCode = new CommandLine(new Linkit()).execute(args);
        System.exit(exitCode);
    }
}
