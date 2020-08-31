package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Associate;
import com.vitorgsevero.io.votingsessionapi.repository.AssociateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associates")
@Api(value = "All Associate operations", tags = "Associate Controller")
public class AssociateController {

    @Autowired
    private AssociateRepository associateRepository;

    @PostMapping
    @ApiOperation(value = "It creates a new Associate", response = Associate[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Associate created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public Associate createAssociate(@RequestBody Associate associate){
        return associateRepository.save(associate);
    }

    @GetMapping
    @ApiOperation(value = "Return all associates", response = Associate[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all associates created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(associateRepository.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "To find a associate by ID", response = Associate[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Associate by ID find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<?> findAssociateById(@PathVariable(value = "id") Long id){
        Associate associate = associateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Associate not found."));
        return new ResponseEntity<>(associate, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specified associate", response = Associate[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete an associate by Id"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociate(@PathVariable(value = "id") Long id){
        Associate associate = associateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Associate not found."));
        associateRepository.delete(associate);
        return ResponseEntity.ok().build();
    }

}
