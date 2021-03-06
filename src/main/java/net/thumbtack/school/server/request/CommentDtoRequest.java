package net.thumbtack.school.server.request;

public class CommentDtoRequest {
    private String token;
    private String content;
    private int songId;
    private int replyCommentId;

    public CommentDtoRequest() {}

    public String getToken() {
        return token;
    }

    public String getContent() {
        return content;
    }

    public int getSongId() {
        return songId;
    }

    public int getReplyCommentId() {
        return replyCommentId;
    }
}
