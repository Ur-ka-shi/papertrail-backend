package com.urvashik.papertrail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // 🚀 ADD THIS IMPORT!
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; // 🚀 ADD THIS IMPORT!
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; // 🚀 ADD THIS IMPORT!
import org.springframework.web.bind.annotation.RestController; // 🚀 ADD THIS IMPORT!

import com.urvashik.papertrail.entity.ResourceRequest;
import com.urvashik.papertrail.service.ResourceService;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "*")  
public class RequestController {

    private final ResourceService resourceService;

    public RequestController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // Student Submits a missing item request
    @PostMapping
    public ResourceRequest createRequest(@RequestBody ResourceRequest request) {
        request.setStatus("PENDING"); 
        return resourceService.saveRequest(request);
    }

    // Admin Views all submitted client requests
    @GetMapping
    public List<ResourceRequest> viewAllRequests() {
        return resourceService.getAllRequests();
    }

    // 🚀 NEW: Admin drops/clears a handled student request item out of the database matrix
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        try {
            resourceService.deleteRequest(id); 
            return ResponseEntity.ok("Request cleared from database registry successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to clear request record: " + e.getMessage());
        }
    }
}