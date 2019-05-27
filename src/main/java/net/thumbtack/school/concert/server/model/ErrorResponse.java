package net.thumbtack.school.concert.server.model;

public class ErrorResponse {
    public ErrorResponse(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
