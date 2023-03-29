package br.com.guitcamargo.springprimefaces.infra.error.resource;

public class ErrorMessage {

    private String message;
    private Integer code;

    public ErrorMessage(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {

        return String.format("ErrorMessage{code=%s,message='%s'}", this.getCode(), this.getMessage());
    }

}
