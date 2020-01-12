package net.thumbtack.school.server.dao;

import net.thumbtack.school.server.database.DataBase;
import net.thumbtack.school.server.model.Song;

import java.util.List;

public class SongDaoImpl implements SongDao {
    private DataBase db = DataBase.getInstance();

    public void insert(Song song) {
        db.addSong(song);
    }

    public String deleteSong(Song song) throws Exception {
        return db.deleteSong(song);
    }

    public Song findSongById(int songId) {
       return db.findSongById(songId);
    }

    public List<Song> getSongList() {
        return db.getSongList();
    }
}
