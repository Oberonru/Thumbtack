package net.thumbtack.school.concert.server;

public class ServerException extends Exception {
    private Error error;

    public ServerException(Error error) {
       super(error.getError());
    }

    public Error getServerError() {
        return error;
    }
}
