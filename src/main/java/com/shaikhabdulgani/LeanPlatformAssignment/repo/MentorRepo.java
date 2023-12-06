package com.shaikhabdulgani.LeanPlatformAssignment.repo;


import com.shaikhabdulgani.LeanPlatformAssignment.model.Mentor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MentorRepo extends JpaRepository<Mentor,String > {

    @Query(value = "SELECT * FROM mentor_tbl WHERE CEILING(rating)=:rating",nativeQuery = true)
    List<Mentor> getByRating(@Param(value = "rating") float rating, Pageable pageable);
}
