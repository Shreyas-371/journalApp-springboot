package net.engineeringdigest.journalApp.Entity.Relational;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="journal_entries")
@Data
public class JournalEntryRelational {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String title;
	private String entry;
	private LocalDate date; 
	
	
	

}
