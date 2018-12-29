package net.thumbtack.school.windows.v4.base;

public class WindowException extends Exception {
    private WindowErrorCode errorCode;

    WindowException(WindowErrorCode errorCode) {
         super(errorCode.getErrorString());
    }

    public WindowErrorCode getWindowErrorCode() {
        return errorCode;
    }


}
