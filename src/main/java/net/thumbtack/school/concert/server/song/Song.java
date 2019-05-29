package net.thumbtack.school.concert.server.song;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

public class Song {

    private String name;
    private String[] musician;
    private String[] author;
    private String singer;
    private double time;
    private String songId;
    private List<Rating> ratingList;

    public Song(String name, String[] musician, String[] author, String singer, double time) {
        setName(name);
        setMusician(musician);
        setAuthor(author);
        setSinger(singer);
        setTime(time);
        this.songId = generateSongId();
    }

    private String generateSongId() {
        SecureRandom secureRandom = new SecureRandom();
        Date date = new Date();
        return String.valueOf(secureRandom.nextInt()) + "v" + date.getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMusician() {
        return musician;
    }

    public void setMusician(String[] musician) {
        this.musician = musician;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSongId() {
        return songId;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void addRating(Rating rating) {
        if (rating.getRating() == 0) {
            rating.setRating(null);
         }
         if (rating.getRating() >=1 && rating.getRating() <= 5) {
            rating.setRating(rating.getRating());
         }
        ratingList.add(rating);
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
