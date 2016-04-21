package utils;

import java.security.InvalidParameterException;

public enum TitleType {
	ANTHOLOGY,BACKCOVERART,COLLECTION,COVERART,INTERIORART,EDITOR,ESSAY,INTERVIEW,NOVEL,
	NONFICTION,OMNIBUS,POEM,REVIEW,SERIAL,SHORTIFICTION,CHAPBOOK;
	
	public static TitleType get(String value){
		
		switch(value){
		case "ANTHOLOGY" : return ANTHOLOGY; 
		case "BACKCOVERART" : return BACKCOVERART; 
		case "COLLECTION" : return COLLECTION; 
		case "COVERART" : return COVERART; 
		case "INTERIORART" : return INTERIORART; 
		case "EDITOR" : return EDITOR; 
		case "ESSAY" : return ESSAY; 
		case "INTERVIEW" : return INTERVIEW; 
		case "NOVEL" : return NOVEL; 
		case "NONFICTION" : return NONFICTION; 
		case "OMNIBUS" : return OMNIBUS; 
		case "POEM" : return POEM; 
		case "REVIEW" : return REVIEW; 
		case "SERIAL" : return SERIAL; 
		case "SHORTIFICTION" : return SHORTIFICTION; 
		case "CHAPBOOK" : return CHAPBOOK; 
		default: throw new InvalidParameterException();
		}
		
	}
	
	
}
