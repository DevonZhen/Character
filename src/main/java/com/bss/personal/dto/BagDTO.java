package com.bss.personal.dto;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class BagDTO {

	private Long   id; 
	private String bagName;
	private Long   charId;
	private List<ItemDTO> itemList = Lists.newArrayList();
}
