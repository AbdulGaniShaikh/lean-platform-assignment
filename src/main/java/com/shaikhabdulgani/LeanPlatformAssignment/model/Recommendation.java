package com.shaikhabdulgani.LeanPlatformAssignment.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "recommendation_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "recommendationId"
)
@JsonIdentityReference(
        alwaysAsId = true
)
public class Recommendation {

    @Id
    private String recommendationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id",updatable = false,nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mentor_id",updatable = false,nullable = false)
    private Mentor mentor;

    @Column(name = "timestamp",nullable = false)
    Timestamp timestamp;

}
