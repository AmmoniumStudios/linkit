package org.ammonium.linkit.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.ammonium.linkit.model.http.ShortUrl;
import org.ammonium.linkit.util.json.Body;

/**
 * Utility for HTTP requests.
 */
public final class HttpUtil {

    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static final Logger LOGGER = Logger.getLogger("HttpUtil");

    private static final String CLOUDFLARE_API = "https://api.cloudflare.com/client/v4/accounts/{account_identifier}/d1/database/{database_identifier}/query"
        .replace("{account_identifier}", System.getenv("CLOUDFLARE_ACCOUNT_IDENTIFIER"))
        .replace("{database_identifier}", System.getenv("CLOUDFLARE_DATABASE_IDENTIFIER"));

    private static final MediaType APPLICATION_JSON = MediaType.get("application/json");
    private static final Headers HEADERS = Headers.of(
        "Authorization", "Bearer %s".formatted(System.getenv("CLOUDFLARE_BEARER_TOKEN"))
    );

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static String shortenUrl(Body json) {
        final RequestBody body = RequestBody.create(GSON.toJson(json), APPLICATION_JSON);
        final Request request = new Request.Builder()
            .url(CLOUDFLARE_API)
            .headers(HEADERS)
            .post(body)
            .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (response.body() == null) {
                throw new IllegalStateException("Response body is null");
            }
            // TODO: Read response and parse to JSON object
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error while shortening URL", e.getMessage());
        }

        return null;
    }

    private HttpUtil() {
        // Prevent instantiation
    }

}
