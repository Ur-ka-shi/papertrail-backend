package com.urvashik.papertrail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.urvashik.papertrail.entity.AcademicResource;

@Repository
public interface ResourceRepository extends JpaRepository<AcademicResource, Long> {

    List<AcademicResource> findByBranchAndSemesterAndType(String branch, Integer semester, String type);

    @Query("SELECT r FROM AcademicResource r WHERE " +
           "(:branch IS NULL OR r.branch = :branch) AND " +
           "(:semester IS NULL OR r.semester = :semester) AND " +
           "(:type IS NULL OR r.type = :type) AND " +
           "(:year IS NULL OR r.year = :year)")
    List<AcademicResource> filterResources(@Param("branch") String branch, 
                                           @Param("semester") Integer semester, 
                                           @Param("type") String type,
                                           @Param("year") Integer year);

    List<AcademicResource> findTop5ByOrderByDownloadsDesc();
}