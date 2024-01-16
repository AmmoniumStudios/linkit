package org.ammonium.linkit.model.http;

import java.util.List;

public class ResponseWrapper {

    private List<DataWrapper> result;
    private List<Error> errors;
    private List<Object> messages;
    private boolean success;

    public List<DataWrapper> getResult() {
        return result;
    }

    public void setResult(List<DataWrapper> result) {
        this.result = result;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
