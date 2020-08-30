package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Associate;
import com.vitorgsevero.io.votingsessionapi.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associates")
public class AssociateController {

    @Autowired
    private AssociateRepository associateRepository;

    @PostMapping
    public Associate createAssociate(@RequestBody Associate associate){
        return associateRepository.save(associate);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(associateRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAssociateById(@PathVariable(value = "id") Long id){
        Associate associate = associateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Associate not found."));
        return new ResponseEntity<>(associate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociate(@PathVariable(value = "id") Long id){
        Associate associate = associateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Associate not found."));
        associateRepository.delete(associate);
        return ResponseEntity.ok().build();
    }

}
