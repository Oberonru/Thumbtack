package net.thumbtack.school.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public class DataBaseModel {
    @JsonDeserialize(as = ArrayList.class, contentAs = User.class)
    public List<User> users = new ArrayList<>();

    @JsonDeserialize(as = ArrayList.class, contentAs = Song.class)
    public List<Song> songs = new ArrayList<>();

    @JsonDeserialize(as = ArrayList.class, contentAs = Rating.class)
    public List<Rating> ratings = new ArrayList<>();

    @JsonDeserialize(as = ArrayList.class, contentAs = Comment.class)
    public List<Comment> comments = new ArrayList<>();

}
