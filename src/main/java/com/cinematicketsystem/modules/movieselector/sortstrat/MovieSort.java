package com.cinematicketsystem.modules.movieselector.sortstrat;

import com.cinematicketsystem.models.movie.Movie;

import java.util.List;

public interface MovieSort {
    List<Movie> sort(List<Movie> movieList);
}
