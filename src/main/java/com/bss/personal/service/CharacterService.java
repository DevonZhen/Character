package com.bss.personal.service;

import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bss.personal.dto.CharacterDTO;
import com.bss.personal.repository.CharacterRepository;
import com.bss.personal.repository.SQLTemplateRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class CharacterService {

	@Autowired
	SQLTemplateRepository SQLRepo;
	
	
	//New Character
	public Boolean newCharacter(String name, Long genderId, Long raceId, Long classId, String origin, Long accId) {
		return SQLRepo.newCharacter(name, genderId, raceId, classId, origin, accId);
	}
	
	public Boolean updateCharacter(String name, Long genderId, Long raceId, Long classId, String origin,Long charId) {
		return SQLRepo.updateCharacter(name, genderId, raceId, classId, origin, charId);
	}
	
	public Boolean deleteCharacter(Long charId) {
		return SQLRepo.deleteCharacter(charId);
	}

	
}

