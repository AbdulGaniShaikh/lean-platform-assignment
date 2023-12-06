package com.shaikhabdulgani.LeanPlatformAssignment.repo;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendationResponse;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Recommendation;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.AssertionsForClassTypes;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@DataJpaTest
class RecommendationRepoTest {

    @Autowired
    private RecommendationRepo repo;

    @Autowired
    private MentorRepo mentorRepo;

    @Autowired
    private StudentRepo studentRepo;

    Recommendation recommendation;
    Student student;
    Mentor mentor;


    @BeforeEach
    void setUp() {

        mentor = Mentor.builder()
                .mentorId("men_1")
                .username("mentor1")
                .rating(0)
                .build();

        student = Student.builder()
                .studentId("stud_1")
                .username("student1")
                .build();

        mentorRepo.save(mentor);
        studentRepo.save(student);

        recommendation =
                Recommendation.builder()
                        .recommendationId("rec_1")
                        .mentor(mentor)
                        .student(student)
                        .timestamp(new Timestamp(new Date().getTime()))
                        .build();

        repo.save(recommendation);
    }

    @AfterEach
    void tearDown() {
        repo.deleteById(recommendation.getRecommendationId());
        studentRepo.deleteById(student.getStudentId());
        mentorRepo.deleteById(mentor.getMentorId());
    }

    @Test
    void testFindByStudentAndMentor_Found() {

        Optional<Recommendation> result = repo.findByStudentAndMentor(student,mentor);
        AssertionsForClassTypes.assertThat(result.get()).isEqualTo(recommendation);

    }
    @Test
    void testFindByStudentAndMentor_NotFound() {

        Optional<Recommendation> result = repo.findByStudentAndMentor(Student.builder().studentId("5353").build(), mentor);
        AssertionsForClassTypes.assertThat(result.isEmpty()).isTrue();

    }

    @Test
    void test_GetRecommendation_Found() {

        Optional<RecommendationResponse> result = repo.getRecommendation(recommendation.getRecommendationId());
        AssertionsForClassTypes.assertThat(result.get().getId()).isEqualTo(recommendation.getRecommendationId());
    }

    @Test
    void testGetRecommendation_NotFound() {

        Optional<RecommendationResponse> result = repo.getRecommendation("somerandom");
        AssertionsForClassTypes.assertThat(result.isEmpty()).isTrue();

    }
}