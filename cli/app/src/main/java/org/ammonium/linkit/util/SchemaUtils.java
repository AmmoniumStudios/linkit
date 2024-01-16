package org.ammonium.linkit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating a schema from a given input stream.
 */
public final class SchemaUtils {

    /**
     *  The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger("SchemaUtils");

    /**
     * Creates a list of queries from a given input stream.
     *
     * @param inputStream the input stream to read from
     * @return a list of queries
     */
    public static List<String> createSchema(InputStream inputStream) {
        List<String> queries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder query = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.equals("--")) {
                    continue;
                }
                query.append(line);
                if (line.endsWith(";")) {
                    queries.add(query.toString());
                    query = new StringBuilder();
                }
            }
        } catch (IOException e) {
            LOGGER.error("An error occurred while trying to parse the schema file. {}", e.getMessage());
        }
        return queries;
    }

    private SchemaUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}

