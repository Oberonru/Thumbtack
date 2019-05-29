package net.thumbtack.school.concert.server;

import com.google.gson.Gson;

public enum Error {
    TOKEN_INVALID("Токен недействителен"),
    PERSOU_REGISTER_INVALID("Невозможно зарегистрировать пользователя"),
    PERSON_IS_NOTREGISTREED("Пользователь не зарегистроирован"),
    SERVER_IS_STARTED("Сервер уже запущен"),
    SERVER_IS_NOTSTARTED("Сервер не запущен"),
    LGIN_IS_ERROR("Ошибка при указании логина"),
    PASSWORD_IS_ERROR("Неверный пароль"),
    PASSWORD_IS_SHORT("Пароль содержит менее трёх символов"),
    RATING_IS_ERROR("Ошибка при добавлении рейтинга");

    private String Error;
    private Gson gson = new Gson();

    Error(String jsonError) {
        setJsonString(jsonError);
    }

    public String setJsonString(String Eror) {
        return gson.toJson(Eror);
    }

    public String getError() {
        return Error;
    }
}
