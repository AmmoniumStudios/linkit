package org.ammonium.linkit;

import org.ammonium.linkit.cli.Linkit;

import org.ammonium.linkit.model.http.ResponseWrapper;
import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.SchemaUtils;
import org.ammonium.linkit.util.env.EnvUtil;
import org.ammonium.linkit.util.json.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.InputStream;
import java.util.List;

public final class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        if (!EnvUtil.checkFiles()) {
            return;
        }

        try (InputStream inputStream = Application.class.getResourceAsStream("/schema/schema.sql")) {
            List<String> schema = SchemaUtils.createSchema(inputStream);
            for (String query : schema) {
                HttpUtil.createShort(new Body(query, null)).thenAccept(response -> {
                    ResponseWrapper wrapper = HttpUtil.GSON.fromJson(response.body(), ResponseWrapper.class);
                    if (wrapper.isSuccess()) {
                        LOGGER.info("Successfully created tables");
                    } else {
                        LOGGER.error("Failed to create table");
                    }
                });
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create tables {}", e.getMessage());
        }

        int exitCode = new CommandLine(new Linkit()).execute(args);
        System.exit(exitCode);
    }
}
