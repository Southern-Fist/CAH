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
	
	public String[] loadFile(final String fileName) throws IOException{
		
		
		
		final char[] textBuffer = new char[1024]; 
		final StringBuffer cahBuffer = new StringBuffer();
		String[] cardArray = null;
		
		
		final File file = new File(fileName);
		final FileReader reader = new FileReader(file);
		
		while (reader.ready()){
			
			reader.read(textBuffer);
			cahBuffer.append(textBuffer);
		}
		reader.close();
		cardArray = cahBuffer.toString().split("<>");
		
		return cardArray;
	}
	
	
	public static void main(final String[] args){
		
		final CAHFIleParser parser = new CAHFIleParser();
		
		try{
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards.txt");
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards1.txt");
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards2.txt");
			
			parser.loadFile("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/wcards.txt");
			
		}catch (final Exception e){
			
			e.printStackTrace();
		}
		
		
	}
}


