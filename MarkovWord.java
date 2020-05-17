
/**
 * Write a description of MarkovWord here.
 * 
 * @author (Sukrity Shrivastava) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder=order;           //how many words to use in prediction
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);  //generate same random text each time
    }

    public void setTraining(String text){
        myText = text.split("\\s+");  //split into words and stored in myText
                                      //words are used to initialize the training text
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        System.out.println(index);
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);;
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words,WordGram target,int start){  //where to start looking for a WordGram match in words
        for(int k=start;k<words.length;k++){
            WordGram wg=new WordGram(words,k,myOrder);
            if(wg.equals(target)){
                return k;
            }
        }
        return -1;
    }
    
    /*
    public void testIndexOf() {
        String s = "this is just a test yes this is a simple test";
        String[] words = s.split("\\s+");
        System.out.print(indexOf(words, "this", 0) + "\n");
        System.out.print(indexOf(words, "this", 3) + "\n");
        System.out.print(indexOf(words, "frog", 0) + "\n");
        System.out.print(indexOf(words, "frog", 5) + "\n");
        System.out.print(indexOf(words, "simple", 2) + "\n");
        System.out.print(indexOf(words, "test", 5) + "\n");
    }
    */

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            if(start == -1) {
                break;
            }
            if(start >= myText.length - 1) {
                break;
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }

}
