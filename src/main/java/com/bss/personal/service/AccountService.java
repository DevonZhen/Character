package com.bss.personal.service;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bss.personal.domain.Account;
import com.bss.personal.domain.Bag;
import com.bss.personal.domain.Character;
import com.bss.personal.domain.Item;
import com.bss.personal.domain.Skills;
import com.bss.personal.dto.AccountDTO;
import com.bss.personal.dto.BagDTO;
import com.bss.personal.dto.CharacterDTO;
import com.bss.personal.dto.ItemDTO;
import com.bss.personal.dto.SkillsDTO;
import com.bss.personal.repository.AccountRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	String status;
	
	//1.Retrieve Person All List
	public List<AccountDTO> findAll() 
	{	
		//log.info("Find all records method starting...");
		//#1.Retrieve the date from DB and fill up into List of the Domain Objects.
		List<Account> AccountList=  accountRepository.findAll();
		//#2.Copy the data from the List of the Domain Objects into List of the DTO Objects
		List<AccountDTO> AccountListDTOs = AccountList.stream().map(toAccountDTO).collect(Collectors.toList());
		//List<ContractDTO> listContractDTOs = Lists.transform(domainList, toContractDTO);
		return AccountListDTOs;
	}
	
	/*--------------------------------toAccountDTO2--------------------------------*/
	//Retrieve Login credentials
	public AccountDTO loginCredentials(String username, String password) {
		status="login"; 
		Account account = accountRepository.findByUsernameAndPassword(username, password);
		AccountDTO accountDTO = toAccountDTO2.apply(account,status);
		return accountDTO;

	}
	
	//Retrieve Character List Names By Account
	public AccountDTO getAccbyName(String username) {
		status="characterList";
		Account account = accountRepository.getAccbyName(username);
		AccountDTO accountDTO = toAccountDTO2.apply(account,status);
		return accountDTO;	
	}
	
	//Retrieve Character's Skills
	public AccountDTO getSkillsbyCharacterName(String name) {
		status="skills";
		Account account = accountRepository.getCharacterSkills(name);
		AccountDTO accountDTO = toAccountDTO2.apply(account,status);
		return accountDTO;	
	}
	
	//Retrieve Character's Info/Bag/Items
	public AccountDTO getCharacterInfo(String name) {
		status="about";
		Account account = accountRepository.getCharacterInfo(name);
		AccountDTO accountDTO = toAccountDTO2.apply(account,status);
		return accountDTO;	
	}
	
	/*--------------------------------new Domains--------------------------------*/
	//New Account
	public AccountDTO newAccount(AccountDTO accountDTO) {
		//New 'DTO' onto DTO
		Account account = newAccountDomain.apply(accountDTO);
		//Repository Annotation has the save function
		accountRepository.save(account);
		return accountDTO;
	}
	
	//New Character
	public CharacterDTO newCharacter(CharacterDTO characterDTO) {
		return null;	
	}
	
	//Delete Account
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);	
	}	
	
	//Update Account
	public AccountDTO updateAccount(AccountDTO accountDTO) {
		//Checks DB for Frontend Data's Account Id
		Account account = accountRepository.getAccbyId(accountDTO.getId());
		if(account == null)
			 throw new RuntimeException(String.format("Cannot find person record with id '%d'", accountDTO.getId()));
		accountDomainUpdate.accept(accountDTO, account);
		return accountDTO;
	}
	
	//Update Character?
	public CharacterDTO updateBag(CharacterDTO	characterDTO) {
		//Checks DB for Frontend Data's 
		Account account = accountRepository.getCharacterInfobyId(characterDTO.getId());
		if(account == null)
			 throw new RuntimeException(String.format("Cannot find Character record with id '%d'", characterDTO.getId()));
//		bagDomainUpdate.accept(characterDTO, account);
		return characterDTO;
	}
	
	//Update 
	
	
	
	//Update Account Domain
	private BiConsumer<AccountDTO, Account> accountDomainUpdate = new BiConsumer<AccountDTO, Account>() {
		@Override
		public void accept(AccountDTO accountDTO, Account account) {
			account.setId(accountDTO.getId());
			account.setUsername(accountDTO.getUsername());
			account.setPassword(accountDTO.getPassword());
			account.setEmail(accountDTO.getEmail());
			account.setFirstName(accountDTO.getFirstName());
			account.setLastName(accountDTO.getLastName());
			account.setBirthday(accountDTO.getBirthday());
			account.setCountry(accountDTO.getCountry());
			account.setStatusId(accountDTO.getStatusId());
		}
	};
	
	
	
	
	
	//New Account Domain
	private Function<AccountDTO, Account> newAccountDomain = new Function<AccountDTO, Account>(){
		@Override
		public Account apply(AccountDTO accountDTO) {
			//New Object aka Empty
			Account account = new Account();
			account.setId(accountDTO.getId());
			account.setUsername(accountDTO.getUsername());
			account.setPassword(accountDTO.getPassword());
			account.setEmail(accountDTO.getEmail());
			account.setBirthday(accountDTO.getBirthday());
			account.setFirstName(accountDTO.getFirstName());
			account.setLastName(accountDTO.getLastName());
			account.setCountry(accountDTO.getCountry());
			account.setStatusId(accountDTO.getStatusId());
			return account;
		}
	};
	
	/*------------------------------------------- DTO1/2 Series-------------------------------------------*/
	private BiFunction<Account, String, AccountDTO> toAccountDTO2 = new BiFunction<Account,String, AccountDTO>() {
		public AccountDTO apply(Account account, String status) {
			AccountDTO accountDTO = new AccountDTO();
			System.out.println("Status: "+status);
			try {
				if(status.equals("login")) {
					if(account.getId()==null || account.getPassword()==null ) {
						System.out.println("It's Wrong!!!");
						accountDTO.setUsername(null);
						accountDTO.setPassword(null);
					}
					else {
						accountDTO.setId(account.getId());
						accountDTO.setUsername(account.getUsername());
						accountDTO.setPassword(account.getPassword());
						accountDTO.setEmail(account.getEmail());
						accountDTO.setBirthday(account.getBirthday());
						accountDTO.setFirstName(account.getFirstName());
						accountDTO.setLastName(account.getLastName());
						accountDTO.setCountry(account.getCountry());
						accountDTO.setStatusId(account.getStatusId());
					}
				}
				else if(status.equals("characterList")) {
					accountDTO.setCharacterList(account.getCharacterList().stream()
							  .map(CharListDTO).collect(Collectors.toList()));	
				}
				else if(status.equals("skills")) {
					accountDTO.setCharacterList(account.getCharacterList().stream()
							  .map(toCharacterDTO1).collect(Collectors.toList()));	
				}
				else if(status.equals("about")) {
					accountDTO.setCharacterList(account.getCharacterList().stream()
							  .map(toCharacterDTO2).collect(Collectors.toList()));	
				}
			}
			catch(Exception e) {
				log.error("Error Occurred: toAccountDTO2()", e);
			}
			return accountDTO;
		}
	};	
	
	private Function<Character, CharacterDTO> CharListDTO = new Function<Character, CharacterDTO>(){
		public CharacterDTO apply(Character character){
			CharacterDTO charDTO = new CharacterDTO();
			if(character != null){
				charDTO.setId(character.getId());
				charDTO.setName(character.getName());
				charDTO.setGenderId(character.getGenderId());
				charDTO.setRaceId(character.getRaceId());
				charDTO.setClassId(character.getClassId());
				charDTO.setOrigin(character.getOrigin());
				charDTO.setAccId(character.getId());
			}
			return charDTO;
		}
	};
	

	private Function<Character, CharacterDTO> toCharacterDTO1 = new Function<Character, CharacterDTO>(){
		public CharacterDTO apply(Character character){
			CharacterDTO charDTO = new CharacterDTO();
			if(character != null){
				charDTO.setId(character.getId());
				charDTO.setName(character.getName());
				charDTO.setGenderId(character.getGenderId());
				charDTO.setRaceId(character.getRaceId());
				charDTO.setClassId(character.getClassId());
				charDTO.setOrigin(character.getOrigin());
				charDTO.setAccId(character.getId());
				//Goes to ORIGINAL SKILLS DTO Function
				charDTO.setSkillsList(character.getSkillsList().stream()
						   .map(toSkillsDTO).collect(Collectors.toList()));
			}
			return charDTO;
		}
	};
	
	private Function<Character, CharacterDTO> toCharacterDTO2 = new Function<Character, CharacterDTO>(){
		public CharacterDTO apply(Character character){
			CharacterDTO charDTO = new CharacterDTO();
			if(character != null){
				charDTO.setId(character.getId());
				charDTO.setName(character.getName());
				charDTO.setGenderId(character.getGenderId());
				charDTO.setRaceId(character.getRaceId());
				charDTO.setClassId(character.getClassId());
				charDTO.setOrigin(character.getOrigin());
				charDTO.setAccId(character.getId());
				//Goes to ORIGINAL BAG/ITEM DTO Function
//				charDTO.setBags(character.getBags().stream()
//						   .map(toBagDTO).collect(Collectors.toList()));
				charDTO.setBags(character.getBags().stream()
						.map(toBagDTO).collect(Collectors.toList()));
			}
			return charDTO;
		}
	};

	/*---------------------------------------- DTO1/2 Series Above----------------------------------------*/
	
	
	
	
	/*-------------------------------List of [ORIGINAL] toNameDTOs functions-------------------------------*/
	
	private Function<Account, AccountDTO> toAccountDTO = new Function<Account, AccountDTO>() {
		public AccountDTO apply(Account account) {
			AccountDTO accountDTO = new AccountDTO();
			try {
				accountDTO.setId(account.getId());
				accountDTO.setUsername(account.getUsername());
				accountDTO.setPassword(account.getPassword());
				accountDTO.setEmail(account.getEmail());
				accountDTO.setBirthday(account.getBirthday());
				accountDTO.setFirstName(account.getFirstName());
				accountDTO.setLastName(account.getLastName());
				accountDTO.setCountry(account.getCountry());
				accountDTO.setStatusId(account.getStatusId());
				accountDTO.setCharacterList(account.getCharacterList().stream()
						  .map(toCharacterDTO).collect(Collectors.toList()));
			}
			catch(Exception e) {
				log.error("function error toPersonDTO()", e);
			}
			return accountDTO;
		}
	};	
	
	
	
	private Function<Character, CharacterDTO> toCharacterDTO = new Function<Character, CharacterDTO>(){
		public CharacterDTO apply(Character character) {
			CharacterDTO charDTO = new CharacterDTO();
			if(character != null) {
				charDTO.setId(character.getId());
				charDTO.setName(character.getName());
				charDTO.setGenderId(character.getGenderId());
				charDTO.setRaceId(character.getRaceId());
				charDTO.setClassId(character.getClassId());
				charDTO.setOrigin(character.getOrigin());
				charDTO.setAccId(character.getId());
				charDTO.setBags(character.getBags().stream()
						.map(toBagDTO).collect(Collectors.toList()));
				charDTO.setSkillsList(character.getSkillsList().stream()
					   .map(toSkillsDTO).collect(Collectors.toList()));
				
			}
			return charDTO;
		}
	};

	
	private Function<Bag, BagDTO> toBagDTO = new Function<Bag, BagDTO>() {
		@Override
		public BagDTO apply(Bag bag) {
			BagDTO bagDTO = new BagDTO();
			if(bag != null) {
				bagDTO.setId(bag.getId());
				bagDTO.setBagName(bag.getBagName());
//				bagDTO.setBagSize(bag.getBagSize());
				bagDTO.setCharId(bag.getCharId());
				bagDTO.setItemList(bag.getItemList().stream()
					  .map(toItemDTO).collect(Collectors.toList()));
			}
			return bagDTO;
		}
	};
	
	private Function<Item, ItemDTO> toItemDTO = new Function<Item, ItemDTO>(){
		@Override
		public ItemDTO apply(Item item) {
			ItemDTO itemDTO = new ItemDTO();
			if(item != null) {
				itemDTO.setId(item.getId());
				itemDTO.setName(item.getName());
				itemDTO.setCost(item.getCost());
				itemDTO.setDesc(item.getDesc());
				itemDTO.setBagId(item.getBagId());
			}
			return itemDTO;
		}
	};
	
	private Function<Skills, SkillsDTO> toSkillsDTO = new Function<Skills, SkillsDTO>(){
		@Override
		public SkillsDTO apply(Skills skills) {
			SkillsDTO skillsDTO = new SkillsDTO();
			if(skills != null)
			{
				skillsDTO.setId(skills.getId());
				skillsDTO.setSkillName(skills.getSkillName());
				skillsDTO.setSkillDamage(skills.getSkillDamage());
				skillsDTO.setSkillCost(skills.getSkillCost());
				skillsDTO.setSkillType(skills.getSkillType());
			}
			return skillsDTO;
		}
		
	};
	/*-----------------------------------------------------------------------------------------------------*/
	
}

