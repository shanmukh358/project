package com.learning.repo;

import java.util.Optional;

import com.learning.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
