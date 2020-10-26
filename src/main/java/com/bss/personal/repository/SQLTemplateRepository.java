package com.bss.personal.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bss.personal.domain.Bag;
import com.bss.personal.domain.Item;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class SQLTemplateRepository {
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Bag>  testSQL() {
//		Long id="";
		List<Bag> bags = new ArrayList<>();
		String sql = "SELECT * FROM \"Bag\" WHERE \"Char_Id\" = '4' ";
		//Object param = new Object[]{id};
		try {
			System.out.println("Starting test SQL...");
			bags = jdbcTemplate.query(sql, new bagsMapper());
		}catch(Exception e) {
			log.error("Error",e);
		}
		return bags;
	}

	public Boolean deleteAccount(Long charId) {
		String deleteAcc = "";
		deleteCharacter(charId);
		return true;
	}
	
	
	
	public Long newBag(String bagName,Long characterId) {
		//INSERT INTO "Bag"(Bag_Id", "Bag_Name", "Char_Id")VALUES (nextval('public."Seq_Bag"'), '', ?) WHERE "Char_Id"=4;
		String sql="INSERT INTO \"Bag\" (\"Bag_Id\",\"Bag_Name\", \"Char_Id\") VALUES (nextval('public.\"Seq_Bag\"'), '"+bagName+"', "+characterId+");";
		String getValue ="SELECT \"Bag_Id\" FROM \"Bag\" WHERE \"Bag_Name\"='"+bagName+"'";
		Long result = null;

		System.out.println("Starting newBag SQL...");
		jdbcTemplate.update(sql);
		Long value = null;
		//Gets the Bag_Id from nextVal()
		return jdbcTemplate.query(getValue, new ResultSetExtractor<Long>() {
			public Long extractData(ResultSet rs) throws SQLException{
				Long result = null;
				while(rs.next()) {result=rs.getLong("Bag_Id");}
				return result;
			}
		});	
	}
	
	public Boolean insertBags(String bagName, Long characterId) {
//		String sql="UPDATE \"Bag\" SET \"Bag_Name\"='"+bagName+"' WHERE  \"Bag_Id\"= "+bagId+" ";
		System.out.println("bagName and charId: "+ bagName+ " "+characterId);
		String sql="INSERT INTO \"Bag\" (\"Bag_Id\",\"Bag_Name\", \"Char_Id\") VALUES (nextval('public.\"Seq_Bag\"'), '"+bagName+"', "+characterId+");";
		try {
			System.out.println("Starting insertBags SQL...");
			jdbcTemplate.update(sql);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean updateBags(String bagName, Long bagId) {
		String sql="UPDATE \"Bag\" SET \"Bag_Name\"='"+bagName+"' WHERE  \"Bag_Id\"= "+bagId+" ";
		try {
			System.out.println("Starting insertBags SQL...");
			jdbcTemplate.update(sql);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean newItem(String name, Long cost, String desc, Long bagId) {
		String sql="INSERT INTO \"Item\"(\"Item_Id\",\"Name\",\"Cost\",\"Desc\",\"Bag_Id\") VALUES (nextval('public.\"Seq_Item\"'),'"+name+"',"+cost+",'"+desc+"',"+bagId+");";
		try {
			System.out.println("Starting newItem SQL...");
			jdbcTemplate.update(sql);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean insertItems(String name, Long cost, String desc, Long bagId) {
//		String sql="UPDATE \"Item\" SET \"Name\"='"+name+"', \"Cost\"="+cost+", \"Desc\"='"+desc+"' WHERE \"Item_Id\"= "+itemId+"";
		String sql="INSERT INTO \"Item\"(\"Item_Id\", \"Name\", \"Cost\", \"Desc\", \"Bag_Id\")	VALUES (nextval('public.\"Seq_Item\"'), '"+name+"', "+cost+", '"+desc+"', "+bagId+")";
		try {
			System.out.println("Starting updateItems SQL...");
			jdbcTemplate.update(sql);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean deleteAllBags(Long characterId) {
		String deleteItems = "DELETE FROM \"Item\" USING \"Bag\" WHERE \"Bag\".\"Bag_Id\"=\"Item\".\"Bag_Id\" AND \"Bag\".\"Char_Id\"="+characterId+"";
		String deleteBags = "DELETE FROM \"Bag\" WHERE \"Char_Id\"="+characterId+"";
		try {
			System.out.println("Starting deleteItems SQL...");
			jdbcTemplate.update(deleteItems);
			System.out.println("Starting deleteBags SQL...");
			jdbcTemplate.update(deleteBags);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean deleteAllItems( Long bagId) {
		String deleteItems = "DELETE FROM \"Item\" WHERE \"Bag_Id\" ="+bagId+"";
		try {
			System.out.println("Starting deleteEverything SQL...");
			jdbcTemplate.update(deleteItems);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	
	public Boolean newCharacter(String name, Long genderId, Long raceId, Long classId, String origin, Long accId) {
		String newCharacter = "INSERT INTO \"Character\"(\"Char_Id\", \"Name\", \"Gender_Id\", \"Race_Id\", \"Class_Id\", \"Origin\", \"Acc_Id\") VALUES (nextval('public.\"Seq_Character\"'), '"+name+"', "+genderId+", "+raceId+", "+classId+", '"+origin+"', "+accId+")";
		try {
			System.out.println("Starting newCharacter SQL...");
			jdbcTemplate.update(newCharacter);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	public Boolean updateCharacter(String name, Long genderId, Long raceId, Long classId, String origin,Long charId) {
		String updateCharacter="UPDATE \"Character\" SET \"Name\"='"+name+"', \"Gender_Id\"="+genderId+", \"Race_Id\"="+raceId+", \"Class_Id\"="+classId+", \"Origin\"='"+origin+"' WHERE \"Char_Id\"="+charId+"";
		try {
			System.out.println("Starting updateCharacter SQL...");
			jdbcTemplate.update(updateCharacter);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	
	
	public Boolean deleteCharacter(Long charId) {
		String delItems="DELETE FROM \"Item\" USING \"Bag\" WHERE \"Bag\".\"Bag_Id\"=\"Item\".\"Bag_Id\" AND \"Bag\".\"Char_Id\"="+charId+"";
		String delBags= "DELETE FROM \"Bag\" WHERE \"Char_Id\"="+charId+"";
		String delCharacter="DELETE FROM \"Character\"	WHERE \"Char_Id\"="+charId+"";
		try {
			System.out.println("Starting deleteCharacter SQL...");
			jdbcTemplate.update(delItems);
			jdbcTemplate.update(delBags);
			jdbcTemplate.update(delCharacter);
			return true;
		}catch(Exception e) {
			log.error("Error",e);
			return false;
		}
	}
	
	
	
	
	//Bag Outline setter??
	public final class bagsMapper implements RowMapper<Bag>{
//		private List<Bag> bags;
		public Bag mapRow(ResultSet rs, int rowNum) throws SQLException{
			Bag bag = new Bag();

			bag.setId(rs.getLong("Bag_Id"));
//			bag.setBagName(rs.getString("Bag_Name"));
//			bag.setCharId(rs.getLong(("Char_Id")));
			return bag;
		}
	}
	public final class itemsMapper implements RowMapper<Item>{
		//		private List<Bag> bags;
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException{
			Item item = new Item();
			item.setId(rs.getLong("Item_Id"));
			item.setName(rs.getString("Name"));
			item.setCost(rs.getLong("Cost"));
			item.setDesc(rs.getString("Desc"));
			item.setBagId(rs.getLong("Bag_Id"));
			return item;
		}
	}
	
	
	
	
}
