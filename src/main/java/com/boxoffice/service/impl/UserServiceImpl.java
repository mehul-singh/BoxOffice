package com.boxoffice.service.impl;

import com.boxoffice.dao.SearchDao;
import com.boxoffice.dao.UserDao;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.MovieTicket;
import com.boxoffice.entity.User;
import com.boxoffice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SearchDao searchDao;

    private static final Logger LOG= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean addUser(User user) {
        LOG.debug("received user for addition {}",user);
        return userDao.addUser(user);
    }
    public List<MovieTicket> getAllBookings(String userId){
        LOG.debug("finding bookings for userId {}",userId);
        List<MovieTicket> movieTicketList=userDao.getAllBookings(userId);
        LOG.debug("list received for userId {} bookings {} ",userId,movieTicketList);
        for(MovieTicket movieTicket : movieTicketList){
                movieTicket.setMovieShow(getShowInfoForTicket(movieTicket.getShowId()));
        }
        LOG.debug("final response list  for userId {} bookings {} ",userId,movieTicketList);
        return movieTicketList;
    }

    @Override
    public boolean isExist(String id) {

        return userDao.isExist(id);
    }

    private MovieShow getShowInfoForTicket(String showId){
        MovieShow movieShow= searchDao.getMovieShowInfo(showId);
        movieShow.setTheatre(searchDao.getTheatre(movieShow.getTheatreId()));
        movieShow.setMovie(searchDao.getMovie(movieShow.getMovieId()));
        return movieShow;
    }
}
