package net.thumbtack.school.server.request;

public class DeleteSongDtoRequest {
    private String token;
    private int songId;


    public String getToken() {
        return token;
    }

    public int getSongId() {
        return songId;
    }
}
