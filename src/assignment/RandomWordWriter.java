package assignment;
import java.io.*;
import java.util.ArrayList;

/*
 * CS 314H Assignment 2 - Random Writing
 *
 * Your task is to implement this RandomWriter class
 */
public class RandomWordWriter implements TextProcessor {
	
	private int analysisLevel;
	private String passageStorage;
	private ArrayList<Object> words;
	public static Character [] characters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	
	

    public static void main(String[] args) 
    {
    	String inputFilename = args[0];
    	String outputFilename = args[1];
    	int length = Integer.parseInt(args[3]);
//    	System.out.println(inputFilename + outputFilename+level+length);
    	
    	
    	TextProcessor t = RandomWordWriter.createProcessor(Integer.parseInt(args[2]));
    	
    	try {
			t.readText(inputFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }

    // Unless you need extra logic here, you might not have to touch this method
    public static TextProcessor createProcessor(int level) {
      return new RandomWordWriter(level);
    }

    private RandomWordWriter(int level) {
      // Do whatever you want here
    	analysisLevel = level;
    	passageStorage = "";
    	words = new ArrayList<Object>();
    }


    public void readText(String inputFilename) throws IOException {
    	FileReader file = new FileReader(inputFilename);
    	BufferedReader reader = new BufferedReader(file);
    	String perLine = reader.readLine();
    	while (perLine != null)
    	{
    		passageStorage +=perLine;
    		perLine = reader.readLine();
    	}

    }

    public void writeText(String outputFilename, int length) throws IOException 
    {

    	int passageLength = passageStorage.length();
    	int index =0;
    	int indexLower =0;
    	int indexUpper =0;
    	int seedIndex =0;
    	int wordCount =0;
    	int wordCountSeed = 0;
    	int nonAlphabet =0;
    	String chosenWord = "";
    	String seed = "";
    	String output = "";
    	
    	if(getAnalysisLevel()==0)
    		while(wordCount<length)
    		{
    			output += getAWord()+" ";
    			wordCount ++;
    		}
    	
    	
    	else
    	{
    	
    		//creating 1st word
    		seed = getAWord();
    		index = passageStorage.indexOf(seed);
        	indexUpper = index;
        	indexLower = index;
    		
        	//数字对不对
        	//create the seed
        	while(nonAlphabet<=getAnalysisLevel())
        	{
        		while((indexLower-1)>=0)
        			
        		{
        			if(!isAlphabet(indexLower-1))
        				nonAlphabet++;
        			if(indexLower == 0)
        				nonAlphabet ++;
        		}
        				
        		
        		while((indexUpper+1)<=(passageStorage.length()-1))
        			{
        			if(!isAlphabet(indexUpper+1))
        				nonAlphabet++;
        			if(indexUpper == passageStorage.length()-1)
        				nonAlphabet++;
        			}
        				
        	}
        	
        	nonAlphabet =0;
        	
    		
    	
    		while(wordCount<length)
    	{

    		seedIndex = passageStorage.indexOf(seed, 0)+seed.length();
    		int upperWord = 0;
    		int lowerWord = seedIndex;
    		
//    		if(isAlphabet(seedIndex))
//    			nonAlphabet++;
//    		
//    		while(nonAlphabet<=2)
//    		{
//    			seedIndex
//    			if(!isAlphabet(seedIndex))
//    				nonAlphabet++;
//    			
//    		}
//    		
//    		words.add((passageStorage.charAt(seedIndex+1)));
//			seedIndex = passageStorage.indexOf(seed, seedIndex+1);
//    		
//    		chosenWord = (String) words.get((int)Math.random()*words.size());
//    		output += chosenWord;
    		
    		
    		
    		// not changing a char, but deleting 
    		seed = seed.substring(seed.indexOf(" ")+1)+chosenWord;	
    	}	
    	}
    	
    	//writing file to defined output source
    	
    	File newFile = new File(outputFilename);
		try {
			newFile.createNewFile();
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			FileWriter fileWriter = new FileWriter(newFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(output);
			bufferedWriter.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }

	public int getAnalysisLevel() {
		return analysisLevel;
	}
	
	public String getAWord ()
	{
		int index = (int) (passageStorage.length()*Math.random());
		int lower = index;
		int upper = index;
		
//		while ((lower-1) >=0 && (upper+1) <=(passageStorage.length()-1))
		
			//ensure a letter is chosen
			while (!isAlphabet(index))
				index = (int) (passageStorage.length()*Math.random());
			
			//form the whole word's indexes
			while(isAlphabet(lower-1)&&(lower-1)>=0)
				lower = lower -1;
			
			while(isAlphabet(upper+1) && (upper+1)<=(passageStorage.length()-1))
				upper = upper +1;
			
			return passageStorage.substring(lower, upper);
		
	}
	
	public boolean isAlphabet (int index)
	{
		for (int i=0; i<characters.length; i++)
		{
			if(characters [i]== passageStorage.charAt(index))
				return true;
		}
		
		return false;
	}
}
