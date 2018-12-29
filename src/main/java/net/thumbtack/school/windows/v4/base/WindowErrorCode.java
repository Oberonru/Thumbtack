package net.thumbtack.school.windows.v4.base;

public enum WindowErrorCode {
    WRONG_STATE(""),
    WRONG_INDEX("Передан недопустимый индекс для массива строк."),
    EMPTY_ARRAY("Массив строк равен null.");
    private String errorString;

    WindowErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
