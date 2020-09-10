package com.core.webapi.model;

import com.core.webapi.listeners.AlterationInfoListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AlterationInfoListener.class)
public class AlterableEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by", length = 64, nullable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    @JsonIgnore
    private Date dateCreated;

    @LastModifiedBy
    @Column(name = "updated_by", length = 64, nullable = true)
    @JsonIgnore
    private String updatedBy;

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_updated", nullable = true)
    @JsonIgnore
    private Date dateUpdated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public AlterableEntity setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;

    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public AlterableEntity setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public AlterableEntity setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;

    }
    public String getCreatedBy() {
        return createdBy;
    }

    public AlterableEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlterableEntity)) return false;
        AlterableEntity that = (AlterableEntity) o;
        return new EqualsBuilder().
                append(this.dateUpdated, that.dateUpdated).
                append(this.updatedBy, that.updatedBy).
                append(this.dateCreated, that.dateCreated).
                append(this.createdBy, that.createdBy).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31).
                append(this.createdBy).
                append(this.dateCreated).
                append(this.updatedBy).
                append(this.dateUpdated).
                toHashCode();
    }
}
