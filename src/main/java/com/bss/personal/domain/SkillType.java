package com.bss.personal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Skill_Type\"", schema = "Public")
public class SkillType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_SkillType\"", allocationSize=1)
	@Column(name="\"SkillType_Id\"", unique=true, nullable=false)         
	private Long id;
	
	@Column(name="\"Skill_Type\"")
	private String SkillType;
}