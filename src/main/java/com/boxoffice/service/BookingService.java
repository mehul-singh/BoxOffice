package com.boxoffice.service;

import com.boxoffice.entity.MovieTicket;

public interface BookingService {

    boolean bookTicket(String userId, MovieTicket movieTicket);

}
