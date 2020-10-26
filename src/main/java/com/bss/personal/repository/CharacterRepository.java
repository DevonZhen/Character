package com.bss.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bss.personal.domain.Account;
import com.bss.personal.domain.Character;


@Repository
public interface CharacterRepository extends BaseRepository<Character, Long>{
	
//	@Query("SELECT c.name FROM Character c JOIN c.Account a ON c.accId = a.accId WHERE a.username = ?1")
//	@Query("SELECT a FROM Account a WHERE a.username = ?1")
//	public Character getCharacterList(String name);

	//Retrieve Specific Character Info
	@Query("SELECT c FROM Character c JOIN FETCH c.bags b WHERE c.id = ?1")
	public Account getCharacterInfobyId(Long id);		
		
	
	
	
}


/*
	//Get All Characters for Specific Account [Home Page]
	SELECT "Character"."Name" FROM "Character" 
	JOIN "Account" ON "Character"."Acc_Id" = "Account"."Acc_Id"
	WHERE "Account"."Username" = 'Devonzhen';

	//Load Bags/Items/Info of a specific Character [Character Page]
	SELECT * FROM "Item" 
	JOIN "Bag" ON "Bag"."Bag_Id" = "Item"."Bag_Id" 
	JOIN "Character" ON "Character"."Char_Id" = "Bag"."Char_Id" 
	WHERE "Character"."Name" = 'Thor'
	
*/
 