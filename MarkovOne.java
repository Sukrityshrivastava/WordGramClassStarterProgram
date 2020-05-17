
/**
 * Write a description of MarkovOne here.
 * 
 * @author (Sukrity Shrivastava) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
//public class MarkovOne {
public class MarkovOne extends AbstractMarkovModel {
    //private String myText;
    //private Random myRandom;

    public MarkovOne() {
       myRandom=new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index , index+1);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key " + " key " + follows);
            if(follows.size()==0){
                break;
            }
            index=myRandom.nextInt(follows.size());
            String next=follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }

        return sb.toString();
    }
    
    @Override
    public String toString(){
        return String.format("MarkovModel Of Order %d " ,1);
    }
    
    
    /*
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos=0;
        while(pos < myText.length()){
            int start = myText.indexOf(key,pos);
            if(start == -1){
                break;
            }
            if(start+key.length() >= myText.length()-1){
                break;
            }
            String next=myText.substring(start+key.length(),start+key.length()+1);
            follows.add(next);
            pos=start+key.length();
        }
        return follows;
    }
    */

}