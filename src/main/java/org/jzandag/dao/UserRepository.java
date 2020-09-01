package org.jzandag.dao;

import org.jzandag.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Users, Long> {
	
	@Query("SELECT u FROM Users u WHERE u.username = :username")
    public Users getUserByUsername(@Param("username") String username);
	
	@Query("SELECT u.bugs FROM Users u inner join u.bugs WHERE u.username = :username ")
	public Users getBugsByUsername(@Param("username") String username);
}
