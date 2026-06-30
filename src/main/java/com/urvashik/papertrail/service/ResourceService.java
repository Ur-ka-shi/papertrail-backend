package com.urvashik.papertrail.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.urvashik.papertrail.entity.AcademicResource;
import com.urvashik.papertrail.entity.ResourceRequest; 
import com.urvashik.papertrail.repository.RequestRepository;
import com.urvashik.papertrail.repository.ResourceRepository;   

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final RequestRepository requestRepository; 

    public ResourceService(ResourceRepository resourceRepository, RequestRepository requestRepository) {
        this.resourceRepository = resourceRepository;
        this.requestRepository = requestRepository;
    }

    public AcademicResource saveResource(AcademicResource resource) {
        return resourceRepository.save(resource);
    }

    public List<AcademicResource> getAllResources() {
        return resourceRepository.findAll();
    }

    public List<AcademicResource> searchResources(String branch, Integer semester, String type, Integer year) {
        String searchBranch = (branch != null && !branch.trim().isEmpty()) ? branch : null;
        String searchType = (type != null && !type.trim().isEmpty()) ? type : null;

        return resourceRepository.filterResources(searchBranch, semester, searchType, year);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    public ResourceRequest saveRequest(ResourceRequest request) {
        return requestRepository.save(request);
    }

    public List<ResourceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<ResourceRequest> getPendingRequests() {
        return requestRepository.findByStatus("PENDING");
    }

    // 🚀 NEW: Expose database drop mechanism for student request records
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}