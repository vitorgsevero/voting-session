package com.vitorgsevero.io.votingsessionapi.repository;

import com.vitorgsevero.io.votingsessionapi.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository <Session, Long> {
}
