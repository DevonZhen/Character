package com.bss.personal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bss.personal.domain.Skills;

@Repository
public interface SkillsRepository extends BaseRepository<Skills, Long>{

}
/*
	//Load Skills of a Specific Character [Skills Page]
	SELECT "Skill_Name","Skill_Damage","Skill_Cost","SkillType_Id"  FROM "Skills" 
	JOIN "Character" ON "Skills"."Char_Id" = "Character"."Char_Id" 
	WHERE "Character"."Name" = 'Thor'
	
*/