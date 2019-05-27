package net.thumbtack.school.concert;

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
    //список песен каждого пользователя
    private List<Song> personSongList;

    public User() {
    }

    public User(String login, String password) throws  Exception{
        setLogin(login);
        setPassword(password);
    }

    public User(String lastName, String firstName, String login, String password, List<Song> personSongList) throws Exception {
        this.lastName = lastName;
        this.firstName = firstName;
        setLogin(login);
        setPassword(password);
        this.personSongList = personSongList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (login.length() < 4 || login.length() > 128 || login == null) {
            throw new ServerException(Error.LGIN_INVALID);
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (this.password.equals(password)) {
            //так корректно будет?
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
