package com.core.webapi.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "addresses")
public class Addresses extends AutoIdBasedEntity implements Serializable {
    @Basic
    @Column(name = "line_1", nullable = false, length = 512)
    private String line_1;
    @Basic
    @Column(name = "line_2", nullable = true, length = 512)
    private String line_2;
    @Basic
    @Column(name = "zip_code", nullable = false)
    private Long zip_code;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "country_code")
    @RestResource(path = "addressCountry", rel="country")
    private Country country;

    @ManyToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Basic
    @Column(name = "state", nullable = false)
    private Long state;
}
