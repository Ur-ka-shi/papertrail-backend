package com.urvashik.papertrail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urvashik.papertrail.entity.ResourceRequest;
import com.urvashik.papertrail.service.ResourceService;

@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "*")  // Allows your React app to securely query these endpoints
public class RequestController {

    private final ResourceService resourceService;

    public RequestController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // Student Submits a missing item request
    @PostMapping
    public ResourceRequest createRequest(@RequestBody ResourceRequest request) {
        // Sets status to pending explicitly to guarantee override payload values
        request.setStatus("PENDING"); 
        return resourceService.saveRequest(request);
    }

    // Admin Views all submitted client requests
    @GetMapping
    public List<ResourceRequest> viewAllRequests() {
        return resourceService.getAllRequests();
    }
}