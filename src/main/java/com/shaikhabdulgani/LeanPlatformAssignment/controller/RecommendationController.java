package com.shaikhabdulgani.LeanPlatformAssignment.controller;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendationResponse;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Recommendation;
import com.shaikhabdulgani.LeanPlatformAssignment.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommendations/{recommendationId}")
    public ResponseEntity<RecommendationResponse> recommendation(@PathVariable String recommendationId) throws NotFound {
        return ResponseEntity.ok(recommendationService.getById(recommendationId));
    }

}
