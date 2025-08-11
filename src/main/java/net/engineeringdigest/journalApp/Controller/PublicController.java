package net.engineeringdigest.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/health-check")
	public String healthCheck()
	{
		return "OK!!";
	}
	
	@PostMapping("/create-user")
	public ResponseEntity<?> creatUser(@RequestBody User user)
	{
		try {
			userService.saveNewEntry(user);
			
			return new ResponseEntity<>(user,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
