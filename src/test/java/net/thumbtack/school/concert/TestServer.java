package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.server.Server;
import net.thumbtack.school.concert.server.Song;
import net.thumbtack.school.concert.server.model.AddSongRequest;
import net.thumbtack.school.concert.server.model.AuthenticationData;
import net.thumbtack.school.concert.server.model.RegisterUserResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class TestServer {
    private static Gson gson = new Gson();
    private final String USER_JSON = "{\"login\":\"Vasya\", \"password\":\"1234\"}"; //TODO: move to different .json file
    private final String USER_JSON_WITH_EMPTY_LOGIN = "{\"login\":\"\", \"password\":\"1234\"}";
    private final String USER_JSON_WITH_EMPTY_PASSWORD = "{\"login\":\"Baurjan\", \"password\":\"\"}";


    @Test
    public void testRegisterUser() {
        Server server = new Server();
    }

    @Test
    public void testRegisterUser_serverIsNotStarted() throws Exception {
        Server server = new Server();
        String errorJson = server.registerUser(USER_JSON);
        Assert.assertEquals(errorJson, "{\"errorCode:\"Server is not started\"}");
    }

    @Test
    public void testRegisterUser_validUser() throws Exception {
        Server server = new Server();
        server.startServer(null);
        String tokenJson = server.registerUser(USER_JSON);
        Assert.assertNotNull(tokenJson); //TODO: parse tokenJson and check that tokenId field is not null
    }
//Тест брата, ....почему нормально должно проходить? по идее если логин не узазан должна быть жсон с ошибкой?...ниже я это попробую реализовать
//    @Test
//    public  void testRegisterUser_invalidUser() throws Exception {
//        Server server = new Server();
//        server.startServer(null);
//        String tokenJson = server.registerUser(USER_JSON_WITH_EMPTY_LOGIN);
//        Assert.assertNotNull(tokenJson); //TODO: parse tokenJson and check that tokenId field is not null
//    }

    @Test
    public void testRegisterUser_emptyLogin() throws Exception {
        Server server = new Server();
        server.startServer(null);
        String actual = server.registerUser(USER_JSON_WITH_EMPTY_LOGIN);
        Assert.assertEquals("{\"errorCode:\" empty login}", actual);
    }

    @Test
    public void testRegisterUser_emptyPassword() throws Exception {
        Server server = new Server();
        server.startServer(null);
        String actual = server.registerUser(USER_JSON_WITH_EMPTY_PASSWORD);
        Assert.assertEquals("{\"errorCode:\" empty password}", actual);
    }

    @Test
    public void testAddSong() throws Exception {
        Server server = new Server();
        server.startServer(null);
        String responseString = server.registerUser(USER_JSON);
        RegisterUserResponse registerResponse = gson.fromJson(responseString, RegisterUserResponse.class);
        String tokenId = registerResponse.getTokenId();

        String[] musicians = {"Musician 1", "Musician 2"};
        String[] authors = {"Author"};
        Song song1 = new Song("Name", musicians, authors, "Singer", 3.2);
        Song song2 = new Song("Different Name", musicians, authors, "Same Singer", 2.2);
        List<Song> songs = new ArrayList();
        songs.add(song1);
        songs.add(song2);
        AddSongRequest requestObject = new AddSongRequest();
        requestObject.setTokenId(tokenId);
        requestObject.setSongs(songs);
        String addSongResponseString = server.addSong(gson.toJson(requestObject, AddSongRequest.class));

        Assert.assertEquals(addSongResponseString, "{}");

        List<Song> songList = server.getSongList();
        Assert.assertEquals(songList.size(), 2);

        Assert.assertEquals(songList.get(0).getName(), songs.get(0).getName());
        Assert.assertArrayEquals(songList.get(0).getMusician(), songs.get(0).getMusician());
        Assert.assertArrayEquals(songList.get(0).getAuthor(), songs.get(0).getAuthor());
        Assert.assertArrayEquals(songList.get(0).getAuthor(), songs.get(0).getAuthor());
        Assert.assertEquals(songList.get(0).getSinger(), songs.get(0).getSinger());
        Assert.assertEquals(songList.get(0).getTime(), songs.get(0).getTime(), 0);

        Assert.assertEquals(songList.get(1).getName(), songs.get(1).getName());
        Assert.assertArrayEquals(songList.get(1).getMusician(), songs.get(1).getMusician());
        Assert.assertArrayEquals(songList.get(1).getAuthor(), songs.get(1).getAuthor());
        Assert.assertArrayEquals(songList.get(1).getAuthor(), songs.get(1).getAuthor());
        Assert.assertEquals(songList.get(1).getSinger(), songs.get(1).getSinger());
        Assert.assertEquals(songList.get(1).getTime(), songs.get(1).getTime(), 0);
    }

//    @Test
//    public void testLogOut() throws Exception {
//        Server server = new Server();
//        server.startServer(null);
//        String tokenJson = server.registerUser(USER_JSON);
//        Song song = new Song("Ramambaharamamburum", "Burum", "Lohmatii", "Lisii", 3.2);
//        song.setTokenId(tokenJson);
//        server.logOut(tokenJson);
//        System.out.println(song.getTokenId());
//        String jsonSong = gson.toJson(song, Song.class);
//        server.addSong(jsonSong);
//
//    }

//    @Test
//    public void testAddSong_logOut() throws Exception {
//        Server server = new Server();
//        server.startServer(null);
//        String response = server.registerUser(USER_JSON);
//        Song song = new Song("Ramambaharamamburum", "Burum", "Lohmatii", "Lisii", 3.2);
//        song.setTokenId(response);
//        String jsonSong = gson.toJson(song, Song.class);
//        server.logOut(response);
//        String actual = server.addSong(jsonSong);
//        Assert.assertEquals("{\"errorCode:\" tokenId is null}", actual);
//    }
}
