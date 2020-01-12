package net.thumbtack.school.server.dao;

import net.thumbtack.school.server.model.Song;

import java.util.List;

public interface SongDao {
    void insert(Song song);

    String deleteSong(Song song) throws Exception;

    Song findSongById(int songId);

    List<Song> getSongList();
}
