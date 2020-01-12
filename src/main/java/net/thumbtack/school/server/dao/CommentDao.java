package net.thumbtack.school.server.dao;

import net.thumbtack.school.server.model.Comment;

import java.util.List;

public interface CommentDao {
    void insert(Comment comment);
    List<Comment> getCommentsBySongId(int songId);
    List<Comment> getCommentList();
}
