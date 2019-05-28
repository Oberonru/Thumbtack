package net.thumbtack.school.concert.server;

import com.google.gson.Gson;
import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.server.model.*;
import net.thumbtack.school.concert.server.song.Rating;
import net.thumbtack.school.concert.server.song.Song;

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
    public void startServer(String savedDataFileName) {
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
    public String registerUser(String requestJsonString)  {

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

    public String addSong(String requestJsonString){
        AddSongRequest request = gson.fromJson(requestJsonString, AddSongRequest.class);
        songList.addAll(request.getSongs());
        return "{}";
    }

    public String addRating(String requestJsonString) throws ServerException {

        AddRatingRequest addRatingRequest = gson.fromJson(requestJsonString, AddRatingRequest.class);
        String tokenId = addRatingRequest.getTokenId();
        Integer rating = addRatingRequest.getRating();
        if (rating < 0 || rating > 5 || rating == null) {
            throw  new ServerException(Error.RATING_IS_ERROR);
        }

        if (!isTokenValid(tokenId)) {
            return gson.toJson(new ServerException(Error.TOKEN_INVALID),ServerException.class);
        }

        for (Song song : songList) {
            if(song.getSongId().equals(addRatingRequest.getSongId())) {
                song.addRating(new Rating(addRatingRequest.getRating(), getUserByToken(tokenId)));
            }
        }
        return "{}";
    }

    public String logIn(String requestJsonString) {
        LogInRequest logInRequest = gson.fromJson(requestJsonString, LogInRequest.class);
        String tokenId = logInRequest.getTokenId();
        for (User userItem : userList) {
            if (userItem.getToken().equals(tokenId)) {
                return "{\"User is loginned\"}";
            }
        }
        return "{\"fig\": user is not loginned}";
    }

    public void logOut(String requestJsonString) {
        LogOutRequest logOutRequest = gson.fromJson(requestJsonString, LogOutRequest.class);
        String tokenId = logOutRequest.getTokenId();
        for (User userItem : userList) {
            if (userItem.getToken().equals(tokenId)) {
                userItem.setToken(null);
                System.out.println("tokenId is null");
                break;
            }
        }
    }

    public String verifyToken(String json) {
        VerifyTokenRequest request = gson.fromJson(json, VerifyTokenRequest.class);
        if (isTokenValid(request.getTokenId())) {
            return gson.toJson(new VerifyTokenResponse(true), VerifyTokenResponse.class);
        }
        return gson.toJson(new VerifyTokenResponse(false), VerifyTokenResponse.class);
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    private boolean isTokenValid(String token) {
        for (User user : userList) {
            String userToken = user.getToken();
            if (userToken != null && userToken.equals(token)) {
                return true;
            }
        }
        return  false;
    }

    private User getUserByToken(String token) {
        for (User user : userList) {
            String userToken = user.getToken();
            if (userToken != null && userToken.equals(token)) {
                return user;
            }
        }
        return null;
    }
}
