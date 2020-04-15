package com.boxoffice.controller.impl;

import com.boxoffice.controller.SearchController;
import com.boxoffice.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchControllerImpl implements SearchController {
    private static final Logger LOGGER= LoggerFactory.getLogger(SearchControllerImpl.class);

    @Autowired
    private SearchService searchService;

    @Override
    public ResponseEntity<?> getAllActiveCities() {
           return new ResponseEntity<>(searchService.getAllActiveCities(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllShowsInCity(String cityId) {
        LOGGER.debug("received cityId request for {}",cityId);
        return new ResponseEntity<>(searchService.getAllShowsInCity(cityId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllShowsForMovie(String movieId, String cityId) {
        LOGGER.debug("received cityId request for {} and movieId {}",cityId,movieId);
        return new ResponseEntity<>(searchService.getAllShowsForMovie(movieId,cityId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllMovies() {
     return new ResponseEntity<>(searchService.getFeaturedMovie(), HttpStatus.OK);

    }
}
