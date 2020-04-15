package com.boxoffice.service;

import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;

import java.util.List;
import java.util.Map;

public interface SearchService {

    Map<String,String> getAllActiveCities();

    List<Movie> getAllShowsInCity(String cityId);

    List<MovieShow> getAllShowsForMovie(String movieId, String cityId);

    List<Movie> getFeaturedMovie();

    List<MovieShow> getAllShowsForTheatre(String theatreId);

}
