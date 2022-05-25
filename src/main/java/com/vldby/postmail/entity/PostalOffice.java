package com.vldby.postmail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "PostalOffice")
@Table(name = "POSTAL_OFFICE")
public class PostalOffice extends BaseUuidEntity {

    @Column(name = "INDEX")
    private String index;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS", length = 1000)
    private String address;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
