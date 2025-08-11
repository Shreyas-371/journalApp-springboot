package net.engineeringdigest.journalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> all = adminService.getAllUsers();
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewAdmin(@RequestBody User user)
	{
		adminService.addNewAdmin(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	
}
