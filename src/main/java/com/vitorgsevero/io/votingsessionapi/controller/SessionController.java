package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Session;
import com.vitorgsevero.io.votingsessionapi.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
@CrossOrigin
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<?> createSession(@RequestBody Session session){
        return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getSession(){
        return new ResponseEntity<>(sessionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSesionById(@PathVariable Long id){
        return new ResponseEntity<>(sessionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Session not found.")), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable(value = "id") Long id){
        Session session = sessionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Session not found."));
        sessionRepository.delete(session);
        return ResponseEntity.ok().build();
    }


}
