package com.bss.personal.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Character\"", schema = "Public")
public class Character  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Character\"", allocationSize=1)
	@Column(name="\"Char_Id\"", unique=true, nullable=false)         
	private Long id;   
	
	@Column(name="\"Name\"")
	private String name;
	
	@Column(name="\"Gender_Id\"")
	private Long genderId;
	
	@Column(name="\"Race_Id\"")
	private Long raceId;
	
	@Column(name="\"Class_Id\"")
	private Long classId;
	
	@Column(name="\"Origin\"")
	private String origin;
	
	@Column(name="\"Acc_Id\"")
	private Long accId;
	
	
	//∞ - 1 Character to Account
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="\"Acc_Id\"", nullable = false,insertable=false, updatable=false)
	private Account account;
	
	//1 - ∞ Character to Bags
	@OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Bag> bags = Lists.newArrayList();
	
	//1 - ∞ Character to Skills
	@OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Skills> skillsList = Lists.newArrayList();
}
