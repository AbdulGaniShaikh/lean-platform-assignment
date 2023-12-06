package com.shaikhabdulgani.LeanPlatformAssignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "review_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "reviewText"
)
public class Review {

    @Id
    private String reviewId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",updatable = false,nullable = false)
    private User user;


    @ManyToOne(optional = false)
    @JoinColumn(name = "mentor_id",updatable = false,nullable = false)
    private Mentor mentor;

    private String reviewText;

    @Column(name = "timestamp",nullable = false)
    private Timestamp timestamp;

}
