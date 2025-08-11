package net.engineeringdigest.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userDb = userService.findByUserName(userName);
		userDb.setUserName(user.getUserName());
		userDb.setPassword(user.getPassword());
		userService.saveNewEntry(userDb);
		return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteByUserName()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());
		userService.deleteUserByUserName(user.getUserName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
