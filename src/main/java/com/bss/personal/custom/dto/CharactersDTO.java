package com.bss.personal.custom.dto;

import java.util.List;

import com.bss.personal.dto.CharacterDTO;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class CharactersDTO {
	//technically Account Id
	private Long   id;  
	private List<CharacterDTO> characterList = Lists.newArrayList();
}
