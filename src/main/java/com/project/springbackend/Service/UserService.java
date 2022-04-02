package com.project.springbackend.Service;

import com.project.springbackend.Domain.User;
import com.project.springbackend.Repository.UserRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) { userRepository.saveAndFlush(user); }

}
