package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.server.Error;
import net.thumbtack.school.concert.server.ServerException;
import net.thumbtack.school.concert.server.song.Song;


import java.util.List;

public class User {
    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private String token;

    public User() {
    }

    public User(String login, String password) throws  Exception{
        setLogin(login);
        setPassword(password);
    }

    public User(String lastName, String firstName, String login, String password) throws Exception {
        this.lastName = lastName;
        this.firstName = firstName;
        setLogin(login);
        setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (login.length() < 4 || login.length() > 128 || login == null) {
            throw new ServerException(Error.LGIN_IS_ERROR);
        }
        this.login = login;
    }

    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }

    public void setPassword(String password) throws Exception {
        if (password.length() < 3) {
            throw new ServerException(Error.PASSWORD_IS_SHORT);
        }
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
