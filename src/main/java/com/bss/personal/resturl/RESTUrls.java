package com.bss.personal.resturl;

public class RESTUrls {
	
	//Variables set to URLs on web browser parameters
	final public static String URL_AccountAll="/AccountAll";
	final public static String URL_GetUsernamePassword="/Login/{username}/{password}";
	final public static String URL_CharacterListbyAccount="/CharacterList/{username}";
	final public static String URL_getCharacterSkills="/Skills/{name}";
	final public static String URL_getCharacterInfo="/About/{name}";
	
	final public static String URL_UpdateBag="/UpdateBag";
	final public static String URL_PostBag="/PostBag";
	
	final public static String URL_UpdateAccount="/UpdateAccount/{name}";
	final public static String URL_PostAccount="/PostAccount";
	final public static String URL_PostCharacter="/PostCharacter";
	final public static String URL_DeleteAccount="/DeleteAccount/{id}";
	final public static String URL_DeleteCharacter="/DeleteCharacter/{id}";

}
