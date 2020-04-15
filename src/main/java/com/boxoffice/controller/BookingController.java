package com.boxoffice.controller;


import com.boxoffice.entity.MovieTicket;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingController {
    @PostMapping(path= "/api/v1/booking/user/{userId}", consumes = "application/json", produces = "application/json")
    @ApiOperation("This is to request a booking, requires a registered user and List of seats with show Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Ticket Booked  successfully."),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Movie addition failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> bookTicket(@ApiParam(value = "UserId for Operation.", required = true)@PathVariable("userId") String userId,
                               @ApiParam(value = "Ticket Request JSON for addition.", required = true)@RequestBody MovieTicket movieTicket);


}
