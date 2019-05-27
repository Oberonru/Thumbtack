package net.thumbtack.school.concert.server.model;

import net.thumbtack.school.concert.server.song.Song;

import java.util.List;

public class AddSongRequest extends AuthenticationData {
    List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
