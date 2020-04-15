package com.boxoffice.dao.impl;

import com.boxoffice.dao.BookingDao;
import com.boxoffice.entity.MovieTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import java.util.*;

import static com.boxoffice.constants.DBConstants.*;
import static com.boxoffice.constants.QueryConstants.*;
@Repository
public class BookingDaoImpl extends NamedParameterJdbcDaoSupport implements BookingDao {
    @Autowired
    public BookingDaoImpl(DataSource dataSource){
        super.setDataSource(dataSource);
    }

    private static final Logger LOG= LoggerFactory.getLogger(BookingDaoImpl.class);

    @Transactional
    @Override
    public boolean bookTicket(String userId, MovieTicket movieTicket) {
        try{
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put(SHOW_ID,movieTicket.getShowId());
            int capacity=getNamedParameterJdbcTemplate().queryForObject(GET_SHOW_CAPACITY,paramMap,Integer.class);
            LOG.debug("found capacity {}" ,capacity);
            String seatsBooked=null;
            try {
                 seatsBooked=getNamedParameterJdbcTemplate().queryForObject(GET_BOOKED_TICKETS_FOR_SHOW,paramMap,String.class);
            }catch (EmptyResultDataAccessException e){
                LOG.debug("no seats booked yet");
            }
            if(!StringUtils.isEmpty(seatsBooked)){
                LOG.debug("booked seats {}",seatsBooked);
                String [] seats=seatsBooked.split(",");
                Set<String> booked= new HashSet<>(Arrays.asList(seats));
                if(capacity-booked.size()<movieTicket.getNumberOfSeats()){
                    LOG.debug("capacity exceeded {}",movieTicket.getShowId());
                    return false;
                }
                for(String seatNo: movieTicket.getSeats()){
                    if(booked.contains(seatNo)) {
                        LOG.debug("overlapping seats {}",seatNo);
                        return false;

                    }
                }
            }
            paramMap.put(USER_ID,movieTicket.getUserId());
            Set<String> seatsToBeBooked=new HashSet<>(movieTicket.getSeats());
            paramMap.put(SEAT_NUMBERS,getSeatsToBeBooked(seatsToBeBooked));

            getNamedParameterJdbcTemplate().update(NEW_BOOKING,paramMap);
        }
        catch (Exception e){
            LOG.error("Failed in booking ticket {} {}",movieTicket,e.getMessage());
            return false;
        }
        return true;
    }

    private String getSeatsToBeBooked(Set<String> seats){
        StringBuilder seatString= new StringBuilder();
        Iterator<String> iterator=seats.iterator();
        if(seats.size()==1){
            return iterator.next();
        }
        else
        {
            seatString.append(iterator.next());
        }
         while (iterator.hasNext()){
           seatString.append(",");
           seatString.append(iterator.next());
        }
        return seatString.toString();
    }

}
