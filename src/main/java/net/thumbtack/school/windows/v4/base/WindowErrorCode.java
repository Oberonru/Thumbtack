package net.thumbtack.school.windows.v4.base;

public enum WindowErrorCode {
    WRONG_STATE("\n" +
            "При создании окна передается WindowState.DESTROYED или null.  При изменении состояния состояние" +
            " устанавливается в null или окно, находящееся в WindowState.DESTROYED, переводится в иное состояние.\n"),
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
