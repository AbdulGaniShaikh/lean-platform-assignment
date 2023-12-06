package com.shaikhabdulgani.LeanPlatformAssignment.dto;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

public interface RecommendationResponse {


    @Value("#{target.student_id}")
    String getStudentId();
    @Value("#{target.mentor_id}")
    String getMentorId();
    @Value("#{target.m_name}")
    String getMentorName();
    @Value("#{target.s_name}")
    String getStudentName();
    Timestamp getTimestamp();
    String getId();

}
