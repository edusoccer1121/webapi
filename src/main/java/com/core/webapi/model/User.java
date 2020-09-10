package com.core.webapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity()
@Audited()
@Table(name="users" )
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements UserDetails,Serializable {
    public User() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(1L, "USER"));
        this.authorities = authorities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    private String username;

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 128)
    private String middleName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "second_last_name", length = 64)
    private String secondLastName;
    @Basic
    @Column(name = "password", nullable = true, length = 200)
    private String password;

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Basic
    @Column(name = "user_type", nullable = true, length = 3)
    private String usertype;

    @Basic
    @Column(name = "account_expired", nullable = true)
    private Boolean accountExpired;

    @Basic
    @Column(name = "account_locked", nullable = true)
    private Boolean accountLocked;

    @Basic
    @Column(name = "credentials_expired", nullable = true)
    private Boolean credentialsExpired;

    @Basic
    @Column(name = "enabled", nullable = true)
    private Boolean enabled;

    @Transient
    private Collection<Authority> authorities;

    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sec_user_granted_roles",
        joinColumns = {@JoinColumn(name = "user_id" )},
        inverseJoinColumns = {@JoinColumn(name = "role_code")}
    )
    private Set<Role> grantedRoles;


    @OneToMany(mappedBy = "owner")
    @NotAudited()
    private List<Addresses> addresses;


    public Collection<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired != null && !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked != null && !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired != null && !credentialsExpired;

    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public List<Role> getGrantedRoles() {
        if (this.grantedRoles == null)
            return Collections.emptyList();
        return new ArrayList<>(this.grantedRoles);
    }

    public void setGrantedRoles(List<Role> grantedRoles) {
        if (this.grantedRoles == null)
            this.grantedRoles = new HashSet<>();

        if (grantedRoles != null)
            this.grantedRoles.addAll(grantedRoles);
    }
}