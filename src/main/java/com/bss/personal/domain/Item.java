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
@Table(name = "\"Item\"", schema = "Public")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"Seq_Item\"", allocationSize=1)
	@Column(name="\"Item_Id\"", unique=true, nullable=false)         
	private Long id;

	@Column(name="\"Name\"")
	private String name;
	
	@Column(name="\"Cost\"")
	private Long cost;
	
	@Column(name="\"Desc\"")
	private String desc;
	
	@Column(name="\"Bag_Id\"")
	private Long bagId;
	
	//∞ - 1 Items to Bag
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"Bag_Id\"", nullable = false,insertable=false, updatable=false)
    private Bag bag;
	
}
