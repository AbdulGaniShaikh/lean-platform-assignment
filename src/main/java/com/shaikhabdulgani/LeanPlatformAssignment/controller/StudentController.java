package com.shaikhabdulgani.LeanPlatformAssignment.controller;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.RecommendationResponse;
import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Recommendation;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Student;
import com.shaikhabdulgani.LeanPlatformAssignment.service.RecommendationService;
import com.shaikhabdulgani.LeanPlatformAssignment.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("students/{studentId}")
    public ResponseEntity<Student> get(@PathVariable String studentId) throws NotFound {
        return ResponseEntity.ok(studentService.get(studentId));
    }

    @PostMapping("student")
    public ResponseEntity<Student> save(@RequestBody @Valid SaveUserDAO req){
        return new ResponseEntity<>(studentService.save(req), HttpStatus.CREATED);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        return ResponseEntity.ok(studentService.getAll(pageNumber, pageSize));
    }

    @PostMapping("students/{studentId}/recommend")
    public ResponseEntity<RecommendationResponse> recommendStudent(
            @RequestBody @Valid RecommendDAO req,
            @PathVariable String studentId
    ) throws UnauthorizedAccess, NotFound {
        return ResponseEntity.ok(recommendationService.recommend(studentId,req));
    }
}
