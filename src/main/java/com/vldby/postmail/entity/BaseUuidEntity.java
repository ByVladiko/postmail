package com.vldby.postmail.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vldby.postmail.entity.listener.BaseUuidEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(BaseUuidEntityListener.class)
public class BaseUuidEntity {

    @Id
    @Column(name = "ID")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @Column(name = "CREATE_TS")
    private Date createTs;

    @JsonIgnore
    @Column(name = "UPDATE_TS")
    private Date updateTs;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }
}
