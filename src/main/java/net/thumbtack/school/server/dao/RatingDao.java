package net.thumbtack.school.server.dao;

import net.thumbtack.school.server.model.Rating;

import java.util.List;

public interface RatingDao {
    void insert(Rating rating) throws Exception;

    void deleteRating(Rating rating) throws Exception;

    int getRatingsCount(int songId);

        List<Rating> getRatingList();
}
