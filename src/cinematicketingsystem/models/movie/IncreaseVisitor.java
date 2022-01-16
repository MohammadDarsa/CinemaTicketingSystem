package cinematicketingsystem.models.movie;

public class IncreaseVisitor implements Visitor{

    @Override
    public void changeRating(Movie movie) {
        movie.setRating((Integer.parseInt(movie.getRating()) + 1) + "");
    }
}
