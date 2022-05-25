package com.vldby.postmail.repository;

import com.vldby.postmail.entity.PostalOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostalOfficeRepo extends JpaRepository<PostalOffice, UUID> {
    PostalOffice findByIndex(String index);
}