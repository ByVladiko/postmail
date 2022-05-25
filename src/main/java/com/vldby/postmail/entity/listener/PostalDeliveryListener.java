package com.vldby.postmail.entity.listener;

import com.vldby.postmail.entity.PostalDelivery;
import com.vldby.postmail.repository.PostalDeliveryRepo;
import com.vldby.postmail.utils.BeanUtil;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;

@Component
public class PostalDeliveryListener {

    @PrePersist
    public void prePersist(PostalDelivery entity) {
        PostalDeliveryRepo postalDeliveryRepo = BeanUtil.getBean(PostalDeliveryRepo.class);
        PostalDelivery postalDelivery = postalDeliveryRepo.findFirstByIndexOrderByCreateTsDesc(entity.getIndex());
        String identifier = postalDelivery == null
                ? createFirstIdentifier(entity)
                : incrementIdentifier(postalDelivery.getIdentifier());
        entity.setIdentifier(identifier);
    }

    private String incrementIdentifier(String identifier) {
        String[] split = identifier.split("-");
        int id = Integer.parseInt(split[0]);
        return String.format("%d-%s", ++id, split[1]);
    }

    private String createFirstIdentifier(PostalDelivery postalDelivery) {
        return "1-" + postalDelivery.getIndex();
    }
}
