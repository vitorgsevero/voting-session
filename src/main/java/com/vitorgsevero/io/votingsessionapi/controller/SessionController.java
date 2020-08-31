package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Session;
import com.vitorgsevero.io.votingsessionapi.repository.SessionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
@CrossOrigin
@Api(value = "All Session operations", tags = "Session Controller")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    @ApiOperation(value = "It creates a new Session", response = Session[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Session created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> createSession(@RequestBody Session session){
        return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Find all sessions", response = Session[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Session find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> getSession(){
        return new ResponseEntity<>(sessionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a session by id", response = Session[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Session created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> getSessionById(@PathVariable Long id){
        return new ResponseEntity<>(sessionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Session not found.")), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a session created", response = Session[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Session deleted"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<Void> deleteSession(@PathVariable(value = "id") Long id){
        Session session = sessionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Session not found."));
        sessionRepository.delete(session);
        return ResponseEntity.ok().build();
    }


}
