package com.learning.controller;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.EROLE;
import com.learning.entity.Food;
import com.learning.entity.Register;
import com.learning.entity.Role;
import com.learning.Exception.AlreadyExistsException;
import com.learning.Exception.IdNotFoundException;
import com.learning.Exception.InvalidPasswordException;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.LoginRepository;
import com.learning.repo.RoleRepository;
import com.learning.repo.UserRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.FoodService;
import com.learning.service.LoginService;
import com.learning.service.UserService;
import com.learning.service.impl.UserServiceimpl;


@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetailsImpl.getId(),
				userDetailsImpl.getEmail(),
				roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
		
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: username is already in use!"));
		}
		
		Register user = new Register(signupRequest.getUsername(),
				signupRequest.getEmail(),
				signupRequest.getAddress(),
				passwordEncoder.encode(signupRequest.getPassword())
				);
		
		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles==null) {
			Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(()-> new RuntimeException("Error: role not found"));
			roles.add(userRole);
		}
		else {
			strRoles.forEach(e->{
				switch (e) {
				case "admin":
					Role roleAdmin = roleRepository.findByRoleName(EROLE.ROLE_ADMIN)
							.orElseThrow(()-> new RuntimeException("Error: role not found"));
					roles.add(roleAdmin);
					break;
				
				default:
					Role roleUser = roleRepository.findByRoleName(EROLE.ROLE_USER)
							.orElseThrow(()-> new RuntimeException("Error: role not found"));
					roles.add(roleUser);
					break;
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity
				.status(201)
				.body(new MessageResponse("User created successfully"));
		
	}
	
	

		
		@Autowired
		FoodService foodService;
		
		
		
		@GetMapping("/all")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> getAllUser() throws InvalidPasswordException {
			Optional<List<Register>> optional = userService.getAllUserDetails();
			if (optional.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No record found"));
			}
			return ResponseEntity.ok(optional.get());
		}
		
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")	
	@GetMapping("/getby{id}")
		public ResponseEntity<?> getUserById(@PathVariable("id") int id) throws IdNotFoundException,InvalidPasswordException{
			Register result = userService.getUserById(id);
			return ResponseEntity.status(201).body(result);
		}
	//return null;
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(String email , String password){
		String result = userService.authenticate(email, password);
		return ResponseEntity.status(201).body(result);
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	  @DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteUserById(@PathVariable("id")int id) throws IdNotFoundException, InvalidPasswordException{
			String result=userService.deleteUserById(id);
			HashMap<String, String> map=new HashMap<>();
			map.put("messeage", "delete");
			return ResponseEntity.status(201).body(map);
		}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUserDetails() throws  InvalidPasswordException{
		Optional<List<Register>> optional = userService.getAllUserDetails();
		if(optional.isEmpty()) {
		Map<String, String> map = new HashMap<>();
//		map.put("message", "no record found");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
	return ResponseEntity.ok(optional.get());
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Register register) throws IdNotFoundException
	{
		Register result = userService.updateUser(id, register);
		Map<String, String> map = new HashMap<>();
		map.put("message", "success updated");
		return ResponseEntity.status(201).body(result);
	}
	

	
	//add record
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN')")
	@PostMapping("/addFood")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws AlreadyExistsException {
		
	
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
		
		}
	
	//retrieve single record
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/food{foodId}")
	public ResponseEntity<?> getFoodById(@PathVariable("foodId") int foodId) throws IdNotFoundException{
		Food result = foodService.getFoodById(foodId);
		return ResponseEntity.ok(result);	
		
	}
	
	//retrieve all records
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/allfoods")
	public ResponseEntity<?> getAllFoodDetails(){
		Optional<List<Food>> optional = foodService.getAllFoodDetails();
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());	
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/food{foodId}")
	public ResponseEntity<?> deleteFoodById(@PathVariable("foodId") int foodId) throws IdNotFoundException, SQLException
	{
		String result = foodService.deleteFoodById(foodId);
		Map<String, String> map = new HashMap<>();
		map.put("message", "success deleted");
		return ResponseEntity.status(201).body(result);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/food{foodId}")
	public ResponseEntity<?> updateFood(@PathVariable("foodId") int foodId, @RequestBody Food food) throws IdNotFoundException
	{
		Food result = foodService.updateFood(foodId, food);
		Map<String, String> map = new HashMap<>();
		map.put("message", "success updated");
		return ResponseEntity.status(201).body(result);
	}
	
	}
