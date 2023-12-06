package com.shaikhabdulgani.LeanPlatformAssignment.controller;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.MessageDTO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.ReviewDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.RateDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.InvalidParams;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.service.MentorService;
import com.shaikhabdulgani.LeanPlatformAssignment.service.RatingService;
import com.shaikhabdulgani.LeanPlatformAssignment.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("mentor")
    public ResponseEntity<Mentor> save(@RequestBody @Valid SaveUserDAO req){
        return ResponseEntity.ok(mentorService.save(req));
    }

    @GetMapping("mentors/{mentorId}")
    public ResponseEntity<Mentor> get(@PathVariable String mentorId) throws NotFound {
        return ResponseEntity.ok(mentorService.get(mentorId));
    }

    @GetMapping("mentors")
    public ResponseEntity<List<Mentor>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "rating",defaultValue = "0",required = false) int rating
    ) throws InvalidParams {
        return ResponseEntity.ok(mentorService.getAll(pageNumber, pageSize, rating));
    }

    @PostMapping("mentors/{mentorId}/rate")
    public ResponseEntity<MessageDTO> rateMentor(
            @RequestBody @Valid RateDAO req,
            @PathVariable String mentorId
    ) throws NotFound, UnauthorizedAccess {
        return ResponseEntity.ok(ratingService.rate(mentorId,req));
    }

    @PostMapping("mentors/{mentorId}/review")
    public ResponseEntity<MessageDTO> addReview(
            @RequestBody @Valid ReviewDAO request,
            @PathVariable String mentorId
    ) throws InvalidParams, NotFound {
        return ResponseEntity.ok(reviewService.addReview(mentorId,request));
    }
}
