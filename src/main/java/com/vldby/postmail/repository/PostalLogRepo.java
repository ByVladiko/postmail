package com.vldby.postmail.repository;

import com.vldby.postmail.entity.PostalDelivery;
import com.vldby.postmail.entity.PostalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostalLogRepo extends JpaRepository<PostalLog, UUID> {
    List<PostalLog> getPostalLogByPostalDeliveryOrderByCreateTs(PostalDelivery postalDelivery);
    PostalLog findFirstByPostalDeliveryIdentifierOrderByCreateTsDesc(String identifier);
}