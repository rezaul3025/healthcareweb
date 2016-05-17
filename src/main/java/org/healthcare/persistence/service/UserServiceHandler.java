/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.persistence.service;

import org.healthcare.domain.User;
import org.healthcare.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author rkarim
 */
@Service
public class UserServiceHandler implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public User addUser(User user) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
       return userRepo.save(user);
    }

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User getUserByParent(Long id) {
		return userRepo.findByParentId(id);
	}

}
