package assignment;
import java.io.*;
import java.util.ArrayList;

/*
 * CS 314H Assignment 2 - Random Writing
 *
 * Your task is to implement this RandomWriter class
 */
public class RandomWriter implements TextProcessor {
	
	private int analysisLevel;
	private String passageStorage;
	private ArrayList<Character> charStrings;	

    public static void main(String[] args) 
    {
    	String inputFilename = args[0];
    	String outputFilename = args[1];
    	int length = Integer.parseInt(args[3]);
//    	System.out.println(inputFilename + outputFilename+level+length);
    	
    	TextProcessor t = RandomWriter.createProcessor(Integer.parseInt(args[2]));
    	
    	try {
			t.readText(inputFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }

    // Unless you need extra logic here, you might not have to touch this method
    public static TextProcessor createProcessor(int level) {
      return new RandomWriter(level);
    }

    private RandomWriter(int level) {
      // Do whatever you want here
    	analysisLevel = level;
    	passageStorage = "";
    	charStrings = new ArrayList<Character>();
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
//    	getAnalysisLevel();
    	int passageLength = passageStorage.length();
    	int index =0;
    	int indexReach =0;
    	int seedIndex =0;
    	char chosenLetter = ' ';
    	String seed = "";
    	String output = "";
    	
    	if(getAnalysisLevel()==0)
    	{
    		while (output.length()<length)
    			output += passageStorage.charAt((int) (Math.random()*passageLength));
    	}
    	
    	else
    	{
    	
    		//creating 1st seed
    		index = (int) (Math.random()*passageLength);
        	indexReach = (int) (Math.random()*passageLength)+getAnalysisLevel()-1;
    		while(indexReach>passageStorage.length()-1)
    			{
    			  index = (int) (Math.random()*passageLength);
    			  indexReach = index+getAnalysisLevel()-1;
    			}
    		
    		seed = passageStorage.substring(index, indexReach);
    	
    		while(output.length()<length)
    	{

    		seedIndex = passageStorage.indexOf(seed, 0);
    		
    		while(seedIndex<=passageStorage.length()-2)
    		{
    			charStrings.add((passageStorage.charAt(seedIndex+1)));
    			seedIndex = passageStorage.indexOf(seed, seedIndex+1);
    			
    		}
    		
    		chosenLetter = charStrings.get((int)Math.random()*charStrings.size());
    		output += chosenLetter;
    		seed = seed.substring(1)+chosenLetter;	
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
}
