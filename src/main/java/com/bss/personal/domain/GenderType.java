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
@Table(name = "\"Gender_Type\"", schema = "Public")
public class GenderType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_GenderType\"", allocationSize=1)
	@Column(name="\"Gender_Id\"", unique=true, nullable=false)         
	private Long id;
	
	@Column(name="\"Gender_Type\"")
	private String gendertype;
}
