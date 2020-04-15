package com.boxoffice.constants;

public class QueryConstants {
    private QueryConstants(){

    }

    public static final String ADD_THEATRE="INSERT INTO\n" +
            "    MOVIE_THEATRE (theatre_name,address,theatre_owner,city_id)\n" +
            "VALUES\n" +
            "  (:THEATRE_NAME, :ADDRESS,:THEATRE_OWNER,:CITY_ID)";
    public static final String ADD_USER="INSERT INTO " +
            "\tUSER_INFO (name,email,phone_number)\n" +
            "VALUES\n" +
            " ( :NAME ,:EMAIL,:PHONE_NUMBER )";
    public static final String ADD_MOVIE="INSERT INTO " +
            "\t MOVIES (movie_name,language,Release_date,genre)\n" +
            "VALUES\n" +
            " ( :MOVIE_NAME, :LANGUAGE, :RELEASE_DATE,:GENRE )";
    public static final String ADD_SHOW="INSERT INTO\n" +
            "    MOVIE_SHOWS (theatre_id,movie_id,capacity,start_time,end_time)\n" +
            "VALUES\n" +
            "  \t( :THEATRE_ID , :MOVIE_ID,:CAPACITY, :START_TIME, :END_TIME )";
    public static final String NEW_BOOKING="INSERT INTO\n" +
            "    MOVIE_TICKETS (show_id,user_id,seat_numbers)\n" +
            "VALUES\n" +
            "  \t( :SHOW_ID, :USER_ID, :SEAT_NUMBERS )";

    public static final String GET_CITIES="SELECT city_id, city_name from CITIES";

    public static final String GET_MOVIES_IN_CITY="SELECT m.movie_id, movie_name, language , Release_date, genre\n" +
            "FROM MOVIES m,MOVIE_SHOWS ms,MOVIE_THEATRE mt\n" +
            "where   ms.movie_id=m.movie_id \n" +
            "    and ms.theatre_id=mt.theatre_id and mt.city_id=:CITY_ID ";


    public static final String GET_ALL_BOOKINGS_FOR_USER="SELECT ticket_id, show_id,user_id,booking_time, seat_numbers from MOVIE_TICKETS where  user_id=:USER_ID";

    public static final String GET_BOOKED_TICKETS_FOR_SHOW="select group_concat(seat_numbers separator ',') as seats from MOVIE_TICKETS where show_id=:SHOW_ID group by show_id ";

    public static final String GET_SHOW_CAPACITY="select capacity from MOVIE_SHOWS where show_Id=:SHOW_ID";

    public static final String GET_SHOW_INFO=" SELECT show_id, theatre_id ,movie_id, start_time , end_time ,  capacity from  MOVIE_SHOWS where show_id=:SHOW_ID ";

    public static final String GET_THEATRE_INFO="select theatre_id, theatre_name, address \n" +
            " FROM MOVIE_THEATRE\n" +
            " where theatre_id=:THEATRE_ID";

    public static final String GET_ALL_SHOWS_FOR_MOVIE="SELECT ms.show_id as show_id, ms.theatre_id as theatre_id, ms.movie_id as movie_id, start_time , end_time , capacity \n" +
            " from  MOVIE_SHOWS ms,MOVIE_THEATRE mt \n" +
            " where ms.theatre_id=mt.theatre_id\n" +
            " and mt.city_id=:CITY_ID and movie_id=:MOVIE_ID";
    public static final String GET_MOVIE_INFO="SELECT movie_id, movie_name, language , Release_date, genre\n" +
            " FROM MOVIES \n" +
            " where   movie_id=:MOVIE_ID";
    public static final String IS_VALID_USER="select user_Id from USER_INFO where user_Id=:USER_ID";

    public static final String IS_VALID_ADMIN="select theatre_owner from MOVIE_THEATRE where theatre_owner=:THEATRE_OWNER group by theatre_owner";
    public static final String IS_VALID_SHOW_ADDITION="select theatre_id from MOVIE_THEATRE where theatre_owner=:THEATRE_OWNER and theatre_id=:THEATRE_ID";
    public static final String GET_FEATURED_MOVIES="SELECT movie_id, movie_name, language , Release_date, genre\n" +
            " FROM MOVIES";

    public static final String GET_ALL_RUNNING_SHOWS_IN_THEATRE= " SELECT ms.show_id as show_id , mt.theatre_id as theatre_id ,ms.movie_id as movie_id, start_time , end_time , capacity\n" +
            " from  MOVIE_SHOWS ms,MOVIE_THEATRE mt\n" +
            " where ms.theatre_id=mt.theatre_id\n" +
            " and mt.theatre_id=:THEATRE_ID";

}
