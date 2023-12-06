package com.shaikhabdulgani.LeanPlatformAssignment.repo;

import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MentorRepoTest {

    @Autowired
    private MentorRepo mentorRepo;

    Mentor mentor;

    @BeforeEach
    void setUp() {
        mentor = Mentor.builder()
                .mentorId("men_1")
                .username("mentor1")
                .rating(5)
                .build();

        mentorRepo.save(mentor);

    }

    @Test
    void testGetByRating_Found() {

        List<Mentor> result = mentorRepo.getByRating(5, PageRequest.of(0,10));
        assertThat(result.get(0).getMentorId()).isEqualTo(mentor.getMentorId());

    }

    @Test
    void testGetByRating_NotFound() {

        List<Mentor> result = mentorRepo.getByRating(1, PageRequest.of(0,10));
        assertThat(result.isEmpty()).isTrue();

    }
}