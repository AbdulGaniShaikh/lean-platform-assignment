package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.MessageDTO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.ReviewDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.InvalidParams;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Review;
import com.shaikhabdulgani.LeanPlatformAssignment.model.User;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.ReviewRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo repo;

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;


    public MessageDTO addReview(String mentorId, ReviewDAO request) throws NotFound, InvalidParams {
        Mentor mentor = mentorService.get(mentorId);
        User user = userService.get(request.getUserId());
        if (wordCount(request.getReviewText())>50){
            throw new InvalidParams("review should be under 50 words");
        }

        Review review = Review.builder()
                .reviewId(UniqueId.review())
                .mentor(mentor)
                .user(user)
                .timestamp(new Timestamp(new Date().getTime()))
                .reviewText(request.getReviewText())
                .build();


        repo.save(review);

        return new MessageDTO("new review was added");

    }

    public List<Review> getAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return repo.findAll(pageable).stream().toList();
    }

    private int wordCount(String str){
        String[] words = str.split(" ");
        return words.length;
    }
}
