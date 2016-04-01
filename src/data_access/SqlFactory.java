package data_access;

import java.sql.PreparedStatement;

import view.BodyForm.AvailableRequest;

public class SqlFactory {

	
	
	
	public static PreparedStatement getStatement(AvailableRequest req){

		switch(req){

		case AUTHOR_SCIENCE_FICTION : 
			return authorScienceFiction();
		case COMIC_DETAIL:	
			return comicDetail(); 
		case OLDEST_AUTHOR:
			return oldestAuthor(); 
		case PUBLISHER_AVERAGE_NOVEL_PRICE:
			return publisherAverageNovelPrice(); 
		case TEN_MOST_PUBLISHED_AUTHORS: 
			return tenMostPublishedAuthors();
		case THREE_MOST_POPULAR_TITLE: 
			return threeMostPopularTitle(); 
		case YEAR_TO_TOTAL_PUB: 
			return yearToTotalPub();
		case YOUNGEST_AUTHOR: 
			return youngestAuthor();
		default: 
			throw new IllegalArgumentException();

		}
		
	}
	
	
	private static PreparedStatement authorScienceFiction(){
		
		return null;
	}
	
	private static PreparedStatement comicDetail(){
		
		return null;
	}
	
	private static PreparedStatement oldestAuthor(){
		
		return null;
	}
	
	private static PreparedStatement youngestAuthor(){
		
		return null;
	}
	
	private static PreparedStatement publisherAverageNovelPrice(){
		
		return null;
	}
	
	private static PreparedStatement tenMostPublishedAuthors(){
		
		return null;
	}
	
	private static PreparedStatement threeMostPopularTitle(){
		
		return null;
	}
	
	private static PreparedStatement yearToTotalPub(){
	
		return null;
	}
	
	
	
	
	
}
