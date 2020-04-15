package com.boxoffice.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface SearchController {

    @GetMapping(path= "/api/v1/search/cities/",  produces = "application/json")
    @ApiOperation("Get all the cities where BoXOffice operates")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user bookings"),
                    @ApiResponse(code = 500, message = "Request failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> getAllActiveCities();


    @GetMapping(path= "/api/v1/search/movie/{cityId}/",  produces = "application/json")
    @ApiOperation("Get all Movies in a given City")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user bookings"),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Request failed. Please try again later or contact support if problem persists.")
            }
    )
    ResponseEntity<?> getAllShowsInCity(@ApiParam(value = "CityId for Search.", required = true) @PathVariable("cityId") String cityId);

    @ApiOperation("Get all shows of a chosen movie in a given city")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user bookings"),
                    @ApiResponse(code = 400, message = "Invalid or null data "),
                    @ApiResponse(code = 500, message = "Request failed. Please try again later or contact support if problem persists.")
            }
    )
    @GetMapping(path= "/api/v1/search/movie/{movieId}/city/{cityId}/", produces = "application/json")
    ResponseEntity<?> getAllShowsForMovie(@ApiParam(value = "MovieId for Search.", required = true)@PathVariable("movieId") String movieId,
                                          @ApiParam(value = "CityId for Search.", required = true) @PathVariable("cityId") String cityId);

    @ApiOperation("Get all movies featured ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All user bookings"),
                    @ApiResponse(code = 500, message = "Request failed. Please try again later or contact support if problem persists.")
            }
    )
    @GetMapping(path= "/api/v1/search/all/",  produces = "application/json")
    ResponseEntity<?> getAllMovies();
}
