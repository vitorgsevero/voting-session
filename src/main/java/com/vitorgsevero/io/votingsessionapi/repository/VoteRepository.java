package com.vitorgsevero.io.votingsessionapi.repository;

import com.vitorgsevero.io.votingsessionapi.model.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
