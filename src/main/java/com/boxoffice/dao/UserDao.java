package com.boxoffice.dao;

import com.boxoffice.entity.MovieTicket;
import com.boxoffice.entity.User;

import java.util.List;

public interface UserDao {

    boolean addUser(User user);
    public List<MovieTicket> getAllBookings(String userId);
    boolean isExist(String Id);
}
