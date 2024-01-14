package org.ammonium.linkit.util.json;

import java.util.List;

public class Body {

    private final List<Param> parameters;
    private final String query;

    public Body(List<Param> parameters, String query) {
        this.parameters = parameters;
        this.query = query;
    }

    public List<Param> getParameters() {
        return parameters;
    }



    public record Param(String name, String value) {

    }

}
