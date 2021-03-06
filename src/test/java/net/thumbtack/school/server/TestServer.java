package net.thumbtack.school.server;

import net.thumbtack.school.server.dao.RatingDaoImpl;
import net.thumbtack.school.server.dao.SongDaoImpl;
import net.thumbtack.school.server.dao.UserDaoImpl;
import net.thumbtack.school.server.database.DataBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestServer {

    private Server server;

    @Before
    public void setupServer() {
        server = new Server();
    }

    @Test
    public void test_startServer() {
        UserDaoImpl userDao = new UserDaoImpl();
        SongDaoImpl songDao = new SongDaoImpl();
        RatingDaoImpl ratingDao = new RatingDaoImpl();
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        Assert.assertEquals(userDao.getUserList().size(), 1);
        Assert.assertEquals(songDao.getSongList().size(), 2);
        Assert.assertEquals(userDao.getUserList().get(0).getFirstName(), "Uasya");
        Assert.assertEquals(songDao.getSongList().get(1).getSongName(), "second");
        Assert.assertEquals(ratingDao.getRatingList().get(0).getLogin(), "uaSek");
        Assert.assertEquals(ratingDao.getRatingList().get(0).getSongId(), 2);
    }

    @Test
    public void test_startServer_fileNull() {
        server.startServer(null);
        server.stopServer("src/test/java/net/thumbtack/school/server/data/saveTestServerData.json");
    }

    @Test
    public void test_startServer_fileEmpty() {
        server.startServer("src/test/java/net/thumbtack/school/server/data/emptyFile.json");
        server.stopServer("src/test/java/net/thumbtack/school/server/data/saveTestServerData.json");
    }

    @Test
    public void test_registerUser_serverIs_notStarted() throws Exception {
        Server server = new Server();
        String requestJsonString = "{\"firstName\":\"Boryaha\",\"lastName\":\"Morkovkin\",\"login\":\"boryan\"," +
                "\"password\":\"321\"}";
        Assert.assertEquals(server.registerUser(requestJsonString), "{\"error\":\"Server is not started!\"}");
    }

    @Test
    public void test_registerUser() throws Exception {
        UserDaoImpl userDao = new UserDaoImpl();
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String registerUserJson = "{\"firstName\":\"Petro\",\"lastName\":\"First\",\"login\":\"petrucsho\"," +
                "\"password\":\"3432s3s\"}";
        System.out.println(server.registerUser(registerUserJson));
        Assert.assertEquals(userDao.getUserList().size(), 2);
        Assert.assertEquals("Petro", userDao.getUserList().get(1).getFirstName());
        server.stopServer("src/test/java/net/thumbtack/school/server/data/saveTestServerData.json");
    }

    @Test
    public void test_registerUser_name_notValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"firstName\":\"B\",\"lastName\":\"Morkovkin\",\"login\":\"boryan\"," +
                "\"password\":\"321\"}";
        Assert.assertEquals("{\"error\":\"Params is not valid\"}", server.registerUser(requestJsonString));
    }

    @Test
    public void test_registerUser_twoEqualsLogin() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"firstName\":\"Boryaha\",\"lastName\":\"Morkovkin\",\"login\":\"gamer\"," +
                "\"password\":\"321\"}";
        server.registerUser(requestJsonString);
        String registerJsonString2 = "{\"firstName\":\"Petro\",\"lastName\":\"First\",\"login\":\"gamer\"," +
                "\"password\":\"3432s3s\"}";

        Assert.assertEquals("{\"error\":\"Login is already used\"}", server.registerUser(registerJsonString2));
    }

    @Test
    public void test_logIn() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"login\" : \"uaSek\", \"password\" : \"123s\"}";
        Assert.assertEquals("\"{}\"", server.logIn(requestJsonString));
    }

    @Test
    public void test_logIn_loginNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"login\" : \"figZnaetHto\", \"password\" : \"undefined\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.logIn(requestJsonString));
    }

    @Test
    public void test_logIn_passwordNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"login\" : \"uaSek\", \"password\" : \"321\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.logIn(requestJsonString));
    }

    @Test
    public void test_logIn_dataNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"firstName\":\"Boryaha\",\"lastName\":\"Morkovkin\",\"log\":\"gamer\"," +
                "\"password\":\"321\"}";
        Assert.assertEquals("{\"error\":\"Data is not valid\"}", server.logIn(requestJsonString));
    }

    @Test
    public void test_logOut() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("\"{}\"", server.logOut(requestJsonString));
        server.stopServer("src/test/java/net/thumbtack/school/server/data/saveTestServerData.json");
    }

    @Test
    public void test_logOut_twice() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("\"{}\"", server.logOut(requestJsonString));
        Assert.assertEquals("{\"error\":\"User not found\"}", server.logOut(requestJsonString));
    }

    @Test
    public void test_logOut_dataNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"songName\" : \"Musjaka\", \"composer\" : [\"Muzyak\", \"MuzyakMladshoi\"]," +
                " \"author\" : [\"OnYe\"], \"musician\" : \"MusyagGroup\",  \"songDuration\" : 0.8," +
                " \"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("{\"error\":\"Data is not valid\"}", server.logOut(requestJsonString));
    }

    @Test
    public void test_logOut_tokenNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"token\" : \"Krakazyabr1111111\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.logOut(requestJsonString));
    }

    @Test
    public void test_addSong() throws Exception {
        DataBase db = DataBase.getInstance();
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestLoginJsonString = "{\"login\" : \"uaSek\", \"password\" : \"123s\" }";
        server.logIn(requestLoginJsonString);
        String requestJsonString = "{\"songName\" : \"Musjaka\", \"composer\" : [\"Muzyak\", \"MuzyakMladshoi\"]," +
                " \"author\" : [\"OnYe\"], \"musician\" : \"MusyagGroup\",  \"songDuration\" : 0.8," +
                " \"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals(db.getSongList().size(), 2);
        Assert.assertEquals("\"{}\"", server.addSong(requestJsonString));
        Assert.assertEquals(db.getSongList().size(), 3);
        server.stopServer("saveTestServerData.json");
    }

    @Test
    public void test_addSong_twice() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestLoginJsonString = "{\"login\" : \"uaSek\", \"password\" : \"123s\" }";
        server.logIn(requestLoginJsonString);
        String requestJsonString = "{\"songName\" : \"MUSJaka\", \"composer\" : [\"Muzyak\", \"MuzyakMladshoi\"]," +
                " \"author\" : [\"OnYe\"], \"musician\" : \"MusyagGroup\",  \"songDuration\" : 0.8," +
                " \"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("\"{}\"", server.addSong(requestJsonString));
        Assert.assertEquals("{\"error\":\"Such a song already exsists\"}", server.addSong(requestJsonString));
    }

    @Test
    public void test_addSong_notLogined() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"songName\" : \"TucMuc\", \"composer\" : [\"Obdolbish\", \"Ukurish\"]," +
                " \"author\" : [\"Bomj\", \"Bezdar'\"], \"musician\" : \"Saruhanov\",  \"songDuration\" : 3.8," +
                " \"token\" : \"null\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.addSong(requestJsonString));
    }

    @Test
    public void test_addSong_loginIsFailure() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"songName\" : \"TucMuc\", \"composer\" : [\"Obdolbish\", \"Ukurish\"]," +
                " \"author\" : [\"Bomj\", \"Bezdar'\"], \"musician\" : \"Saruhanov\",  \"songDuration\" : 3.8," +
                " \"token\" : \"Fatal_ERROR\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.addSong(requestJsonString));
    }

    @Test
    public void test_addSong_logOut() throws Exception {
        SongDaoImpl songDao = new SongDaoImpl();
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestLoginJsonString = "{\"login\" : \"uaSek\", \"password\" : \"123s\" }";
        server.logIn(requestLoginJsonString);
        String requestJsonString = "{\"songName\" : \"Musjaka\", \"composer\" : [\"Muzyak\", \"MuzyakMladshoi\"]," +
                " \"author\" : [\"OnYe\"], \"musician\" : \"MusyagGroup\",  \"songDuration\" : 0.8," +
                " \"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals(songDao.getSongList().size(), 2);
        server.addSong(requestJsonString);
        Assert.assertEquals(songDao.getSongList().size(), 3);
        String logOutJsonString = "{\"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("\"{}\"", server.logOut(logOutJsonString));
        String requestJsonString2 = "{\"songName\" : \"Musjaka\", \"composer\" : [\"Muzyak\", \"MuzyakMladshoi\"]," +
                " \"author\" : [\"OnYe\"], \"musician\" : \"MusyagGroup\",  \"songDuration\" : 0.8," +
                " \"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("{\"error\":\"User not found\"}", server.addSong(requestJsonString2));
    }

    @Test
    public void test_addSong_dataNotValid() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String registerUserJson = "{\"firstName\":\"Petro\",\"lastName\":\"First\",\"login\":\"petrucsho\"}";
        Assert.assertEquals("{\"error\":\"Data is not valid\"}", server.addSong(registerUserJson));
    }

    @Test
    public void tes_exitToServer() throws Exception {
        server.startServer("src/test/java/net/thumbtack/school/server/data/testServerData.json");
        String requestJsonString = "{\"token\" : \"a6acedd8-213f-4018-b61f-d4b1a0a78418\"}";
        Assert.assertEquals("\"{}\"", server.exitToServer(requestJsonString));
        server.stopServer("saveTestServerData.json");
    }
}
