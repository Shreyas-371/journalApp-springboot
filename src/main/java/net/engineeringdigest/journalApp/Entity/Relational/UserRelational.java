package net.engineeringdigest.journalApp.Entity.Relational;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_table")
@Data
public class UserRelational {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
		
	@Column(unique = true,nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@OneToMany
	@JoinColumn(name ="journalEntry_id")
	private List<JournalEntryRelational> journalEntries = new ArrayList<>();
	@ElementCollection
	@CollectionTable(
			name ="user_roles",
			joinColumns=@JoinColumn(name="user_id")
			)
	@Column(name = "role")
	private List<String> roles = new ArrayList<>();
	 
	
	
	

}
