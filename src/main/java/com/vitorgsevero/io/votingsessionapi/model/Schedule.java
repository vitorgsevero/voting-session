package com.vitorgsevero.io.votingsessionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitorgsevero.io.votingsessionapi.auditing.ScheduleAudit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Schedule extends ScheduleAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", nullable = false)
    @JsonIgnore
    private Session session;

}
