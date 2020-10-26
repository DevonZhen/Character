package com.bss.personal.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bss.personal.custom.dto.CharactersDTO;
import com.bss.personal.dto.CharacterDTO;
import com.bss.personal.resturl.RESTUrls;
import com.bss.personal.service.BagService;
import com.bss.personal.service.CharacterService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class CharacterController {
	@Autowired
	CharacterService characterService;
	@Autowired
	BagService bagService;
	
	//Creating New Character
	@PostMapping(value = RESTUrls.URL_PostCharacter,  produces = "application/json")
	public CharacterDTO newCharacter(@RequestBody CharactersDTO charactersDTO) 
	{
		System.out.println(charactersDTO);
		Long accId = charactersDTO.getId();
		boolean flag = false;
		try {
			System.out.println("SQL postCharacter Begins...");
			for(CharacterDTO characterDTO: charactersDTO.getCharacterList()) {
				System.out.println("Character: "+characterDTO.getName()+" "+characterDTO.getGenderId()+" "+characterDTO.getRaceId()
											+" "+characterDTO.getClassId()+" "+characterDTO.getOrigin()+" "+characterDTO.getAccId());
				if(characterDTO.getAccId()==null) {
					System.out.println("New Character...");
					flag = characterService.newCharacter(characterDTO.getName(), characterDTO.getGenderId(), characterDTO.getRaceId(), characterDTO.getClassId(), characterDTO.getOrigin(), accId);
				}
				else
					flag = characterService.updateCharacter(characterDTO.getName(), characterDTO.getGenderId(), characterDTO.getRaceId(), characterDTO.getClassId(), characterDTO.getOrigin(), characterDTO.getAccId());
			}
		}catch (Exception e) {
			log.error("Error calling postCharacter()", e);
		}
		return null;
	}	
	
	@DeleteMapping(value = RESTUrls.URL_DeleteCharacter, produces = "application/json")
	public void deletePerson(@PathVariable Long id) {
		try {
			System.out.println("Deleting...");
			characterService.deleteCharacter(id);
		}catch (Exception e) {
		     log.error("Error calling deletePerson()", e);
		}
	}
}
