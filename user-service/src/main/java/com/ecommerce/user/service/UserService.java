package com.ecommerce.user.service;

import com.ecommerce.user.config.JwtUtil;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(user.getEmail());  // Generate JWT Token
            }
        }
        throw new RuntimeException("Invalid Credentials");
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
