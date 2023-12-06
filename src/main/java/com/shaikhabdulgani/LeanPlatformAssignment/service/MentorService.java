package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.InvalidParams;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.MentorRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRepo repo;


    public Mentor save(@Valid SaveUserDAO req) {
        return repo.save(
                Mentor.builder()
                        .mentorId(UniqueId.mentor())
                        .username(req.getUsername())
                        .build()
        );
    }

    public Mentor get(String mentorId) throws NotFound {
        Optional<Mentor> mentor = repo.findById(mentorId);
        return mentor.orElseThrow(()->new NotFound("mentor not present by id "+mentorId));
    }

    public List<Mentor> getAll(int pageNumber, int pageSize, int rating) throws InvalidParams {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (rating==0)
            return repo.findAll(pageable).stream().toList();
        if (rating < 1 || rating > 5) {
            throw new InvalidParams("rating should be between 1 and 5");
        }
        return repo.getByRating(rating,pageable);
    }

    public Mentor updateRating(Mentor mentor, float currRating) {
        mentor.setRating(currRating);
        return repo.save(mentor);
    }
}
