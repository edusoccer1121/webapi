package com.core.webapi.model;

import com.core.webapi.model.interfaces.IdentifiableEntity;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@MappedSuperclass
public class AutoIdBasedEntity extends AlterableEntity implements IdentifiableEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Override
    public Long getId() {
        return id;
    }
    public AutoIdBasedEntity setId(Long id) {
        this.id = id;
        return this;
    }
    @JsonIgnore
    public String getIdString(){ return id == null ? "" : id.toString();}
}
