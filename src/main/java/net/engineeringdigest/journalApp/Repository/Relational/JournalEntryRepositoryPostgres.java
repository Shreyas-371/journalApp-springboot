package net.engineeringdigest.journalApp.Repository.Relational;

import org.springframework.data.jpa.repository.JpaRepository;

import net.engineeringdigest.journalApp.Entity.Relational.JournalEntryRelational;

public interface JournalEntryRepositoryPostgres extends JpaRepository<JournalEntryRelational, Long> {

}
