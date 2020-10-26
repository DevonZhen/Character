package com.bss.personal.custom.dto;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class ItemsDTO {
	private Long   id; 
	private String bagName;
	private Long   charId;
	private List<ItemsDTO> itemList = Lists.newArrayList();
}
