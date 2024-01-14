package org.ammonium.linkit.model.http;

import java.util.List;

public class ResponseWrapper {

    private List<DataWrapper> result;
    private List<Object> errors;
    private List<Object> messages;

    public List<DataWrapper> getResult() {
        return result;
    }

    public void setResult(List<DataWrapper> result) {
        this.result = result;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }
}
