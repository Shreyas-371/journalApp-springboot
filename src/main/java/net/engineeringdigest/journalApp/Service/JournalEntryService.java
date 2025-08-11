package net.engineeringdigest.journalApp.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;


@Component
public class JournalEntryService {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;
	
	public void saveEntry(JournalEntry journalEntry, String userName)
	{
		User user = userService.findByUserName(userName);
		journalEntry.setDate(LocalDate.now());
		JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
		user.getJournalEntries().add(savedEntry);
		userService.saveEntry(user);
	}
	public void saveEntry(JournalEntry journalEntry)
	{
		journalEntry.setDate(LocalDate.now());
		journalEntryRepository.save(journalEntry);
	}
	
//	public void save
	
	
	public List<JournalEntry> findEntry()
	{
		
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id)
	{
		return journalEntryRepository.findById(id);
	}
	
	public void deleteEntry(ObjectId id,String userName)
	{
		User user = userService.findByUserName(userName);
		boolean deleteEntry = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		if(deleteEntry)
		{
			userService.saveEntry(user);
			journalEntryRepository.deleteById(id);
		}
	}
	
	public void deleteEntry(ObjectId id)
	{
		journalEntryRepository.deleteById(id);
		
	}
		
	
	
//	public void editEntry(ObjectId id,JournalEntry entry)
//	{
//		journalEntryRepository.
//	}

}
