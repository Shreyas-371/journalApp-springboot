package net.engineeringdigest.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journalApp.Entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	public User findByUserName(String user);
	public void deleteUserByUserName(String userName);

}
