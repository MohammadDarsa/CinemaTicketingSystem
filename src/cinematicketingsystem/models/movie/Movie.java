package cinematicketingsystem.models.movie;

//database table movie <=> Movie class

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "movie")
public class Movie {
    @ID
    @Col(name  = "mid")
    private Integer id;
    @Col(name = "mname")
    private String name;
    @Col(name = "description")
    private String description;
    @Col(name = "tor")
    private Timestamp screenPlayTimestamp;
    @Col(name = "price")
    private Double price;
    @Col(name = "lang")
    private String language;
    @Col(name = "rating")
    private Double rating;
    @Col(name = "len")
    private Timestamp length;
    @Col(name = "tickets_sold", updateIgnore = true, insertIgnore = true)
    private Integer ticketsSold;
    @Col(name = "image")
    private String imagePath;
    @Col(name = "admin_id", insertIgnore = true, updateIgnore = true)
    private Integer adminId;

    public Movie() {
    }

    public Movie(String name, String description, Timestamp screenPlayTimestamp, Double price, String language, Double rating, Timestamp length, Integer ticketsSold, String imagePath, Integer adminId) {
        this.name = name;
        this.description = description;
        this.screenPlayTimestamp = screenPlayTimestamp;
        this.price = price;
        this.language = language;
        this.rating = rating;
        this.length = length;
        this.ticketsSold = ticketsSold;
        this.imagePath = imagePath;
        this.adminId = adminId;
    }

    public Movie(Integer id, String name, String description, Timestamp screenPlayTimestamp, Double price, String language, Double rating, Timestamp length, Integer ticketsSold, String imagePath, Integer adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.screenPlayTimestamp = screenPlayTimestamp;
        this.price = price;
        this.language = language;
        this.rating = rating;
        this.length = length;
        this.ticketsSold = ticketsSold;
        this.imagePath = imagePath;
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
    }

    public Movie setId(Integer id) {
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

    public Timestamp getScreenPlayTimestamp() {
        return screenPlayTimestamp;
    }

    public Movie setScreenPlayTimestamp(Timestamp screenPlayTimestamp) {
        this.screenPlayTimestamp = screenPlayTimestamp;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Movie setPrice(Double price) {
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

    public Double getRating() {
        return rating;
    }

    public Movie setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Timestamp getLength() {
        return length;
    }

    public Movie setLength(Timestamp length) {
        this.length = length;
        return this;
    }

    public Integer getTicketsSold() {
        return ticketsSold;
    }

    public Movie setTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Movie setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Movie setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", screenPlayTimestamp=" + screenPlayTimestamp +
                ", price=" + price +
                ", language='" + language + '\'' +
                ", rating=" + rating +
                ", length=" + length +
                ", ticketsSold=" + ticketsSold +
                ", imagePath='" + imagePath + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}
