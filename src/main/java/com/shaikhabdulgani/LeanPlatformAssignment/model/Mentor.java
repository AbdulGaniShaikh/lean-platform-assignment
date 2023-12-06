package com.shaikhabdulgani.LeanPlatformAssignment.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "mentor_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "mentorId"
)
public class Mentor {

    @Id
    String mentorId;

    @Column(unique = true)
    String username;

    @OneToMany(mappedBy = "mentor")
    @JsonBackReference
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "mentor")
    private List<Review> reviews;

    @OneToMany(mappedBy = "mentor")
    private List<Rating> ratings;

    private float rating;

}
