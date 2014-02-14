package com.oneguyonegirl.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CAHFIleParser {

	public static CAHFIleParser instance = new CAHFIleParser();
	
	
	public static CAHFIleParser getInstance(){
		
		return instance;
	}
	
	public String[] loadFile(String fileName) throws IOException{
		
		
		
		char[] textBuffer = new char[1024]; 
		StringBuffer cahBuffer = new StringBuffer();
		String[] cardArray = null;
		
		
		File file = new File(fileName);
		FileReader reader = new FileReader(file);
		
		while (reader.ready()){
			
			reader.read(textBuffer);
			cahBuffer.append(textBuffer);
		}
		reader.close();
		cardArray = cahBuffer.toString().split("<>");
		
		return cardArray;
	}
	
	
	public static void main(String[] args){
		
		CAHFIleParser parser = new CAHFIleParser();
		
		try{
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards.txt");
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards1.txt");
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards2.txt");
			
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/wcards.txt");
			
		}catch (Exception e){
			
			e.printStackTrace();
		}
		
		
	}
}


