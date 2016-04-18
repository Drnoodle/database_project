package data_access;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;


public class CsvReader implements Iterator<String[]>{

	
	
	
	private BufferedReader file; 
	
	private String currentLine = null;
	
	private final String SEPARATOR = "\t";

	
	public CsvReader(String src){
		
		try {
			file = new BufferedReader(new InputStreamReader(new FileInputStream(src), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
	} 

	
	
	@Override
	public boolean hasNext() {
		try {
			currentLine = file.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentLine != null;
	}

	
	@Override
	public String[] next() {
		
		return currentLine.split(SEPARATOR);
	}

	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	public void close() throws IOException{
		file.close();
	}
	
	public static String removeWeirdChar(String str){
		return str.replaceAll("�", "").replaceAll("%%", "").replaceAll("##", " ");
	}
	
	
	public static String removeHtml(String str){
		return str.replaceAll("(<)(.+)(>)", "").replaceAll("(&)(.{2,7})(;)", "");
	}
	
	
	
}
