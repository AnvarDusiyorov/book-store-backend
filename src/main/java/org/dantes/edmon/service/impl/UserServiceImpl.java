package org.dantes.edmon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dantes.edmon.model.User;
import org.dantes.edmon.repository.UserRepository;
import org.dantes.edmon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(user);

        return savedUser;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try {
            result = userRepo.findByEmail(email);
            log.info("IN findByUsername - user: {} found by username: {}", result, email);
        }catch (Exception e){
            log.info("IN findByUsername - user with username: {} not found!", email);
        }

        return result;
    }
}
