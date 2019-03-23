package net.thumbtack.school.windows.v4.base;

public class WindowException extends Exception {
    private WindowErrorCode errorCode;

    public WindowException(WindowErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public WindowErrorCode getWindowErrorCode() {
        return errorCode;
    }


}
