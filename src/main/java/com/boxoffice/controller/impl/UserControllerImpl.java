package com.boxoffice.controller.impl;

import com.boxoffice.controller.UserController;
import com.boxoffice.entity.User;
import com.boxoffice.service.UserService;
import com.boxoffice.util.BoxOfficeResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserControllerImpl.class);
    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<?> addUser(User user) {
        if(userService.addUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAllBookings(String userId) {
        LOGGER.debug("received userId {}",userId);
        if (userService.isExist(userId)){
            return new ResponseEntity<>(userService.getAllBookings(userId),HttpStatus.OK);
        }
        return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }




}
