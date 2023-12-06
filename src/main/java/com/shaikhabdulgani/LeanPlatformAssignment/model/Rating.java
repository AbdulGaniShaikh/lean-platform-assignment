package com.shaikhabdulgani.LeanPlatformAssignment.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "rating_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "rating"
)
@JsonIdentityReference(
        alwaysAsId = true
)
public class Rating {

    @Id
    String ratingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",updatable = false,nullable = false)
    User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mentor_id",updatable = false,nullable = false)
    Mentor mentor;

    @Column(name = "timestamp",nullable = false)
    Timestamp timestamp;

    int rating;

}
