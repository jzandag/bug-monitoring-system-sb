package org.jzandag.dao;

import java.util.List;

import org.jzandag.model.Bug;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BugRepository extends CrudRepository<Bug, Long>{

	@Transactional(readOnly = true)   
    List<Bug> findAll() throws DataAccessException;
	
	Bug findOne(@Param("id") Long id) throws DataAccessException;
}
