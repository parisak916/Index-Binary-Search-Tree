/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indextree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Parisa Khan
 */

public class IndexNode implements Comparable<IndexNode> {

	// The word for this entry
	String word;
	// The number of occurences for this word
	int occurences;
	// A list of line numbers for this word.
	ArrayList<Integer> list; 
	IndexNode left;
	IndexNode right;
	
	
	//Constructors
        
        public IndexNode() {
            word = null;
            occurences = 0;
            this.left = null;
            this.right = null;
            list = new ArrayList<Integer>();
        }
        
	public IndexNode(String e){
		word = e;
                occurences = 1;
                this.left = null;
                this.right = null;
                list = new ArrayList<Integer>();
	}
       
        
        //GETTERS
	public String getWord() {
		return word;
	}
        
        public int getOccurence() {
            return occurences;
        }
        public ArrayList getLine() {
            return list;
        }
        
        //SETTERS
        public void setOccurance(int occurance) {
            occurences = occurance;
        }

	public void setWord(String item) {
		word = item;
	}
        
        public void setLine(ArrayList e) {
            list = e;
        }
        
	public void addLine(int linenumber) {
            list.add(linenumber);
        }
       
	
        //compares word of indexnode caller with the indexnode that gets sent in
        @Override
        public int compareTo(IndexNode otherEntry) {
            int x = word.compareTo(otherEntry.toString());
            return x;
        }

	// return the word and the lines it appears on.
	// string must be one line
        @Override
	public String toString(){
            System.out.print("Word:" + word);
            System.out.print(" Occurences:" + occurences);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(" Line:" + list.get(i));
            }
        return "";
        
        
	}
	
	
	
}