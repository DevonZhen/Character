package com.bss.personal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"Skills\"", schema = "Public")
public class Skills implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Skills\"", allocationSize=1)
	@Column(name="\"Skill_Id\"", unique=true, nullable=false)         
	private Long id;
	
	@Column(name="\"Skill_Name\"")
	private String skillName;
	
	@Column(name="\"Skill_Damage\"")
	private Long skillDamage;
	
	@Column(name="\"Skill_Cost\"")
	private Long skillCost;
	
//	@Column(name="\"Skill_Type\"")
//	private String skillType;
	
	@Column(name="\"SkillType_Id\"")
	private Long skillType;
	
	@Column(name="\"Char_Id\"")
	private Long charId;
	
	//∞ - 1 Skills to Character
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"Char_Id\"", nullable = false,insertable=false, updatable=false)
    private Character character;	
	
}
