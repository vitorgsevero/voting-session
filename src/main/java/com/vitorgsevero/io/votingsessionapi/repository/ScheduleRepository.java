package com.vitorgsevero.io.votingsessionapi.repository;

import com.vitorgsevero.io.votingsessionapi.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
