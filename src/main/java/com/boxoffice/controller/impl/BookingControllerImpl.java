package com.boxoffice.controller.impl;

import com.boxoffice.controller.BookingController;
import com.boxoffice.entity.MovieTicket;
import com.boxoffice.service.BookingService;
import com.boxoffice.service.UserService;
import com.boxoffice.util.BoxOfficeResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingControllerImpl implements BookingController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Override
    public ResponseEntity<?> bookTicket(String userId, MovieTicket movieTicket) {
        if(userService.isExist(userId)){
            if(bookingService.bookTicket(userId,movieTicket)){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }
}
