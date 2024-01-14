package org.ammonium.linkit.model.http;

import com.google.gson.annotations.SerializedName;

public class ShortUrlData {

    private final int id;
    @SerializedName("short")
    private String shortUrl;
    private final String url;

    public ShortUrlData(int id, String shortUrl, String url) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n" +
            "shortUrl = " + shortUrl + "\n" +
            "url = " + url + "\n";
    }
}
