package org.ammonium.linkit.model.http;

public final class ShortUrl {

    private final String endPoint;
    private final String url;

    public ShortUrl(String endPoint, String url) {
        this.endPoint = endPoint;
        this.url = url;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
            "endPoint='" + endPoint + '\'' +
            ", url='" + url + '\'' +
            '}';
    }
}
