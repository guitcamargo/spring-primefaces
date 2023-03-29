package br.com.guitcamargo.springprimefaces.infra.error.resource;

import java.util.LinkedList;
import java.util.List;

public class ErrorMessageWrapper {

    private List<ErrorMessage> messages;

    public ErrorMessageWrapper() {
        messages = new LinkedList<>();
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public void addErrorMessage(String errorMessage, Integer errorCode) {
        messages.add(new ErrorMessage(errorMessage, errorCode));
    }
}
