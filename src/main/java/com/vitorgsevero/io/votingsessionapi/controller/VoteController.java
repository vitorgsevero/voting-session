package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Schedule;
import com.vitorgsevero.io.votingsessionapi.model.Vote;
import com.vitorgsevero.io.votingsessionapi.repository.VoteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
@Api(value = "All Vote operations", tags = "Vote Controller")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    @ApiOperation(value = "Find all votes", response = Vote[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Votes find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(voteRepository.findAll());
    }

    @PostMapping
    @ApiOperation(value = "It creates a new Vote", response = Vote[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vote created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public Vote createVote(@RequestBody Vote vote){
        return voteRepository.save(vote);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a vote by id", response = Vote[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vote find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> findVoteById(@PathVariable(value = "id") Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vote not found."));
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a vote done", response = Schedule[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vote deleted"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<Void> deleteVote(@PathVariable(value = "id") Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vote not found."));
        voteRepository.delete(vote);
        return ResponseEntity.ok().build();
    }

}
