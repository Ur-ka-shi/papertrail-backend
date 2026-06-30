package com.urvashik.papertrail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urvashik.papertrail.entity.ResourceRequest;

@Repository
public interface RequestRepository extends JpaRepository<ResourceRequest, Long> {
    // Custom finder method to let admins filter down to just open requests
    List<ResourceRequest> findByStatus(String status);
}