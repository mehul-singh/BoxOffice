package com.boxoffice.controller;

import com.boxoffice.entity.Movie;
import com.boxoffice.entity.MovieShow;
import com.boxoffice.entity.Theatre;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface AdminController {

    @PostMapping(path= "/api/v1/admin/{id}/movie/", consumes = "application/json", produces = "application/json")
    @ApiOperation("This is to add a new Movie, you have to be a theatre owner to add a movie")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Movie added successfully."),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Movie addition failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> addMovie(@ApiParam(value = "UserId for Operation.", required = true)@PathVariable("id") String id,
                               @ApiParam(value = "Movie Info JSON for addition.", required = true)@RequestBody Movie movie);



    @ApiOperation("This is to add a new Theatre, only existing user can add a theatre")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Movie added successfully."),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Movie addition failed. Please try again later or contact support if problem persists.")
            }
    )
    @PostMapping(path= "/api/v1/admin/{id}/theatre/", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> addTheatre(@ApiParam(value = "UserId for Operation.", required = true)@PathVariable("id") String id,
                                 @ApiParam(value = "Theatre Info JSON for addition", required = true)@RequestBody Theatre theatre);
    @ApiOperation("This is to add a new show for a given movie and a theatre")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Movie added successfully."),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Movie addition failed. Please try again later or contact support if problem persists.")
            }
    )
    @PostMapping(path= "/api/v1/admin/{id}/show/", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> addShow(@ApiParam(value = "UserId for Operation.", required = true) @PathVariable("id") String id,
                              @ApiParam(value = "Show JSON for Addition.", required = true)@RequestBody MovieShow movieShow);
}
