package net.thumbtack.school.concert.server;

import com.google.gson.Gson;
import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.server.model.AddSongRequest;
import net.thumbtack.school.concert.server.model.RegisterUserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {
    private List<User> userList;
    private List<Song> songList;
    private boolean isStarted;
    private boolean isUserRegistred;
    private boolean tokenIsValid;
    private static Gson gson = new Gson();

    //Производит всю необходимую инициализацию и запускает сервер.
    //savedDataFileName - имя файла, в котором было сохранено состояние сервера.  Если savedDataFileName == null,
    // восстановление состояния не производится, сервер стартует “с нуля”.
    public void startServer(String savedDataFileName) throws Exception {
        if (savedDataFileName == null) {
            userList = new ArrayList<>();
            songList = new ArrayList<>();
        }
        //вот тут выдаст ошибку, ...как поставить заглушку вместо файла savedDataFileName
        //JsonReader jsonReader = new JsonReader(new FileReader(new File(savedDataFileName)));


        isStarted = true;
    }

    //Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName.
    // Если savedDataFileName == null, запись содержимого не производится.
    public void stopServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            Gson gson = new Gson();
            gson.toJson(userList);
            gson.toJson(songList);
        }
        isStarted = false;
    }

    /**
     * @param requestJsonString Все данные для запроса должны быть упакованы в json-строку
     * @return json-строку с результатом выполнения операции.
     */
    public String registerUser(String requestJsonString) throws Exception {

        if (!isStarted) {
            return "{\"errorCode:\"Server is not started\"}";
        }
        //parse JSON and get:
        User user = gson.fromJson(requestJsonString, User.class);
        if (user.getLogin().isEmpty()) {
            return "{\"errorCode:\" empty login}";
        }
        if (user.getPassword().isEmpty()) {
            return "{\"errorCode:\" empty password}";
        }
        user.setToken(UUID.randomUUID().toString());
        tokenIsValid = true;
        userList.add(user);
        isUserRegistred = true;

        RegisterUserResponse response = new RegisterUserResponse();
        response.setTokenId(user.getToken());
        return gson.toJson(response);
    }

    public String addSong(String requestJsonString) throws ServerException {
        if (!isUserRegistred) {
            return "{\"errorCode:\"User is not registered\"}";
        }
        AddSongRequest request = gson.fromJson(requestJsonString, AddSongRequest.class);
//        if (verifyToken(requestJsonString)) {
//            return "{\"errorCode:\" tokenId is INVALID}";
//        }
//        for (User userItem : userList) {//правильно ли пробегать по всем пользователям?
//            if (userItem.getToken() == null) {
//                return "{\"errorCode:\" tokenId is null}";
//            }
//        }
        songList.addAll(request.getSongs());
        //Метод при успешном выполнении возвращает пустной json(условие)
        return "{}";
    }

    public void logOut(String requestJsonString) {
        //убираем началюную и конечную кавычки, как из Жсона в строку перевести аргумент не знаю, выдаёт ошибку
        String tokenId = requestJsonString.substring(1, requestJsonString.length() - 1);
        for (User userItem : userList) {
            if (tokenId.equals(userItem.getToken())) {
                userItem.setToken(null);
                break;
            }
        }
    }

    private boolean verifyToken(String json) {
//        Song song = gson.fromJson(json, Song.class);
//        String tokenId = song.getTokenId();
//        for (User userItem : userList) {
//            if (userItem.getToken().equals(tokenId)) {
//                return true;
//            }
//        }
//        return false;
        return true;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
