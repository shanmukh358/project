package com.learning.service;

import java.util.Optional;

import com.learning.entity.User;
import com.learning.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Page<User> getAllUsers(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    public User createUser(User user) {
        user.setId(null);
        return repo.save(user);
    }

    public User getUserById(Integer id) {
        Optional<User> op = repo.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }

    public User update(User user) {
        if (repo.existsById(user.getId())) {
            return repo.save(user);
        }
        throw new RuntimeException("Sorry user With " + user.getId() + " not found");
    }

    public void delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Sorry user With " + id + " not found");
        }
    }

    public User getUser(String email, String password) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent()) {
            User u = user.get();
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        throw new RuntimeException("Invalid email/password combination");
    }
}
