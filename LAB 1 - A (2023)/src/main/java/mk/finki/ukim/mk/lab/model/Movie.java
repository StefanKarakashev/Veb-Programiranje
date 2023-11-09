package mk.finki.ukim.mk.lab.model;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Movie {
    private String title;
    private String summary;
    private double rating;

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }
}
