package utils;

import java.security.InvalidParameterException;

public enum PublicationType {
ANTHOLOGY,COLLECTION,MAGAZINE,NONFICTION,NOVEL,OMNIBUS,FANZINE,CHAPBOOK;
	

public static PublicationType get(String value){
	
	switch(value){
	case "ANTHOLOGY" : return ANTHOLOGY; 
	case "COLLECTION" : return COLLECTION;
	case "MAGAZINE" : return MAGAZINE;
	case "NONFICTION" : return NONFICTION;
	case "NOVEL" : return NOVEL;
	case "OMNIBUS" : return OMNIBUS;
	case "FANZINE" : return FANZINE;
	case "CHAPBOOK" : return CHAPBOOK;
	default: throw new InvalidParameterException();
	}
	
}
	
}
