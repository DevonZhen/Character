package com.bss.personal.dto;

import java.util.List;
import com.google.common.collect.Lists;
import lombok.Data;

@Data
public class CharacterDTO {
	
	private Long   id;  
	private String name;
	private Long   genderId;
	private Long   raceId;
	private Long   classId;
	private String origin;
	private Long   accId;
	private List<BagDTO> bags = Lists.newArrayList();
	private List<SkillsDTO> skillsList = Lists.newArrayList();
}
