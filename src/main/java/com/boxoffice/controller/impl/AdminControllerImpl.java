package com.boxoffice.controller.impl;

import com.boxoffice.controller.AdminController;
import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;
import com.boxoffice.service.AdminService;
import com.boxoffice.service.UserService;
import com.boxoffice.util.BoxOfficeResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllerImpl implements AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<?> addMovie(String id, Movie movie) {
        if(adminService.isValidAdmin(id)){
            if(adminService.addMovie(movie)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<?> addTheatre(String id, Theatre theatre) {
        if(userService.isExist(id)){
            theatre.setOwnerId(id);
            if(adminService.addTheatre(theatre)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
            return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> addShow(String id, MovieShow movieShow) {
        if(adminService.isValidShow(id,movieShow.getTheatreId())){
            if(adminService.addShow(movieShow)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(BoxOfficeResponseUtil.getErrorResponse(),HttpStatus.BAD_REQUEST);
    }


}
