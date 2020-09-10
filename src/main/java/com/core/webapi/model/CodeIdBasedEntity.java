package com.core.webapi.model;

import com.core.webapi.listeners.AlterationInfoListener;
import com.core.webapi.model.interfaces.IdentifiableEntity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners({AlterationInfoListener.class})

public abstract class CodeIdBasedEntity extends AlterableEntity implements IdentifiableEntity<String> {

    @Id
    @Column(name="code", nullable = false)
    private String code;

    public void setCode(String code){ this.code = code;}
    public String getCode() {
        return code;
    }
    @Override
    public String getId() {
        return code;
    }
}
