package com.boxoffice.controller;

import com.boxoffice.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping
public interface UserController {

    @PostMapping(path= "/api/v1/user/register/", consumes = "application/json", produces = "application/json")
    @ApiOperation("This is to add a new user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "User has been successfully queued for deletion."),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "User creation failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> addUser(@ApiParam(value = "User JSON for a user  edition.", required = true) @RequestBody User user);


    @GetMapping(path= "/api/v1/user/{userId}/bookings/", produces = "application/json")
    @ApiOperation("Get all the bookings made my the given user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user bookings"),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Request failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> getAllBookings(@ApiParam(value = "UserId for Operation.", required = true) @PathVariable("userId") String userId);

}
