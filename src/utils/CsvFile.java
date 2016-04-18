package utils;

import java.io.File;

public enum CsvFile {

	AUTHORS,
	PUBLICATIONS_AUTHORS,
	TAGS,
	AWARD_CATEGORIES,
	PUBLICATIONS_CONTENT,
	TITLES_AWARDS,
	AWARDS,
	PUBLICATIONS,
	TITLES,
	AWARD_TYPES,
	PUBLICATIONS_SERIES,
	TITLES_SERIES,
	LANGUAGES,
	PUBLISHERS,
	TITLES_TAG,
	NOTES,
	REVIEWS,
	WEBPAGES;

	public String path(){
		return "csv/" + this.toString().toLowerCase() + ".csv";
	}
	
}
