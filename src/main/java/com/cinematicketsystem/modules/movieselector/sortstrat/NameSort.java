package com.cinematicketsystem.modules.movieselector.sortstrat;

import com.cinematicketsystem.models.movie.Movie;

import java.util.Comparator;
import java.util.List;

public class NameSort implements MovieSort{
    @Override
    public List<Movie> sort(List<Movie> movieList) {
        movieList.sort(Comparator.comparing(Movie::getName));
        return movieList;
    }
}
