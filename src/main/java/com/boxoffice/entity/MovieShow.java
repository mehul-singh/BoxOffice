package com.boxoffice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieShow {

    String showId;
    String movieId;
    String theatreId;
    Integer capacity;
    Date startTime;
    Date endTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Movie movie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Theatre theatre;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "MovieShow{" +
                "showId='" + showId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", theatreId='" + theatreId + '\'' +
                ", capacity=" + capacity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", movie=" + movie +
                ", theatre=" + theatre +
                '}';
    }
}
