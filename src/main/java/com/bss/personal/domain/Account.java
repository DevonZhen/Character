package com.bss.personal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Account\"", schema = "Public")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Account\"", allocationSize=1)
	@Column(name="\"Acc_Id\"", unique=true, nullable=false)         
	private Long id;
	
	@Column(name="\"Username\"")
	private String username;
	
	@Column(name="\"Password\"")
	private String password;
	
	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"DOB\"")
	private Date birthday;
	
	@Column(name="\"First_Name\"")
	private String firstName;
	
	@Column(name="\"Last_Name\"")
	private String lastName;
	
	@Column(name="\"Country\"")
	private String country;
	
	@Column(name="\"Status_Id\"")
	private Long statusId;
	
	// 1 - ∞ Account to Characters (Account PK: Acc_Id -- Character FK: Acc_Id)
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Character> characterList = Lists.newArrayList();
	

}
