package com.bss.personal.dto;

import lombok.Data;

@Data
public class ItemDTO {

	private Long   id;
	private String name;
	private Long   cost;
	private String desc;
	private Long   bagId;
}
