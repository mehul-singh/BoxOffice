package com.boxoffice.dao;

import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;

import java.util.List;
import java.util.Map;

public interface SearchDao {

    Map<String,String> getAllActiveCities();

    List<Movie> getAllShowsInCity(String cityId);

    List<MovieShow> getAllShowsForMovie(String movieId,String cityId);

    Theatre getTheatre(String theatreId);

    Movie getMovie(String movieId);

    MovieShow getMovieShowInfo(String showId);

    List<Movie> getFeaturedMovie();

    List<MovieShow> getAllShowsForTheatre(String theatreId);




}
