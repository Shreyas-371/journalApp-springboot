package net.engineeringdigest.journalApp.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.UserRepository;

@Component
public class AdminService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public void addNewAdmin(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userRepository.save(user);
	}
	
}
