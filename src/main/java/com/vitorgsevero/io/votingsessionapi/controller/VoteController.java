package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Associate;
import com.vitorgsevero.io.votingsessionapi.model.Vote;
import com.vitorgsevero.io.votingsessionapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(voteRepository.findAll());
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote){
        return voteRepository.save(vote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findVoteById(@PathVariable(value = "id") Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vote not found."));
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable(value = "id") Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Vote not found."));
        voteRepository.delete(vote);
        return ResponseEntity.ok().build();
    }

}
