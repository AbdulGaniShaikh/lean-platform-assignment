package com.shaikhabdulgani.LeanPlatformAssignment.service;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.SaveUserDAO;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.model.User;
import com.shaikhabdulgani.LeanPlatformAssignment.repo.UserRepo;
import com.shaikhabdulgani.LeanPlatformAssignment.utility.UniqueId;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;


    public User save(SaveUserDAO req) {
        return repo.save(
                User.builder()
                        .userId(UniqueId.user())
                        .username(req.getUsername())
                        .build()
        );
    }

    public User get(String userId) throws NotFound {
        Optional<User> user = repo.findById(userId);
        return user.orElseThrow(()->new NotFound("user not present by id "+userId));
    }

    public List<User> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return repo.findAll(pageable).stream().toList();
    }
}
