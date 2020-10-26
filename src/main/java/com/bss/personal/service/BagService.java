package com.bss.personal.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bss.personal.custom.dto.BagsDTO;
import com.bss.personal.domain.Bag;
import com.bss.personal.dto.BagDTO;
import com.bss.personal.repository.SQLTemplateRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class BagService {
	@Autowired
	SQLTemplateRepository SQLRepo;
	
	
	public List<BagDTO> getBags() 
	{	
		List<Bag> bagList = SQLRepo.testSQL();
		List<BagDTO> bagListDTO = bagList.stream().map(toBagDTO).collect(Collectors.toList());
		return bagListDTO;
	}
	
	
	public Long newBags(String bagName,Long characterId){
		return SQLRepo.newBag(bagName,characterId);
	}
	
	public Boolean insertBags(String bagName, Long bagId){
		return SQLRepo.insertBags(bagName, bagId);
	}
	
	public Boolean newItem(String name, Long cost, String desc, Long bagId) {
		return SQLRepo.newItem(name, cost, desc, bagId);
	}
	
	public Boolean insertItems(String name, Long cost, String desc, Long bagId) {
		return SQLRepo.insertItems(name, cost, desc, bagId);
	}
	
	public Boolean deleteAllItems(Long bagId) {
		return SQLRepo.deleteAllItems(bagId);
	}
	
	public Boolean deleteAllBagsItems(Long characterId) {
		return SQLRepo.deleteAllBags(characterId);
	}
	
	private Function<Bag, BagDTO> toBagDTO = new Function<Bag, BagDTO>() {
		@Override
		public BagDTO apply(Bag bag) {
			BagDTO bagDTO = new BagDTO();
			if(bag != null) {
				bagDTO.setId(bag.getId());
				bagDTO.setBagName(bag.getBagName());
				bagDTO.setCharId(bag.getCharId());
			}
			return bagDTO;
		}
	};



	
}
