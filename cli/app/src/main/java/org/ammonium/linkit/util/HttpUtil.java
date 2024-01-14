package org.ammonium.linkit.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ammonium.linkit.model.http.ResponseWrapper;
import org.ammonium.linkit.model.http.ShortUrlData;
import org.ammonium.linkit.util.json.Body;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * Utility for HTTP requests.
 */
public final class HttpUtil {

    private static final Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    private static final Logger LOGGER = Logger.getLogger("HttpUtil");

    private static final String CLOUDFLARE_API = "https://api.cloudflare.com/client/v4/accounts/{account_identifier}/d1/database/{database_identifier}/query"
        .replace("{account_identifier}", "") // TODO: Use account identifier
        .replace("{database_identifier}", ""); // TODO: Use database identifier;

    /**
     * Sends a POST request to the Cloudflare API to create a short URL.
     *
     * @param body The body of the request.
     * @return A {@link CompletableFuture} containing the response.
     */
    public static CompletableFuture<HttpResponse<String>> createShort(Body body) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = startRequest()
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(body)))
                .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        }
    }

    /**
     * Retrieves all short URLs from the Cloudflare API.
     *
     * @param body The body of the request.
     * @return A {@link CompletableFuture} containing the response.
     */
    public static CompletableFuture<List<ShortUrlData>> listAllShorts(Body body) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = startRequest()
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(body)))
                .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> GSON.fromJson(response.body(), ResponseWrapper.class))
                .thenApply(ResponseWrapper::getResult)
                .thenApply(result -> result.getFirst().getResults());
        }
    }

    /**
     * Sends a DELETE request to the Cloudflare API to delete a short URL.
     *
     * @param body The body of the request.
     * @return A {@link CompletableFuture} containing the response.
     */
    public static CompletableFuture<HttpResponse<String>> deleteShort(Body body) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = startRequest()
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(body)))
                .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        }
    }


    /**
     * Starts a request to the Cloudflare API.
     *
     * @return A {@link HttpRequest.Builder} with the base URL and headers.
     */
    private static HttpRequest.Builder startRequest() {
        return HttpRequest.newBuilder(URI.create(CLOUDFLARE_API))
            .headers(
                "Authorization", "Bearer %s".formatted(""), // TODO: Add API key,
                "Content-Type", "application/json"
            );
    }

    private HttpUtil() {
        // Prevent instantiation
    }

}
