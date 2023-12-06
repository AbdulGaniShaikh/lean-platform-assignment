package com.shaikhabdulgani.LeanPlatformAssignment.repo;


import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendationResponse;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Recommendation;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RecommendationRepo extends JpaRepository<Recommendation,String> {
    Optional<Recommendation> findByStudentAndMentor(Student student, Mentor mentor);

    @Query(
            nativeQuery = true,
            value = "SELECT r.recommendation_id as id" +
                    ",s.username as s_name," +
                    "s.student_id," +
                    "r.timestamp,m.mentor_id," +
                    "m.username as m_name " +
                    "FROM recommendation_tbl as r,student_tbl as s,mentor_tbl as m WHERE m.mentor_id=r.mentor_id AND r.student_id=s.student_id AND recommendation_id=:recommendationId"
    )
    Optional<RecommendationResponse> getRecommendation(@Param("recommendationId") String recommendationId);
}
