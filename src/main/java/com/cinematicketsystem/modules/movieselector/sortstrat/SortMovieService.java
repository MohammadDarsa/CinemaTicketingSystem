package com.cinematicketsystem.modules.movieselector.sortstrat;

import com.cinematicketsystem.models.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortMovieService {
    private MovieSort movieSort;

    public void sort(List<Movie> movieList) {
        movieSort.sort(movieList);
    }
}
