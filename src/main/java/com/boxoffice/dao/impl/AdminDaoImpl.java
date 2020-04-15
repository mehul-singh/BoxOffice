package com.boxoffice.dao.impl;

import com.boxoffice.dao.AdminDao;
import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.boxoffice.constants.DBConstants.*;
import static com.boxoffice.constants.QueryConstants.*;

@Repository
public class AdminDaoImpl extends NamedParameterJdbcDaoSupport implements AdminDao {
    @Autowired
    public AdminDaoImpl( DataSource dataSource){
        super.setDataSource(dataSource);
    }


    private static final Logger LOG= LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    public boolean addMovie(Movie movie) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(MOVIE_NAME, movie.getMovieName());
        paramMap.put(GENRE, movie.getGenre());
        paramMap.put(LANGUAGE, movie.getLanguage());
        paramMap.put(RELEASE_DATE,movie.getReleaseDate());
        try {
            getNamedParameterJdbcTemplate().update(ADD_MOVIE, paramMap);
        }catch (Exception e){
            LOG.error("error in creating user {} {}",movie,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addTheatre(Theatre theatre) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(THEATRE_NAME,theatre.getName());
        paramMap.put(ADDRESS, theatre.getAddress());
        paramMap.put(CITY_ID, theatre.getCityId());
        paramMap.put(THEATRE_OWNER,theatre.getOwnerId());
        try {
            getNamedParameterJdbcTemplate().update(ADD_THEATRE, paramMap);
        }catch (Exception e){
            LOG.error("error in adding new movie {} {}",theatre,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addShow(MovieShow movieShow) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(MOVIE_ID,movieShow.getMovieId());
        paramMap.put(START_TIME, movieShow.getStartTime());
        paramMap.put(END_TIME, movieShow.getEndTime());
        paramMap.put(CAPACITY,movieShow.getCapacity());
        paramMap.put(THEATRE_ID,movieShow.getTheatreId());
        try {
            getNamedParameterJdbcTemplate().update(ADD_SHOW, paramMap);
        }catch (Exception e){
            LOG.error("error in adding new show  {}, {}",movieShow,e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidAdmin(String adminId) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(THEATRE_OWNER, adminId);
        String admin;
        try {
            admin= getNamedParameterJdbcTemplate().queryForObject(IS_VALID_ADMIN,paramMap,String.class);
        }catch (Exception e){
            LOG.error("admin does not exist {}",e.getMessage());
            return false;
        }
        return admin != null;
    }

    @Override
    public boolean isValidShow(String theatreId, String adminId) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put(THEATRE_OWNER, adminId);
        paramMap.put(THEATRE_ID,theatreId);
        List<String> theatres;
        try {
            theatres= getNamedParameterJdbcTemplate().queryForList(IS_VALID_SHOW_ADDITION,paramMap,String.class);
        }catch (Exception e){
            LOG.error("theatre does not exist {}",e.getMessage());
            return false;
        }
        return theatres != null;    }
}
