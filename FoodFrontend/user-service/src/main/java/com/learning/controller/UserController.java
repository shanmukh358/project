package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learning.entity.LoginInfo;
import com.learning.entity.User;
import com.learning.service.UserService;
import com.learning.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin // handled at gateway (application.yml)
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil JwtUtil;

    @GetMapping("/users")
    public List<User> getAllUsers(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return service.getAllUsers(page - 1, size).toList();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        User user1 = service.createUser(user);
        String token;
		try {
			token = JwtUtil.createToken(user1.getId(), user1.getName());
			Map<String, Object> map = new HashMap<>();
			map.put("token", token);
			// map.put("name", user.getName());
			user.setPassword(null);
			map.put("user", user);
			return ResponseEntity.status(HttpStatus.CREATED).body(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("msg", e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
		}
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginInfo loginInfo) {
    	System.out.println();
        try {
            User user = null;

            if (loginInfo.getEmail().equals("admin@admin.com") &&
                    loginInfo.getPassword().equals("secret@123")) {
                user = new User();
                user.setEmail(loginInfo.getEmail());
                user.setId(0);
                user.setName("Administrator");
            } else {
                user = service.getUser(loginInfo.getEmail(), loginInfo.getPassword());
            }

            String token = JwtUtil.createToken(user.getId(), user.getName());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            // map.put("name", user.getName());
            user.setPassword(null);
            map.put("user", user);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {

        User user = service.getUserById(id);
        if (user == null) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Sorry user With " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            user.setId(id);
            User user1 = service.update(user);
            return ResponseEntity.ok(user1);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Map<String, String> map = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            service.delete(id);
            map.put("message", "User Deleted Successfully");
        } catch (Exception e) {
            map.put("message", e.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(map);
    }
}
