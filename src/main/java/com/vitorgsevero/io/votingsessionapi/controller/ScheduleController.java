package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Schedule;
import com.vitorgsevero.io.votingsessionapi.repository.ScheduleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@Api(value = "All Schedule operations", tags = "Schedule Controller")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping
    @ApiOperation(value = "It creates a new Schedule", response = Schedule[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule created"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public Schedule addSchedule(@RequestBody Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    @GetMapping
    @ApiOperation(value = "To find all schedules", response = Schedule[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<List<Schedule>> findAll(){
        return ResponseEntity.ok(scheduleRepository.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "To find a schedule by ID", response = Schedule[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule by ID find"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<Schedule> findScheduleById(@PathVariable(value = "id") Integer id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Schedule not found."));
        return ResponseEntity.ok().body(schedule);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a specified schedule", response = Schedule[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete a schedule by Id"),
            @ApiResponse(code = 403, message = "You don't have permission to access this resource"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "An exception occurred")
    })
    public ResponseEntity<Void> deleteSchedule(@PathVariable(value = "id") Integer id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Schedule not found."));
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok().build();
    }

}
