package com.core.webapi.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="countries")
@AttributeOverride(name = "code", column = @Column(name = "country_code", length = 32, nullable = false))
public class Country extends CodeIdBasedEntity{

}
