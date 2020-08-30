package com.vitorgsevero.io.votingsessionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitorgsevero.io.votingsessionapi.auditing.AssociateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Associate extends AssociateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int scheduleCounter;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "associate")
    @JoinColumn(name="id", nullable = false)
    @JsonIgnore
    private Session session;

}
