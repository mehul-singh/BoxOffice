
INSERT INTO
	USER_INFO (name,email,phone_number)
VALUES
  	('Don Joe','donJoe@gmail.com',1234567890);

INSERT INTO
	 USER_INFO (name,email,phone_number)
VALUES
  	('Alber Pinto','albert12@gmail.com',9876543210);


INSERT INTO
    CITIES (city_name,state)
VALUES
  	('Bangalore', 'karanataka');

INSERT INTO
    CITIES (city_name,state)
VALUES
  	('Delhi', 'Delhi');
INSERT INTO
    CITIES (city_name,state)
VALUES
  	('Mumbai', 'Maharashtra');

  	INSERT INTO
    CITIES (city_name,state)
VALUES
  	('Hyderabad', 'Telangana');
  	 	INSERT INTO
    CITIES (city_name,state)
VALUES
  	('Chennai', 'Tamil Nadu');

INSERT INTO
    MOVIES (movie_name,language,Release_date,genre)
VALUES
  	('The Dark Knight', 'english','18 July 2008','Action');

INSERT INTO
    MOVIES (movie_name,language,Release_date,genre)
VALUES
  	('Laxmmi Bomb', 'hindi','22 May 2020','Horror/Comedy');


INSERT INTO
    MOVIE_THEATRE (theatre_name,address,theatre_owner,city_Id)
VALUES
  	('INOX LEDO', '1 MG-Lido Mall',1,1);

 INSERT INTO
    MOVIE_THEATRE (theatre_name,address,theatre_owner,city_Id)
VALUES
  	('Cinépolis Forum Shantiniketan', 'Whitefield Main Road, Hoodi, Thigalarapalya, Krishnarajapura, Bengaluru, Karnataka 560067',2,1);

INSERT INTO
    MOVIE_SHOWS (theatre_id,movie_id,capacity,start_time,end_time)
VALUES
  	(1, 1,100,'2020-04-18 10:00:00','2020-04-18 12:00:00');

  	INSERT INTO
    MOVIE_SHOWS (theatre_id,movie_id,capacity,start_time,end_time)
VALUES
  	(2, 2,5,'2020-05-28 20:00:00','2020-05-28 22:00:00');
INSERT INTO
    MOVIE_TICKETS (show_id,user_id,seat_numbers)
VALUES
  	(1,2,'1,2,3');

  	INSERT INTO
    MOVIE_TICKETS (show_id,user_id,seat_numbers)
VALUES
  	(1,1,'6,7');

  	INSERT INTO
    MOVIE_TICKETS (show_id,user_id,seat_numbers)
VALUES
  	(2,1,'2,3');




