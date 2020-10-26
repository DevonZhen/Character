package com.bss.personal.dto;

import java.util.List;
import com.google.common.collect.Lists;
import lombok.Data;

@Data
public class CharDTO {
	//BagDTO Class?
	private String id;
	private List<BagDTO> bags = Lists.newArrayList();
}
