package com.boxoffice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieTicket {
    private String ticketId;
    private String showId;
    private String userId;
    private int numberOfSeats;
    private Date bookingTime;
    private List<String> seats;

    private MovieShow movieShow;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public MovieShow getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(MovieShow movieShow) {
        this.movieShow = movieShow;
    }

    @Override
    public String toString() {
        return "MovieTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", showId='" + showId + '\'' +
                ", userId='" + userId + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", bookingTime=" + bookingTime +
                ", seats=" + seats +
                ", movieShow=" + movieShow +
                '}';
    }
}
