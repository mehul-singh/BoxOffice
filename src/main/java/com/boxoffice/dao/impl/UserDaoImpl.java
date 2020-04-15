package com.boxoffice.dao.impl;

import com.boxoffice.dao.UserDao;
import com.boxoffice.entity.MovieTicket;
import com.boxoffice.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.boxoffice.constants.DBConstants.*;
import static com.boxoffice.constants.QueryConstants.*;
@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {
    @Autowired
    public UserDaoImpl(DataSource dataSource){
        super.setDataSource(dataSource);
    }
    private static final Logger LOG= LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public boolean addUser(User user) {

        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(NAME, user.getName());
        paramMap.put(EMAIL, user.getEmail());
        paramMap.put(PHONE_NUMBER, user.getPhoneNumber());
        try {
            getNamedParameterJdbcTemplate().update(ADD_USER, paramMap);
        }catch (Exception e){
            LOG.error("error in creating user {} {}",user,e.getMessage());
            return false;
        }
        return true;
    }

    public List<MovieTicket> getAllBookings(String userId){
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(USER_ID, userId);
        try {
            return getNamedParameterJdbcTemplate().query(GET_ALL_BOOKINGS_FOR_USER, paramMap, new RowMapper<MovieTicket>() {
                @Override
                public MovieTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MovieTicket movieTicket=new MovieTicket();
                    movieTicket.setUserId(rs.getString(USER_ID));
                    String[] seats=rs.getString(SEAT_NUMBERS).split(",");
                    movieTicket.setSeats(Arrays.asList(seats));
                    movieTicket.setBookingTime(rs.getTimestamp(BOOKING_TIME));
                    movieTicket.setNumberOfSeats(seats.length);
                    movieTicket.setShowId(rs.getString(SHOW_ID));
                    movieTicket.setTicketId(rs.getString(TICKET_ID));
                    return movieTicket;
                }
            });
        }catch (Exception e){
            LOG.error("Error in getting all Shows or no bookings yet for user {} {}",userId,e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean isExist(String id) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(USER_ID, id);
        String user;
        try {
           user= getNamedParameterJdbcTemplate().queryForObject(IS_VALID_USER,paramMap,String.class);
        }catch (Exception e){
            LOG.error("user does not exist {}",e.getMessage());
            return false;
        }
        return user != null;
    }
}
