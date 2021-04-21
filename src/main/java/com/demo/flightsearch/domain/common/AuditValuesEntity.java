package com.demo.flightsearch.domain.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * The type Audit values entity.
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AuditValuesEntity {
    private static final ZoneId UTC = ZoneId.of("UTC");

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "timestamptz"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @LastModifiedDate
    @Column(
            nullable = false,
            columnDefinition = "timestamptz"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    /**
     * Instantiates a new Audit values entity.
     */
    public AuditValuesEntity() {
    }

    /**
     * Gets last updated.
     *
     * @return the last updated
     */
    public ZonedDateTime getLastUpdated() {
        return ZonedDateTime.ofInstant(this.lastUpdated.toInstant(), UTC);
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public ZonedDateTime getDateCreated() {
        return ZonedDateTime.ofInstant(this.dateCreated.toInstant(), UTC);
    }
}
