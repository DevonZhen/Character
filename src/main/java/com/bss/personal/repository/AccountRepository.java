package com.bss.personal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bss.personal.domain.Account;
import com.bss.personal.domain.Character;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>{
	
	public List<Account> findAll();
	
	Account findByUsernameAndPassword(String username, String password);
	
	
	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	public Account getAccbyName(String name);
	
	@Query("SELECT a FROM Account a WHERE a.id = ?1")
	public Account getAccbyId(Long id);
	
//	@Query("SELECT a FROM Account a, Character c, Skills s WHERE a.id=c.accId AND c.id=s.charId AND a.getCharacterList().name = ?1")
//	@Query("SELECT a FROM Account a  JOIN WHERE a.getCharacterList() = ?1")
//	public Account getCharacterSkills(String name);

    //When the relationship between the entities is already defined, you can use the [join fetch] syntax:
	//Retrieve Specific Character and its Skills
	@Query("SELECT a FROM Account a JOIN FETCH a.characterList c WHERE c.name = ?1")
	public Account getCharacterSkills(String name);
	
	//Retrieve Specific Character Info
	@Query("SELECT a FROM Account a JOIN FETCH a.characterList c WHERE c.name = ?1")
	public Account getCharacterInfo(String name);
	
	//Retrieve Specific Character Info
	@Query("SELECT a FROM Account a JOIN FETCH a.characterList c WHERE c.id = ?1")
	public Account getCharacterInfobyId(Long id);	
	
	
	
}

