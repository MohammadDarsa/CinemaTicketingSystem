package cinematicketingsystem.models.movie;

import java.sql.Timestamp;

public final class MovieBuilder {
    private Movie movie;

    private MovieBuilder() {
        movie = new Movie();
    }

    public static MovieBuilder aMovie() {
        return new MovieBuilder();
    }

    public MovieBuilder setId(Integer id) {
        movie.setId(id);
        return this;
    }

    public MovieBuilder setName(String name) {
        movie.setName(name);
        return this;
    }

    public MovieBuilder setDescription(String description) {
        movie.setDescription(description);
        return this;
    }

    public MovieBuilder setScreenPlayTimestamp(Timestamp screenPlayTimestamp) {
        movie.setScreenPlayTimestamp(screenPlayTimestamp);
        return this;
    }

    public MovieBuilder setPrice(Double price) {
        movie.setPrice(price);
        return this;
    }

    public MovieBuilder setLanguage(String language) {
        movie.setLanguage(language);
        return this;
    }

    public MovieBuilder setRating(Double rating) {
        movie.setRating(rating);
        return this;
    }

    public MovieBuilder setLength(Timestamp length) {
        movie.setLength(length);
        return this;
    }

    public MovieBuilder setTicketsSold(Integer ticketsSold) {
        movie.setTicketsSold(ticketsSold);
        return this;
    }

    public MovieBuilder setImagePath(String imagePath) {
        movie.setImagePath(imagePath);
        return this;
    }

    public MovieBuilder setAdminId(Integer adminId) {
        movie.setAdminId(adminId);
        return this;
    }

    public Movie build() {
        return movie;
    }
}
