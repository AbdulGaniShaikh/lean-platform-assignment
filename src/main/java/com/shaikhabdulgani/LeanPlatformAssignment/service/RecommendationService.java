package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendationResponse;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import com.shaikhabdulgani.LeanPlatformAssignment.model.*;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.RecommendationRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class RecommendationService {

    @Autowired
    RecommendationRepo repo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MentorService mentorService;



    public RecommendationResponse recommend(String studentId, RecommendDAO req) throws NotFound, UnauthorizedAccess {

        Student student = studentService.get(studentId);
        Mentor mentor = mentorService.get(req.getMentorId());

        Optional<Recommendation> recommendationOptional = repo.findByStudentAndMentor(student,mentor);
        if (recommendationOptional.isPresent())
            throw new UnauthorizedAccess("mentor already recommended student");

        Recommendation recommendation =
                Recommendation.builder()
                        .recommendationId(UniqueId.recommendation())
                        .student(student)
                        .mentor(mentor)
                        .timestamp(new Timestamp(new Date().getTime()))
                        .build();
        String id = repo.save(recommendation).getRecommendationId();
        return repo.getRecommendation(id).get();
    }

    public RecommendationResponse getById(String recommendationId) throws NotFound {

        Optional<RecommendationResponse> student = repo.getRecommendation(recommendationId);
        return student.orElseThrow(()->new NotFound("Recommendation not present by id "+recommendationId));

    }
}
