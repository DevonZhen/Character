package com.bss.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.personal.service.AccountService;
import com.bss.personal.resturl.RESTUrls;
import com.bss.personal.domain.Character;
import com.bss.personal.dto.AccountDTO;
import com.bss.personal.dto.CharacterDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class AccountController {
	
	@Autowired
	AccountService accountService;
		
	//Retrieves All the information of an Account
	@RequestMapping(value = RESTUrls.URL_AccountAll, method = RequestMethod.GET)
	public ResponseEntity<List<AccountDTO>> findAllAccount() 
	{
		try {
			System.out.println("SQL AccountAll Begins...");
			List<AccountDTO> accountDTOList = accountService.findAll();
			if(accountDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<AccountDTO>>(accountDTOList, HttpStatus.OK);
		}
		catch(Exception e){
			log.error("Error calling AllAccount()",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Retrieve Username/Password for Login
	@RequestMapping(value = RESTUrls.URL_GetUsernamePassword, method = RequestMethod.GET)
	public ResponseEntity<AccountDTO> loginCredentials(@PathVariable String username,@PathVariable String password) 
	{
		try {
			System.out.println("Sql GetUsernamePassword Begins...");
			AccountDTO accountDTO = accountService.loginCredentials(username, password);
			
			if(accountDTO==null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("Error calling getUsernamePassword()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//Retrieve Character List
	@RequestMapping(value = RESTUrls.URL_CharacterListbyAccount, method = RequestMethod.GET)
	public ResponseEntity<AccountDTO>  getAccbyName(@PathVariable String username) 
	{
		try {
			System.out.println("SQL CharacterListbyAccount Begins...");
			AccountDTO accountDTO = accountService.getAccbyName(username);

			if(accountDTO==null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("Error calling CharacterListbyAccount()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Retrieve Character Skills
	@RequestMapping(value = RESTUrls.URL_getCharacterSkills, method = RequestMethod.GET)
	public ResponseEntity<AccountDTO>  getSkillsbyCharacterName(@PathVariable String name) 
	{
		try {
			System.out.println("SQL GetCharacterSkills Begins...");
			AccountDTO accountDTO = accountService.getSkillsbyCharacterName(name);
			
			if(accountDTO==null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("Error calling GetCharacterSkills()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//Retrieve Character Skills (Hibernate)
	@RequestMapping(value = RESTUrls.URL_getCharacterInfo, method = RequestMethod.GET)
	public ResponseEntity<AccountDTO>  getCharacterInfo(@PathVariable String name) 
	{
		try {
			System.out.println("SQL GetCharacterInfo Begins...");
			AccountDTO accountDTO = accountService.getCharacterInfo(name);
			
			if(accountDTO==null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("Error calling getCharacterInfo()", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//Creating New Account (Hibernate)
	@PostMapping(value = RESTUrls.URL_PostAccount, produces = "application/json")
	public AccountDTO newAccount(@RequestBody AccountDTO accountDTO) 
	{
		System.out.println("AccountDTO: "+accountDTO);
		AccountDTO resultDTO = null;
		try {
			System.out.println("SQL newAccount Begins...");
			if(accountDTO.getId()==null)
				resultDTO = accountService.newAccount(accountDTO);
			else
				resultDTO = accountService.updateAccount(accountDTO);
		}catch (Exception e) {
			log.error("Error calling newAcccount()", e);
		}
		
		return resultDTO;
		
	}	
	
	//Update Character
	@PostMapping(value = RESTUrls.URL_UpdateAccount,  produces = "application/json")
	public CharacterDTO updateCharacter()
	{
		try {
			System.out.println("SQL updateCharacter Begins...");
		}catch (Exception e) {
			log.error("Error calling getCharacterInfo()", e);
		}
		return null;
	}
	
	//Delete Account
	@DeleteMapping(value = RESTUrls.URL_DeleteAccount, produces = "application/json")
    public void deleteAccount(@PathVariable String id) {
		try {
			 Long Id = Long.parseLong(id);
		     accountService.deleteAccount(Id);
		} catch (Exception e) {
		     log.error("Error Ocurred: deleteAccount()", e);
		}
	
    }
	
	
}
