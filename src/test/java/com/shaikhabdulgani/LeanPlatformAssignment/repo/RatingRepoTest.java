package com.shaikhabdulgani.LeanPlatformAssignment.repo;

import com.shaikhabdulgani.LeanPlatformAssignment.model.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RatingRepoTest {

    @Autowired
    private RatingRepo repo;

    @Autowired
    private MentorRepo mentorRepo;

    @Autowired
    private UserRepo userRepo;

    Rating rating;
    User user;
    Mentor mentor;


    @BeforeEach
    void setUp() {

        mentor = Mentor.builder()
                .mentorId("men_1")
                .username("mentor1")
                .rating(0)
                .build();

        user = User.builder()
                .userId("user_1")
                .username("user1")
                .build();

        mentorRepo.save(mentor);
        userRepo.save(user);

        rating =
                Rating.builder()
                        .ratingId("rec_1")
                        .mentor(mentor)
                        .user(user)
                        .rating(4)
                        .timestamp(new Timestamp(new Date().getTime()))
                        .build();

        repo.save(rating);
    }

    @AfterEach
    void tearDown() {
        repo.deleteById(rating.getRatingId());
        userRepo.deleteById(user.getUserId());
        mentorRepo.deleteById(mentor.getMentorId());
    }

    @Test
    void testFindByStudentAndMentor_Found() {

        Optional<Rating> result = repo.findByUserAndMentor(user,mentor);
        AssertionsForClassTypes.assertThat(result.get()).isEqualTo(rating);

    }
    @Test
    void testFindByStudentAndMentor_NotFound() {

        Optional<Rating> result = repo.findByUserAndMentor(User.builder().userId("5353").build(), mentor);
        AssertionsForClassTypes.assertThat(result.isEmpty()).isTrue();

    }

}