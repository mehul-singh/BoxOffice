package com.boxoffice.service;

import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;

public interface AdminService {

    boolean addMovie(Movie movie);
    boolean addTheatre(Theatre theatre);
    boolean addShow(MovieShow movieShow);
    boolean isValidAdmin(String adminId);
    boolean isValidShow(String theatreId,String adminId);
}
