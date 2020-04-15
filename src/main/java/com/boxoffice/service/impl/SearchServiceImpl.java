package com.boxoffice.service.impl;

import com.boxoffice.dao.SearchDao;
import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchDao searchDao;
    private static final Logger LOGGER= LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public Map<String, String> getAllActiveCities() {
        return searchDao.getAllActiveCities();
    }

    @Override
    public List<Movie> getAllShowsInCity(String cityId) {
        LOGGER.debug("getting all shows for cityId {}",cityId);
        return searchDao.getAllShowsInCity(cityId);
    }

    @Override
    public List<MovieShow> getAllShowsForMovie(String movieId, String cityId) {
        List<MovieShow> movieShowList=searchDao.getAllShowsForMovie(movieId,cityId);
        LOGGER.debug("received  all shows for cityId {} and movieId {} list {}",cityId,movieId,movieShowList);
        movieShowList.forEach((MovieShow movieShow) -> {
            movieShow.setTheatre(searchDao.getTheatre(movieShow.getTheatreId()));
            movieShow.setMovie(searchDao.getMovie(movieShow.getMovieId()));
        });
        LOGGER.debug("final  all shows for cityId {} and movieId {} list {}",cityId,movieId,movieShowList);
        return movieShowList;
    }

    @Override
    public List<Movie> getFeaturedMovie() {
        return searchDao.getFeaturedMovie();
    }

    @Override
    public List<MovieShow> getAllShowsForTheatre(String theatreId) {
        return searchDao.getAllShowsForTheatre(theatreId);
    }
}
