package net.thumbtack.school.concert.server.model;

public class VerifyTokenResponse {
    public VerifyTokenResponse(boolean success) {
        this.success = success;
    }

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
