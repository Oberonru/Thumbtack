package net.thumbtack.school.concert.server.song;

import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.server.Error;
import net.thumbtack.school.concert.server.ServerException;

public class Rating {
    private Integer rating;
    private User author;

    public Rating(Integer rating, User author) {
        setRating(rating);
        setAuthor(author);
    }

    public Integer getRating() {
        return rating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
