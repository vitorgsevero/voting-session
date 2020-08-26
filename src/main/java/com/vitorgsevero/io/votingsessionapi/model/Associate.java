package com.vitorgsevero.io.votingsessionapi.model;

import com.vitorgsevero.io.votingsessionapi.auditing.AssociateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Associate extends AssociateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
