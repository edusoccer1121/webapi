package com.core.webapi.model;

import com.core.webapi.model.interfaces.MultiLanguageBase;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MultiLanguageLabel implements MultiLanguageBase {

    @Column(name = "label_en", length = 150)
    private String english;

    @Column(name = "label_es", length = 150)
    private String spanish;

    public MultiLanguageLabel() {
    }

    public MultiLanguageLabel(String spanish, String english) {
        this.spanish = spanish;
        this.english = english;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

    @Override
    public String toString() {
        return "MultiLanguageLabel{" +
                "english='" + english + '\'' +
                ", spanish='" + spanish + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiLanguageLabel)) return false;
        MultiLanguageLabel that = (MultiLanguageLabel) o;
        return new EqualsBuilder().
                append(this.english, that.english).
                append(this.spanish, that.spanish).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31).
                append(this.spanish).
                append(this.english).
                toHashCode();
    }
}
