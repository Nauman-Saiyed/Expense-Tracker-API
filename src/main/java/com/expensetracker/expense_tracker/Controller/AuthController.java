package com.expensetracker.expense_tracker.Controller;

import com.expensetracker.expense_tracker.DTO.LoginRequest;
import com.expensetracker.expense_tracker.DTO.SignUpRequest;
import com.expensetracker.expense_tracker.repository.UserRepository;
import com.expensetracker.expense_tracker.security.JwtUtil;
import com.expensetracker.expense_tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists");
        } else {
            User user = new User();
            user.setEmail(request.getEmail());

            String encodedPassword = passwordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not Found");
        }
        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password!");
        }

//        else if (userRepository.findByEmail(request.getEmail()).isPresent()
//                && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Successfull");
//        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("Token : ", token));
    }


}
