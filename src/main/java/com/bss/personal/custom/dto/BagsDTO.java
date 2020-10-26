package com.bss.personal.custom.dto;

import java.util.List;

import com.bss.personal.dto.BagDTO;
import com.google.common.collect.Lists;
import lombok.Data;

@Data
public class BagsDTO {
	//id technically belongs to Character Id
	private Long id;
	private List<BagDTO> bags = Lists.newArrayList();
	
}
