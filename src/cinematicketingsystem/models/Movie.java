package cinematicketingsystem.models;

//database table movie <=> Movie class

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "movie")
public class Movie {
    @ID
    @Col(name  = "id_movie")
    private String id;

    @Col(name = "name_movie", updateIgnore = true)
    private String name;

    @Col(name = "desc_movie", insertIgnore = true)
    private String desc;

    @Col(name = "tor_movie", updateIgnore = true)
    private String tor;

    @Col(name = "price_movie", updateIgnore = true)
    private String price;

    public Movie() {}

    public Movie(String name, String desc, String tor, String price) {
        this.name = name;
        this.desc = desc;
        this.tor = tor;
        this.price = price;
    }

    public Movie(String id, String name, String desc, String tor, String price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.tor = tor;
        this.price = price;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", tor='" + tor + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
