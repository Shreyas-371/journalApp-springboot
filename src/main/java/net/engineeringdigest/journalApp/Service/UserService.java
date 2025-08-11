package net.engineeringdigest.journalApp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.UserRepository;


@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void saveNewEntry(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		user.setRoles(Arrays.asList("USER"));
		userRepository.save(user);
	}
	
	public void saveEntry(User user)
	{
		userRepository.save(user);
	}
	
	public void deleteUserByUserName(String userName)
	{
		userRepository.deleteUserByUserName(userName);
	}
	
	public List<User> findEntry()
	{
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id)
	{
		return userRepository.findById(id);
	}
	
	public void deleteEntry(ObjectId id)
	{
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String userName)
	{
		return userRepository.findByUserName(userName);
	}
	
//	public void editEntry(ObjectId id,JournalEntry entry)
//	{
//		journalEntryRepository.
//	}

}
