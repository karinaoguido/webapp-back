package com.webapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.model.User;
import com.webapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	public void insertUser(User user) {
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		
		if (userOptional.isPresent()) {
			throw new IllegalStateException("email already used");
		}
		
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		boolean userExists = userRepository.existsById(id);
		
		if (!userExists) {
			throw new IllegalStateException("user with id " + id + " does not exists");
		}
		
		userRepository.deleteById(id);
	}

	@Transactional
	public void updateUser(Long id, String firstName, String lastName, String email) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if (userOptional.isPresent()) {
			throw new IllegalStateException("user with id " + id + " does not exists");
		}
		
		User user = userOptional.get();
		
		if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
			user.setFirstName(firstName);
		}

		if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
			user.setLastName(lastName);
		}

		if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
			Optional<User> emailOptional = userRepository.findUserByEmail(email);
			
			if (emailOptional.isPresent()) {
				throw new IllegalStateException("email already used");
			}
			
			user.setEmail(email);
		}
	}

}
