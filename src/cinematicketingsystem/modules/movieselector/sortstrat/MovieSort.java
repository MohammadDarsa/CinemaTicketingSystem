package cinematicketingsystem.modules.movieselector.sortstrat;

import cinematicketingsystem.models.movie.Movie;

import java.util.List;

public interface MovieSort {
    List<Movie> sort(List<Movie> movieList);
}
