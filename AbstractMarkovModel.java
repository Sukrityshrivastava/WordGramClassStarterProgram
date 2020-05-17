
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;


public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int myChar;
    
    public AbstractMarkovModel(int noc) {
        myRandom = new Random();
        myChar = noc;
    }
    
     public AbstractMarkovModel() {
         myRandom = new Random();
    }
    
    public void setRandom(int seed){
		myRandom = new Random(seed);
	}
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    
	
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        int len = key.length();
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<myText.length()-len;i++){
            if(key.equals(myText.substring(i , i+len))){
                result.add(myText.substring(i+len , i+len+1));
            }
        }
        return result;
    }
}
