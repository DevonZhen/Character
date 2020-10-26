package com.bss.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.personal.custom.dto.BagsDTO;
import com.bss.personal.custom.dto.ItemsDTO;
import com.bss.personal.dto.BagDTO;
import com.bss.personal.dto.CharDTO;
import com.bss.personal.dto.ItemDTO;
import com.bss.personal.resturl.RESTUrls;
import com.bss.personal.service.BagService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class BagController {
	@Autowired
	BagService bagService;


	@RequestMapping(value = "/getBags", method = RequestMethod.GET)
	public ResponseEntity<List<BagDTO>> getBags() 
	{
		try {
			System.out.println("SQL GetBags Begins...");
			List<BagDTO> resultDTO = bagService.getBags();

			if(resultDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<BagDTO>>(resultDTO, HttpStatus.OK);
		}
		catch(Exception e){
			log.error("Error calling AllAccount()",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = RESTUrls.URL_UpdateBag,  produces = "application/json")
	public Boolean updateBags(@RequestBody BagsDTO bagsDTO) 
	{
		Long characterId = bagsDTO.getId();
		System.out.println("CharId: "+bagsDTO.getId());
		if(bagsDTO.getId() != null) {
			//Delete all Bags From Character
//			bagService.deleteAllItems(bagsDTO.getId());
			bagService.deleteAllBagsItems(characterId);
			System.out.println("Finished Deletion");
		}
		boolean flag=false;
		
		System.out.println("BagList: "+bagsDTO.getBags());
		try {
			Long result = null;
			for(BagDTO bagDTO: bagsDTO.getBags()) {
				System.out.println("Bags: "+bagDTO.getBagName()+ " "+characterId);
				//Get Bag_Id and insert new Bags
				 result=bagService.newBags(bagDTO.getBagName(),characterId);	
				//Items
				for(ItemDTO itemDTO: bagDTO.getItemList()){
					System.out.println("Items: "+itemDTO.getName()+ " "+bagDTO.getId());
//					if(itemDTO.getBagId() == null)
						bagService.newItem(itemDTO.getName(), itemDTO.getCost(),itemDTO.getDesc(),result);
//					else
//						bagService.insertItems(itemDTO.getName(), itemDTO.getCost(),itemDTO.getDesc(),result);
				}
			}
		}catch (Exception e) {
			log.error("Error calling UpdateBag()", e);
		}
		return flag;
	}	
	
	
	
	
	
	
	
	
	
	
	
}
