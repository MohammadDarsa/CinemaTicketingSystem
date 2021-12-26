package cinematicketingsystem.models;

//database table movie <=> Movie class

public class Movie {
    private String id_movie;
    private String name_movie;
    private String desc_movie;
    private String tor_movie;
    private String price_movie;

    @Override
    public String toString() {
        return "Movie{" +
                "id_movie='" + id_movie + '\'' +
                ", name_movie='" + name_movie + '\'' +
                ", desc_movie='" + desc_movie + '\'' +
                ", tor_movie='" + tor_movie + '\'' +
                ", price_movie='" + price_movie + '\'' +
                '}';
    }
}
