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

    @PostConstruct
    private void populate() throws UnauthorizedAccess, NotFound, InvalidParams {
        populateUsers();
        populateMentors();
        populateStudents();

        System.out.println("here");
        recommendStudents();
        reviewMentors();
//        rateMentors();
    }


    private void populateUsers(){
        User user1 = User.builder()
                .userId("1")
                .username("user1")
                .build();
        User user2 = User.builder()
                .userId("2")
                .username("user2")
                .build();

        User user3 = User.builder()
                .userId("3")
                .username("user3")
                .build();

        userRepo.saveAll(Arrays.asList(user1,user2,user3));
    }


    private void populateMentors(){
        Mentor mentor1 = Mentor.builder()
                .mentorId("1")
                .username("mentor1")
                .rating(0)
                .build();
        Mentor mentor2 = Mentor.builder()
                .mentorId("2")
                .username("mentor2")
                .rating(0)
                .build();
        Mentor mentor3 = Mentor.builder()
                .mentorId("3")
                .username("mentor3")
                .rating(0)
                .build();

        mentorRepo.saveAll(Arrays.asList(mentor1,mentor2,mentor3));
    }

    private void populateStudents(){
        Student student1 = Student.builder()
                .studentId("1")
                .username("student1")
                .build();
        Student student2 = Student.builder()
                .studentId("2")
                .username("student2")
                .build();
        Student student3 = Student.builder()
                .studentId("3")
                .username("student3")
                .build();

        studentRepo.saveAll(Arrays.asList(student1,student2,student3));
    }

    private void rateMentors() throws UnauthorizedAccess, NotFound {

        //rate mentor with id 1
        rateMentor("1","1",4);
        rateMentor("1","2",3);
        rateMentor("1","3",3);

        //rate mentor with id 2
        rateMentor("2","1",1);
        rateMentor("2","2",1);
        rateMentor("2","3",2);

        //rate mentor with id 3
        rateMentor("3","1",5);
        rateMentor("3","2",5);
        rateMentor("3","3",5);
    }

    private void reviewMentors() throws NotFound, InvalidParams {

        reviewMentor("1","1","demo review1");
        reviewMentor("1","2","demo review2");
        reviewMentor("1","3","demo review3");

        reviewMentor("2","1","demo review1");
        reviewMentor("2","2","demo review2");
        reviewMentor("2","3","demo review3");

        reviewMentor("3","1","demo review1");
        reviewMentor("3","2","demo review2");
        reviewMentor("3","3","demo review3");

    }

    private void recommendStudents() throws UnauthorizedAccess, NotFound {
        recommendStudent("1","2");
        recommendStudent("1","3");

        recommendStudent("2","1");
        recommendStudent("2","3");

        recommendStudent("3","1");
        recommendStudent("3","2");
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
