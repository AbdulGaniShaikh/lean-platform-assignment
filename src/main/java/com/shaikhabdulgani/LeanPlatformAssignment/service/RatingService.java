package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.MessageDTO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.RateDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Rating;
import com.shaikhabdulgani.LeanPlatformAssignment.model.User;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.RatingRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private RatingRepo repo;

    public MessageDTO rate(String mentorId, RateDAO req) throws NotFound, UnauthorizedAccess {
        User user = userService.get(req.getUserId());
        Mentor mentor = mentorService.get(mentorId);

        Rating rating;
        Optional<Rating> ratingOptional = repo.findByUserAndMentor(user,mentor);
        if (ratingOptional.isPresent())
            throw new UnauthorizedAccess("User already rated the mentor");

        rating = Rating.builder()
                .ratingId(UniqueId.rate())
                .user(user)
                .rating(req.getRating())
                .mentor(mentor)
                .timestamp(new Timestamp(new Date().getTime()))
                .build();

        int size = mentor.getRatings().size();
        float updatedRating = ((mentor.getRating()*size)+ req.getRating())/(size+1);
        repo.save(rating);
        mentorService.updateRating(mentor,updatedRating);

        return new MessageDTO("mentor with id "+mentorId+" was rated");

    }

}
