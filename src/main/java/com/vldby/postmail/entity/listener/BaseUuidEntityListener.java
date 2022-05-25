package com.vldby.postmail.entity.listener;

import com.vldby.postmail.entity.BaseUuidEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class BaseUuidEntityListener {

    @PrePersist
    public void prePersist(BaseUuidEntity entity) {
        entity.setCreateTs(new Date());
    }

    @PreUpdate
    public void preUpdate(BaseUuidEntity entity) {
        entity.setUpdateTs(new Date());
    }
}
