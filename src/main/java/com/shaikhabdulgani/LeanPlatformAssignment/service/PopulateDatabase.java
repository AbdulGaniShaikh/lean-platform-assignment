package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.RateDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.ReviewDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.InvalidParams;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Student;
import com.shaikhabdulgani.LeanPlatformAssignment.model.User;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.*;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Component
public class PopulateDatabase {

    @Autowired
    MentorRepo mentorRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    RatingService ratingService;
    @Autowired
    RecommendationService recommendationService;
    @Autowired
    ReviewService reviewService;

    User user1;
    User user2;
    User user3;

    Mentor mentor1;
    Mentor mentor2;
    Mentor mentor3;

    Student student1;
    Student student2;
    Student student3;

    @PostConstruct
    private void populate() throws UnauthorizedAccess, NotFound, InvalidParams {
        populateUsers();
        populateMentors();
        populateStudents();

        recommendStudents();
        reviewMentors();
//        rateMentors();
    }


    private void populateUsers(){
        user1 = User.builder()
                .userId(UniqueId.user())
                .username("user1")
                .build();
        user2 = User.builder()
                .userId(UniqueId.user())
                .username("user2")
                .build();

        user3 = User.builder()
                .userId("3")
                .username("user3")
                .build();

        userRepo.saveAll(Arrays.asList(user1,user2,user3));
    }


    private void populateMentors(){
        mentor1 = Mentor.builder()
            .mentorId(UniqueId.mentor())
            .username("mentor1")
            .rating(0)
            .build();
        mentor2 = Mentor.builder()
            .mentorId(UniqueId.mentor())
            .username("mentor2")
            .rating(0)
            .build();
        mentor3 = Mentor.builder()
            .mentorId(UniqueId.mentor())
            .username("mentor3")
            .rating(0)
            .build();

        mentorRepo.saveAll(Arrays.asList(mentor1,mentor2,mentor3));
    }

    private void populateStudents(){
        student1 = Student.builder()
                .studentId(UniqueId.student())
                .username("student1")
                .build();
        student2 = Student.builder()
                .studentId(UniqueId.student())
                .username("student2")
                .build();
        student3 = Student.builder()
                .studentId(UniqueId.student())
                .username("student3")
                .build();

        studentRepo.saveAll(Arrays.asList(student1,student2,student3));
    }

    private void rateMentors() throws UnauthorizedAccess, NotFound {

        //rate mentor with id 1
        rateMentor(mentor1.getMentorId(),user1.getUserId(),4);
        rateMentor(mentor1.getMentorId(),user2.getUserId(),3);
        rateMentor(mentor1.getMentorId(),user3.getUserId(),3);

        //rate mentor with id 2
        rateMentor(mentor2.getMentorId(),user1.getUserId(),1);
        rateMentor(mentor2.getMentorId(),user2.getUserId(),1);
        rateMentor(mentor2.getMentorId(),user3.getUserId(),2);

        //rate mentor with id 3
        rateMentor(mentor3.getMentorId(),user1.getUserId(),5);
        rateMentor(mentor3.getMentorId(),user2.getUserId(),5);
        rateMentor(mentor3.getMentorId(),user3.getUserId(),5);
    }

    private void reviewMentors() throws NotFound, InvalidParams {

        reviewMentor(mentor1.getMentorId(),user1.getUserId(),"demo review1");
        reviewMentor(mentor1.getMentorId(),user2.getUserId(),"demo review2");
        reviewMentor(mentor1.getMentorId(),user3.getUserId(),"demo review3");

        reviewMentor(mentor2.getMentorId(),user1.getUserId(),"demo review1");
        reviewMentor(mentor2.getMentorId(),user2.getUserId(),"demo review2");
        reviewMentor(mentor2.getMentorId(),user3.getUserId(),"demo review3");

        reviewMentor(mentor3.getMentorId(),user1.getUserId(),"demo review1");
        reviewMentor(mentor3.getMentorId(),user2.getUserId(),"demo review2");
        reviewMentor(mentor3.getMentorId(),user3.getUserId(),"demo review3");

    }

    private void recommendStudents() throws UnauthorizedAccess, NotFound {
        recommendStudent(student1.getStudentId(),mentor2.getMentorId());
        recommendStudent(student1.getStudentId(),mentor3.getMentorId());

        recommendStudent(student2.getStudentId(),mentor1.getMentorId());
        recommendStudent(student2.getStudentId(),mentor3.getMentorId());

        recommendStudent(student3.getStudentId(),mentor1.getMentorId());
        recommendStudent(student3.getStudentId(),mentor2.getMentorId());
    }


    private void recommendStudent(String studentId,String mentorId) throws UnauthorizedAccess, NotFound {

        RecommendDAO recommendDAO = new RecommendDAO();
        recommendDAO.setMentorId(mentorId);
        recommendationService.recommend(studentId,recommendDAO);

    }

    private void reviewMentor(String mentorId,String userId,String review) throws InvalidParams, NotFound {

        ReviewDAO reviewDAO = new ReviewDAO();
        reviewDAO.setReviewText(review);
        reviewDAO.setUserId(userId);

        reviewService.addReview(mentorId,reviewDAO);

    }

    private void rateMentor(String mentorId,String userId, int rating) throws UnauthorizedAccess, NotFound {

        RateDAO rateDAO = new RateDAO();
        rateDAO.setRating(rating);
        rateDAO.setUserId(userId);
        ratingService.rate(mentorId,rateDAO);
    }

}
