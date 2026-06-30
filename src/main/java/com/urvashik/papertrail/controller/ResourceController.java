package com.urvashik.papertrail.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.urvashik.papertrail.entity.AcademicResource;
import com.urvashik.papertrail.service.ResourceService;

@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "*") // 🚀 Allows free-tier Vercel and Render to communicate without CORS blocks
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // --- 1. GET ALL RESOURCES ---
    @GetMapping("/all")
    public ResponseEntity<List<AcademicResource>> getAllResources() {
        try {
            List<AcademicResource> resources = resourceService.getAllResources();
            return ResponseEntity.ok(resources);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // --- 2. MULTI-BRANCH LIGHTWEIGHT LINK UPLOAD ---
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResource(
            @RequestParam("title") String title,
            @RequestParam("branch") List<String> branches,
            @RequestParam("semester") Integer semester,
            @RequestParam("subject") String subject,
            @RequestParam("year") String yearString,
            @RequestParam("type") String type,
            @RequestParam("examType") String examType,
            @RequestParam("fileUrl") String fileUrl) {

        try {
            if (fileUrl == null || fileUrl.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Document URL address cannot be empty.");
            }

            // Parse year string (e.g., "2025-26") into numeric format for database consistency
            int numericYear = 2026;
            if (yearString != null && yearString.contains("-")) {
                numericYear = Integer.parseInt(yearString.split("-")[0]);
            } else if (yearString != null) {
                numericYear = Integer.parseInt(yearString);
            }

            // Loop through every single checked branch box sent by the frontend array
            for (String branchName : branches) {
                AcademicResource resource = new AcademicResource();
                resource.setTitle(title);
                resource.setBranch(branchName);
                resource.setSemester(semester);
                resource.setSubject(subject);
                resource.setYear(numericYear); 
                resource.setType(type);
                resource.setExamType(examType);
                resource.setFileUrl(fileUrl); // 🔥 Stores Google Drive URL text string directly
                resource.setDownloads(0);

                // Pass to your service layer to handle the direct database save operation
                resourceService.saveResource(resource);
            }

            return ResponseEntity.ok("Resource links indexed across all target departments successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing upload: " + e.getMessage());
        }
    }

    // --- 3. FILTER OR LEAN SEARCH ---
    @GetMapping
    public ResponseEntity<List<AcademicResource>> getResources(
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer year) {

        String searchBranch = (branch != null && !branch.trim().isEmpty() && !branch.equals("ALL")) ? branch : null;
        String searchType = (type != null && !type.trim().isEmpty() && !type.equals("ALL")) ? type : null;

        if (searchBranch == null && semester == null && searchType == null && year == null) {
            return ResponseEntity.ok(resourceService.getAllResources());
        }

        List<AcademicResource> filtered = resourceService.searchResources(searchBranch, semester, searchType, year);
        return ResponseEntity.ok(filtered);
    }
    
    // --- 4. GET SINGLE RESOURCE ---
    @GetMapping("/{id}")
    public AcademicResource getResourceById(@PathVariable Long id) {
        return resourceService.getAllResources().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));
    }

    // --- 5. DELETE RESOURCE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) {
        try {
            resourceService.deleteResource(id);
            return ResponseEntity.ok("Resource with ID " + id + " has been successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed: " + e.getMessage());
        }
    }   
}