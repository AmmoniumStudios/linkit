package org.ammonium.linkit.util;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.ammonium.linkit.model.http.ShortUrl;

/**
 * Utility for HTTP requests.
 */
public final class HttpUtil {

    private static final String CLOUDFLARE_API = "https://api.cloudflare.com/client/v4/accounts/{account_identifier}/d1/database/{database_identifier}/query"
        .replace("{account_identifier}", System.getenv("CLOUDFLARE_ACCOUNT_IDENTIFIER"))
        .replace("{database_identifier}", System.getenv("CLOUDFLARE_DATABASE_IDENTIFIER"));

    private static final MediaType APPLICATION_JSON = MediaType.get("application/json");
    private static final Headers HEADERS = Headers.of(
        "Authorization", "Bearer %s".formatted(System.getenv("CLOUDFLARE_BEARER_TOKEN"))
    );

    public static ShortUrl shortenUrl(String json) {
        final RequestBody body = RequestBody.create(json, APPLICATION_JSON);
        final Request request = new Request.Builder()
            .url(CLOUDFLARE_API)
            .headers(HEADERS)
            .post(body)
            .build();

        return null;
    }

    private HttpUtil() {
        // Prevent instantiation
    }

}
