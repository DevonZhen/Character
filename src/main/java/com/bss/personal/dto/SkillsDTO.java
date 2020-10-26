package com.bss.personal.dto;

import lombok.Data;

@Data
public class SkillsDTO {

	private Long   id;
	private String skillName;
	private Long   skillDamage;
	private Long   skillCost;
	private Long   skillType;
}
