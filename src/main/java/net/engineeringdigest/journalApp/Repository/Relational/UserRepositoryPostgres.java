package net.engineeringdigest.journalApp.Repository.Relational;

import org.springframework.data.jpa.repository.JpaRepository;

import net.engineeringdigest.journalApp.Entity.Relational.UserRelational;

public interface UserRepositoryPostgres extends JpaRepository<UserRelational, Long> {
	public UserRelational findByUserName(String userName);
	public void deleteUserByUserName(String userName);

}
