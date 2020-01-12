package net.thumbtack.school.server;

import net.thumbtack.school.server.dao.RatingDaoImpl;
import net.thumbtack.school.server.dao.SongDaoImpl;
import net.thumbtack.school.server.model.Rating;
import net.thumbtack.school.server.model.Song;
import net.thumbtack.school.server.model.User;
import net.thumbtack.school.server.request.DeleteSongRatingDtoRequest;
import net.thumbtack.school.server.request.RatingDtoRequest;

public class RatingService {
    private UserService userService = new UserService();
    private RatingDaoImpl ratingDao = new RatingDaoImpl();
    private SongDaoImpl songDao = new SongDaoImpl();

    public String addRaiting(RatingDtoRequest request) throws Exception {

        if (!verifyRating(request.getSongRating())) {
            throw new Exception("Rating not valid");
        }
        User user = userService.getUserByLogin(request.getLogin());
        if (user == null) {
            throw new Exception("User not found");
        }
        Song song = songDao.findSongById(request.getSongId());
        if (song == null) {
            throw new Exception("Song not found");
        }

        ratingDao.insert(new Rating(user.getLogin(), song.getSongId(), request.getSongRating()));
        return "{}";
    }

    public boolean verifyRating(int raiting) {
        return raiting > 0 && raiting < 6;
    }

    public String deleteRating(DeleteSongRatingDtoRequest request) throws Exception {
        User user = userService.getUserByToken(request.getToken());
        if (user == null) {
            throw new Exception("User not found");
        }

        Song song = songDao.findSongById(request.getSongId());
        if (song == null) {
            throw new Exception("Song not found");
        }

        ratingDao.deleteRating(new Rating(user.getLogin(), request.getSongId()));
        return "{}";
    }
}
