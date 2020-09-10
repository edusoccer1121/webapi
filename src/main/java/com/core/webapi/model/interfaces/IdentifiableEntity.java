package com.core.webapi.model.interfaces;

import java.io.Serializable;

public interface IdentifiableEntity<IDTYPE extends Serializable> {
    public IDTYPE getId();
}
