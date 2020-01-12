package net.thumbtack.school.server.response;

import net.thumbtack.school.server.model.Song;

import java.util.ArrayList;
import java.util.List;

public class GetSongsDtoResponse {
    private List<Song> songList = new ArrayList<>();

    public List<Song> getSongList() {
        return songList;
    }
}
