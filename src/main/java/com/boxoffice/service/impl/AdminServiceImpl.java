package com.boxoffice.service.impl;

import com.boxoffice.dao.AdminDao;
import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;
import com.boxoffice.service.AdminService;
import com.boxoffice.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOG= LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private SearchService searchService;

    @Override
    public boolean addMovie(Movie movie) {
        LOG.debug("adding movie {}",movie);
        return adminDao.addMovie(movie);
    }

    @Override
    public boolean addTheatre(Theatre theatre) {
        LOG.debug("adding theatre {}",theatre);
        return adminDao.addTheatre(theatre);
    }

    @Override
    public boolean addShow(MovieShow movieShow) {
        LOG.debug("adding show {}",movieShow);
        if(movieShow.getEndTime().before(movieShow.getStartTime())){
            LOG.error("wrong start and end time for show  start {} end {}",movieShow.getStartTime(),movieShow.getEndTime());
            return false;
        }
        List<MovieShow> runningMovies=searchService.getAllShowsForTheatre(movieShow.getTheatreId());
        for(MovieShow show: runningMovies){
            if( (movieShow.getStartTime().after(show.getStartTime()) && movieShow.getStartTime().before(show.getEndTime()))
            || (movieShow.getEndTime().after(show.getStartTime()) && movieShow.getEndTime().before(show.getEndTime())) ||
                    (movieShow.getStartTime().equals(show.getStartTime()))
                    || movieShow.getEndTime().equals(show.getEndTime())){
                LOG.error("conflicting  start and end time for new show {} with present Show {}",movieShow,show);
                return false;
            }
        }
        return adminDao.addShow(movieShow);
    }

    @Override
    public boolean isValidAdmin(String adminId) {
        return adminDao.isValidAdmin(adminId);
    }

    @Override
    public boolean isValidShow(String theatreId, String adminId) {
        return adminDao.isValidShow(theatreId,adminId);
    }
}
