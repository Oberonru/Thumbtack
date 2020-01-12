package net.thumbtack.school.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.school.server.dao.CommentDaoImpl;
import net.thumbtack.school.server.dao.RatingDaoImpl;
import net.thumbtack.school.server.dao.SongDaoImpl;
import net.thumbtack.school.server.model.Comment;
import net.thumbtack.school.server.model.Rating;
import net.thumbtack.school.server.model.Song;
import net.thumbtack.school.server.model.User;
import net.thumbtack.school.server.request.*;
import net.thumbtack.school.server.response.FindSongByAutorDtoResponse;
import net.thumbtack.school.server.response.FindSongByComposersDtoResponse;
import net.thumbtack.school.server.response.FindSongsListDtoResponse;
import net.thumbtack.school.server.response.GetSongsDtoResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongService {

    private SongDaoImpl songDao = new SongDaoImpl();
    private RatingDaoImpl ratingDao = new RatingDaoImpl();
    private CommentDaoImpl commentDao = new CommentDaoImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private UserService userService = new UserService();

    public SongService() {
    }

    public Song createSong(String songName, String[] composer, String[] author, String musician, double songDuration,
                           String token) {
        Song song = new Song();
        song.setSongName(songName);
        song.setComposers(composer);
        song.setAuthor(author);
        song.setMusician(musician);
        song.setSongDuration(songDuration);
        song.setLogin(userService.getUserByToken(token).getLogin());
        song.setSongId(1);
        return song;
    }

    public String addSong(RegisterSongDtoRequest request) throws Exception {
        User user = userService.getUserByToken(request.getToken());

        if (user == null) {
            throw new Exception("User not found");
        }

        if (findSongBySongName(request.getSongName())) {
            throw new Exception("Such a song already exsists");
        }

        Song newSong = createSong(request.getSongName(), request.getComposer(), request.getAuthor(),
                request.getMusician(), request.getSongDuration(), request.getToken());
        generateSongId(newSong);
        songDao.insert(newSong);
        ratingDao.insert(new Rating(user.getLogin(), newSong.getSongId(), 5));

        return "{}";
    }

    public String deleteSong(DeleteSongDtoRequest request) throws Exception {
        Song song = songDao.findSongById(request.getSongId());
        User user = userService.getUserByToken(request.getToken());

        if (user == null) {
            throw new Exception("User not found");
        }
        if (song == null) {
            throw new Exception("Song not found");
        }

        List<Comment> songComments = commentDao.getCommentsBySongId(song.getSongId());

            if (user.getLogin().equals(song.getLogin())) {
                if (ratingDao.getRatingsCount(song.getSongId()) == 1 && songComments.size() == 0) {
                    songDao.getSongList().remove(song);
                    return "{}";
                }

                else {
                    for (Rating rating : ratingDao.getRatingList()) {
                        if (rating.getLogin().equals(song.getLogin())) {
                            ratingDao.getRatingList().remove(rating);
                            throw new Exception("Song rating is deleted, the user can't delete song");
                        }
                    }
                }
            }

        throw new Exception("The user can't delete song");
    }

    public String getSongs(GetSongsDtoRequest request) throws Exception {

        User user = userService.getUserByToken(request.getToken());
        if (user == null) {
            throw new Exception("User not found");
        }
        GetSongsDtoResponse getSongsDtoResponse = new GetSongsDtoResponse();
        getSongsDtoResponse.getSongList().addAll(songDao.getSongList());

        return mapper.writeValueAsString(getSongsDtoResponse);
    }

    public String findSongByComposer(FindSongByComposersDtoRequest request) throws Exception {
        List<Song> songs = new ArrayList<>();

        User user = userService.getUserByToken(request.getToken());
        if (user == null) {
            throw new Exception("User not found");
        }

        for (Song song : songDao.getSongList()) {
            if (Arrays.asList(song.getComposers()).containsAll(Arrays.asList(request.getComposers()))) {
                songs.add(song);
            }
        }

        FindSongByComposersDtoResponse response = new FindSongByComposersDtoResponse();
        response.getSongList().addAll(songs);
        if (songs.size() == 0) {
           throw new Exception("Composers not found");
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

    public String findSongByAuthor(FindSongByAutorDtoRequest request) throws Exception {
        List<Song> songs = new ArrayList<>();

        User user = userService.getUserByToken(request.getToken());
        if (user == null) {
            throw new Exception("User not found");
        }

        for (Song song : songDao.getSongList()) {
            if (Arrays.asList(song.getAuthor()).containsAll(Arrays.asList(request.getAuthor()))) {
                songs.add(song);
            }
        }

        if (songs.size() == 0) {
            throw new Exception("Authors is not found");
        }

        FindSongByAutorDtoResponse response = new FindSongByAutorDtoResponse();
        response.getSongList().addAll(songs);
        return mapper.writeValueAsString(response);
    }

    public String findSongByMusician(FindSongByMisicianDtoRequest request) throws Exception {
        List<Song> songs = new ArrayList<>();

        User user = userService.getUserByToken(request.getToken());

        if (user == null) {
            throw new Exception("User not found");
        }

        for (Song song : songDao.getSongList()) {
            if (song.getMusician().equalsIgnoreCase(request.getMusician())) {
                songs.add(song);
            }
        }

        if (songs.size() == 0) {
            throw new Exception("Authors is not found");
        }

        FindSongsListDtoResponse response = new FindSongsListDtoResponse();
        response.getSongList().addAll(songs);
        return mapper.writeValueAsString(response);
    }

    private boolean findSongBySongName(String songName) {
        for (Song song : songDao.getSongList()) {
            if (song.getSongName().equalsIgnoreCase(songName)) {
                return true;
            }
        }
        return false;
    }

    private void generateSongId(Song song) {
        if (songDao.getSongList().size() != 0) {
            int newId = songDao.getSongList().get(songDao.getSongList().size() - 1).getSongId() + 1;
            song.setSongId(newId);
        }
    }


}
