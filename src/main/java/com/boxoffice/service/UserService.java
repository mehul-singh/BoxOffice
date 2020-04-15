package com.boxoffice.service;

import com.boxoffice.entity.MovieTicket;
import com.boxoffice.entity.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    public List<MovieTicket> getAllBookings(String userId);
    boolean isExist(String id);
}
