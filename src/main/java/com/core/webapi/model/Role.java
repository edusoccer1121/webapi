package com.core.webapi.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name="sec_role")
@AttributeOverride(name = "code", column = @Column(name = "role_code", length = 32, nullable = false))
public class Role extends CodeIdBasedEntity{
    @Embedded
    private MultiLanguageLabel label;

    public MultiLanguageLabel getLabel() {
        return label;
    }

    public void setLabel(MultiLanguageLabel label) {
        this.label = label;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role that = (Role) o;
        return new EqualsBuilder().
                appendSuper(super.equals(that)).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31).
                appendSuper(super.hashCode()).
                toHashCode();
    }
}
