package org.ammonium.linkit.model.http;

import java.util.List;

public class DataWrapper {

    private List<ShortUrlData> results;
    private boolean success;
    private Meta meta;

    public List<ShortUrlData> getResults() {
        return results;
    }

    public void setResults(List<ShortUrlData> results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
