package com.vitorgsevero.io.votingsessionapi.controller;

import com.vitorgsevero.io.votingsessionapi.exception.ResourceNotFoundException;
import com.vitorgsevero.io.votingsessionapi.model.Schedule;
import com.vitorgsevero.io.votingsessionapi.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping
    public Schedule addSchedule(@RequestBody Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAll(){
        return ResponseEntity.ok(scheduleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findScheduleById(@PathVariable(value = "id") Integer id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Schedule not found."));
        return ResponseEntity.ok().body(schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable(value = "id") Integer id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Schedule not found."));
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok().build();
    }



}
