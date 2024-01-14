package org.ammonium.linkit.model.http;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of the HTTP request.
 */
public final class QueryResult {

    private final List<Object> errors;
    private final List<Object> messages;
    private final List<Object> results;

    public QueryResult() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public QueryResult(List<Object> errors, List<Object> messages, List<Object> results) {
        this.errors = errors;
        this.messages = messages;
        this.results = results;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public List<Object> getResults() {
        return results;
    }
}
