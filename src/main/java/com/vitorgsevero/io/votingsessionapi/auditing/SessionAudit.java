package com.vitorgsevero.io.votingsessionapi.auditing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at,updated_at"}, allowGetters = true)
public class SessionAudit {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", nullable = false, updatable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name="created_by", nullable = false)
    @CreatedDate
    private String createdBy;

    @Column(name="updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;

}
