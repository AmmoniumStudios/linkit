package org.ammonium.linkit.util.json;

import java.util.List;

public class Body {

    private final String[] params;
    private final String sql;

    public Body(String query, String[] params) {
        this.params = params;
        this.sql = query;
    }

    public String[] getParameters() {
        return params;
    }

    public String getQuery() {
        return sql;
    }
}
