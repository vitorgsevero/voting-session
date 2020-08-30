package com.vitorgsevero.io.votingsessionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitorgsevero.io.votingsessionapi.auditing.SessionAudit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Session extends SessionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sessionName;

    private int sessionTime = 1;

    private boolean sessionStatus;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "session")
    @JsonIgnore
    private Schedule schedule;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", nullable = false)
    @JsonIgnore
    private Associate associate;


}
