package cinematicketingsystem.models;

//database table movie <=> Movie class

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "movie")
public class Movie {
    @ID
    @Col(name  = "mid")
    private String id;
    @Col(name = "name")
    private String name;
    @Col(name = "desc")
    private String description;
    @Col(name = "tor")
    private String screenPlayTime;
    @Col(name = "price")
    private String price;
    @Col(name = "lang")
    private String language;
    @Col(name = "rating")
    private String rating;
    @Col(name = "len")
    private String length;
    @Col(name = "admin_id", insertIgnore = true, updateIgnore = true)
    private String adminId;

    public Movie() {
    }

    public Movie(String name, String description, String screenPlayTime, String price, String language, String rating, String length, String adminId) {
        this.name = name;
        this.description = description;
        this.screenPlayTime = screenPlayTime;
        this.price = price;
        this.language = language;
        this.rating = rating;
        this.length = length;
        this.adminId = adminId;
    }

    public Movie(String id, String name, String description, String screenPlayTime, String price, String language, String rating, String length, String adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.screenPlayTime = screenPlayTime;
        this.price = price;
        this.language = language;
        this.rating = rating;
        this.length = length;
        this.adminId = adminId;
    }

    public String getId() {
        return id;
    }

    public Movie setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getScreenPlayTime() {
        return screenPlayTime;
    }

    public Movie setScreenPlayTime(String screenPlayTime) {
        this.screenPlayTime = screenPlayTime;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Movie setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Movie setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public Movie setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getLength() {
        return length;
    }

    public Movie setLength(String length) {
        this.length = length;
        return this;
    }

    public String getAdminId() {
        return adminId;
    }

    public Movie setAdminId(String adminId) {
        this.adminId = adminId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", screenPlayTime='" + screenPlayTime + '\'' +
                ", price='" + price + '\'' +
                ", language='" + language + '\'' +
                ", rating='" + rating + '\'' +
                ", length='" + length + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
