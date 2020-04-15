package com.boxoffice.dao.impl;

import com.boxoffice.dao.SearchDao;
import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.boxoffice.constants.DBConstants.*;
import static com.boxoffice.constants.QueryConstants.*;
@Repository
public class SearchDaoImpl extends NamedParameterJdbcDaoSupport implements SearchDao {
    @Autowired
    public SearchDaoImpl( DataSource dataSource){
        super.setDataSource(dataSource);
    }
    private static final Logger LOG= LoggerFactory.getLogger(SearchDaoImpl.class);
    @Override
    public Map<String, String> getAllActiveCities() {
        try {

            return getNamedParameterJdbcTemplate().query(GET_CITIES, new HashMap<>(), (ResultSetExtractor<Map>) rs -> {
                final Map<String, String> result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getString(1), rs.getString(2));
                }
                return result;
            });
        }catch (Exception e ){
            LOG.error("error in getting all city info {} ",e.getMessage());
            return new HashMap<>();
        }
    }

    @Override
    public List<Movie> getAllShowsInCity(String cityId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(CITY_ID, cityId);
        try {
            return getNamedParameterJdbcTemplate().query(GET_MOVIES_IN_CITY, paramMap, new MovieReposeMapper());
        }catch (Exception e){
            LOG.error("error in getting movie list for city {} {}",cityId,e.getMessage());
            return new ArrayList<>();
        }
    }

    private class MovieReposeMapper implements RowMapper{
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie=new Movie();
            movie.setMovieId(rs.getString(MOVIE_ID));
            movie.setMovieName(rs.getString(MOVIE_NAME));
            movie.setGenre(rs.getString(GENRE));
            movie.setLanguage(rs.getString(LANGUAGE));
            movie.setReleaseDate(rs.getString(RELEASE_DATE));
            return movie;
        }
    }

    @Override
    public List<MovieShow> getAllShowsForMovie(String movieId,String cityId) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put(CITY_ID,cityId);
        paramMap.put(MOVIE_ID,movieId);
        try {
            return getNamedParameterJdbcTemplate().query(GET_ALL_SHOWS_FOR_MOVIE, paramMap, new MovieShowMapper() );
        }
        catch (Exception e){
            LOG.error("error in getting all shows for movie {} in city {} {}",movieId,cityId,e.getMessage());
            return new ArrayList<>();
        }
    }
    private class MovieShowMapper implements RowMapper<MovieShow>{

        @Override
        public MovieShow mapRow(ResultSet rs, int rowNum) throws SQLException {
            MovieShow movieShow = new MovieShow();
            movieShow.setShowId(rs.getString(SHOW_ID));
            movieShow.setCapacity(rs.getInt(CAPACITY));
            movieShow.setStartTime(rs.getTimestamp(START_TIME));
            movieShow.setEndTime(rs.getTimestamp(END_TIME));
            movieShow.setMovieId(rs.getString(MOVIE_ID));
            movieShow.setTheatreId(rs.getString(THEATRE_ID));
            return movieShow;
        }
    }

    @Override
    public Theatre getTheatre(String theatreId) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put(THEATRE_ID,theatreId);
        List<Theatre> theatres=new ArrayList<>();
        try {
             theatres = getNamedParameterJdbcTemplate().query(GET_THEATRE_INFO, paramMap, new RowMapper<Theatre>() {
                @Override
                public Theatre mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Theatre theatre = new Theatre();
                    theatre.setTheatreId(rs.getString(THEATRE_ID));
                    theatre.setName(rs.getString(THEATRE_NAME));
                    theatre.setAddress(rs.getString(ADDRESS));
                    return theatre;
                }
            });
        }catch (Exception e){
            LOG.error("error in getting theatre info for id {} {}",theatreId,e.getMessage());
            return new Theatre();
        }
        return theatres.get(0);
    }

    @Override
    public Movie getMovie(String movieId) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put(MOVIE_ID,movieId);
        List<Movie> movies= new ArrayList<>();
        try {
           movies = getNamedParameterJdbcTemplate().query(GET_MOVIE_INFO, paramMap, new MovieReposeMapper());
        }
        catch (Exception e){
            LOG.error("error in getting movie info for Id {} {}", movieId ,e.getMessage());
            return new Movie();
        }
        return movies.get(0);
    }

    @Override
    public MovieShow getMovieShowInfo(String showId) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put(SHOW_ID,showId);
        List<MovieShow> movieShows=new ArrayList<>();
        try {
            movieShows=getNamedParameterJdbcTemplate().query(GET_SHOW_INFO,paramMap,new MovieShowMapper());
        }catch (Exception e ){
            LOG.error("unable to get show info  for show {} {}",showId,e.getMessage());
            return new MovieShow();
        }

        return movieShows.get(0);
    }

    @Override
    public List<Movie> getFeaturedMovie() {
        List<Movie> movieList=new ArrayList<>();
        try {
            movieList=getNamedParameterJdbcTemplate().query(GET_FEATURED_MOVIES,new HashMap<>(),new MovieReposeMapper());
        }catch (Exception e ){
            LOG.error("unable to get all movie catalogue {}",e.getMessage());
            return new ArrayList<>();
        }

        return movieList;
    }

    @Override
    public List<MovieShow> getAllShowsForTheatre(String theatreId) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put(THEATRE_ID,theatreId);

        try {
            return getNamedParameterJdbcTemplate().query(GET_ALL_RUNNING_SHOWS_IN_THEATRE, paramMap, new MovieShowMapper() );
        }
        catch (Exception e){
            LOG.error("error in getting all shows for theatre {} {}",theatreId,e.getMessage());
            return new ArrayList<>();
        }
    }


}
