package com.vldby.postmail.repository;

import com.vldby.postmail.entity.PostalDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostalDeliveryRepo extends JpaRepository<PostalDelivery, UUID> {
    PostalDelivery findByIdentifier(String identifier);
    PostalDelivery findFirstByIndexOrderByCreateTsDesc(String index);
}