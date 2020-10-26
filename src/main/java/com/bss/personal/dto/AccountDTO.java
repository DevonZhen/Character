package com.bss.personal.dto;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class AccountDTO {

	private Long   id;
	private String username;
	private String password;
	private String email;
	private Date   birthday;
	private String firstName;
	private String lastName;
	private String country;
	private Long   statusId;
	private List<CharacterDTO> characterList = Lists.newArrayList();
}
