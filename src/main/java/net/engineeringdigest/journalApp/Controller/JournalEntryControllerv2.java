package net.engineeringdigest.journalApp.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
	
	
	@Autowired
	private JournalEntryService journalEntryService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping()
	public ResponseEntity<?> findAllJournalEntriesByUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());
		
		List<JournalEntry> all = user.getJournalEntries();
		if(all != null && !all.isEmpty())
		{
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<?> getEntryFromId(@PathVariable ObjectId myId)
	{
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String UserName = auth.getName();
		 User user = userService.findByUserName(UserName);
		 List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
		 if(!collect.isEmpty())
		 	{
				 Optional<JournalEntry> entry = journalEntryService.findById(myId);
				 if(entry.isPresent())
					 return new ResponseEntity<>(entry,HttpStatus.OK);
		 	}
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> creatNewEntry(@RequestBody JournalEntry myEntry)
	{ try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			journalEntryService.saveEntry(myEntry,userName);
			return new ResponseEntity<>(myEntry,HttpStatus.OK);
	} catch(Exception e)
	{
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	}
	
	
	@PutMapping("id/{id}")
	public ResponseEntity<?> editEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String userName = auth.getName();
		 User user = userService.findByUserName(userName);
		 List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		 if(!collect.isEmpty())
		 {
			 JournalEntry old = journalEntryService.findById(id).orElse(null);
			 journalEntryService.deleteEntry(id);
			 if(newEntry.getTitle() != null && newEntry.getTitle() != "")
				{
					old.setTitle(newEntry.getTitle());
				}
				if(newEntry.getEntry() != null && newEntry.getEntry() != "")
				{
					old.setEntry(newEntry.getEntry());
				}
				journalEntryService.saveEntry(old);
				return new ResponseEntity<>(old,HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("id/{id}")
	public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id)
	{ try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		journalEntryService.deleteEntry(id,auth.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	} catch(Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}

}
