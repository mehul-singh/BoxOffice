package com.boxoffice.service.impl;

import com.boxoffice.dao.BookingDao;
import com.boxoffice.entity.MovieTicket;
import com.boxoffice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    private static final Logger LOGGER= LoggerFactory.getLogger(BookingServiceImpl.class);
    @Override
    public boolean bookTicket(String userId, MovieTicket movieTicket) {
        movieTicket.setUserId(userId);
        movieTicket.setNumberOfSeats(movieTicket.getSeats().size());
        LOGGER.debug("movie ticket to be booked for user",userId,movieTicket);
        return bookingDao.bookTicket(userId,movieTicket);
    }
}
