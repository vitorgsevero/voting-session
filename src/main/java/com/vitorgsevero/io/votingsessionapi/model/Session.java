package com.vitorgsevero.io.votingsessionapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Session extends SessionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String sessionName;

    private int sessionTime;

    private boolean sessionStatus;

}