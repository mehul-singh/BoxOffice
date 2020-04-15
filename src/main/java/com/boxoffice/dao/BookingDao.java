package com.boxoffice.dao;

import com.boxoffice.entity.MovieTicket;

public interface BookingDao {

    boolean bookTicket(String userId, MovieTicket movieTicket);
}
