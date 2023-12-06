package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.Student;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.StudentRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public Student save(@Valid SaveUserDAO req) {
        return repo.save(
                Student.builder()
                        .studentId(UniqueId.student())
                        .username(req.getUsername())
                        .build()
        );
    }

    public Student get(String studentId) throws NotFound {
        Optional<Student> student = repo.findById(studentId);
        return student.orElseThrow(()->new NotFound("student not present by id "+studentId));
    }

    public List<Student> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return repo.findAll(pageable).stream().toList();
    }
}
