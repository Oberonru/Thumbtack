package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.server.Server;
import net.thumbtack.school.concert.server.model.AddRatingRequest;
import net.thumbtack.school.concert.server.song.Song;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRate {

    private static Gson gson = new Gson();
    private final String USER_JSON = "{\"login\":\"Vasya\", \"password\":\"1234\"}";

    @Test
    public void testAddRating() {
        Server server = new Server();
        server.startServer(null);
        String userTokenId = server.registerUser(USER_JSON);

        //теперь создаём песню и добавляем ёё в список песен, затем можно будет добавлять рейтинг песни
        String[] musicians = {"Musician 1", "Musician 2"};
        String[] authors = {"Author"};
        Song song1 = new Song("Name", musicians, authors, "Singer", 3.2);
        Song song2 = new Song("Different Name", musicians, authors, "Same Singer", 2.2);
        List<Song> songsList = new ArrayList<>();
        songsList.add(song1);
        songsList.add(song2);
        AddRatingRequest addRatingRequest = new AddRatingRequest();
        Assert.assertEquals(songsList.get(0).getSongId(), addRatingRequest.getSongId());


    }
}
