package net.thumbtack.school.concert.server;

public class Song {
    private String name;
    private String[] musician;
    private String[] author;
    private String singer;
    private double time;

    public Song(String name, String[] musician, String[] author, String singer, double time) {
        this.name = name;
        this.musician = musician;
        this.author = author;
        this.singer = singer;
        this.time = time;
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
}
