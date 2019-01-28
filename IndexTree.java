/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indextree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Parisa Khan
 */
public class IndexTree {

	// This is your root 
	private IndexNode root;
        ArrayList<IndexNode> list;
	

        public IndexTree() {
            root = null;
             list = new ArrayList<IndexNode>();
        }
       
	
	// call your recursive add method
	public void add(String word, int lineNumber){
            if(root == null) {
               root = new IndexNode(word);
               root.addLine(lineNumber);
               list.add(root);
                
            }
            else {
            add(root, word, lineNumber); 
            }
		
	}
	
	
        //a to z - switch compare signs to reverse order but have to reverse delete signs too
	private IndexNode add(IndexNode root, String word, int lineNumber){
            
            //original root has alrady been taken care of
            
            int compare = word.compareTo(root.getWord());
            
            if(compare == 0) {
               int x = root.getOccurence();
               root.setOccurance(x+1);
               root.addLine(lineNumber);
               
            }
            
            //if word is less than root --> add to the left subtree
            else if(compare < 0) {
                if(root.left != null) {
                    add(root.left, word, lineNumber);
                }
                else {
                    root.left = new IndexNode(word);
                    root.left.addLine(lineNumber);
                    list.add(root.left);
                }
            }
            
            //if word is greater than root --> add to the right subtree
            else if(compare > 0 )
            {
                if(root.right != null) {
                    add(root.right, word, lineNumber);
                }
                
                else {
                    root.right = new IndexNode(word);
                    root.right.addLine(lineNumber);
                }
            }
		return root;
	}
	
	
	
	
	// returns true if the word is in the index
	public boolean contains(String word){
            for(int i = 0; i < list.size(); i++) {
                String indexnode = list.get(i).getWord();
                int compare = word.compareTo(indexnode);
                if(compare == 0){
                    return true;
                }
            }
		return false;
	}
	
	// call your recursive method
	public void delete(String word){
            try {
            root = delete(root, word);
            }
            catch(Exception e) {
                System.out.println("String '" + word + "' not found in IndexTree");
            }
	}
	
	//recursive deletion of an indexnode
	private IndexNode delete(IndexNode root, String word) {
            int comparison = word.compareTo(root.getWord());
            
            if(root == null) {
                return null;
            }
            
            //if word is less than root, search left subtree recursively
            if(comparison < 0) {
                root.left = delete(root.left, word);
                return root;
            }
            
            //if word is more than root, search right subtree recursively
            else if(comparison > 0) {
               root.right = delete(root.right, word);
               return root;
            }
            
            else  {
                //root has no children
                if(root.left == null && root.right == null){
                    return null;
			} 
                
                //root has right child only
                else if(root.left == null){
                    return root.right;
			}
                
                //root has left child only
		else if(root.right == null){
                    return root.left;
			}
                
                //root has two children
		else {  
                    
                    //finds left's right most child
                    IndexNode current  = root.left;
                    while(current.right != null){
                    current = current.right;
			}
                    
                    //swaps rightmost child with root
                    IndexNode temp = root;
                    root.setWord(current.getWord());
                    root.setLine(current.getLine());
                    root.setOccurance(current.getOccurence());
                     
                    //sets current to root that needs to be deleted
                    current.setWord(word);
                    //deletes current
                    root.left = delete(root.left, word);
				
                    return root;
				
                            }
		
                        }
                }
        

	// prints all the words in the index in inorder order
	public void printIndex(){
            printIndex(root);
            System.out.println("---------------------END");
		
	}
        public void printIndex(IndexNode root) {
            
            if( root != null) {
                printIndex(root.left);
                if(root.getWord().compareTo("") != 0) {
                System.out.println(root.toString());
                }
                printIndex(root.right);
            }
        }
	
	public static void main(String[] args){
		IndexTree index = new IndexTree();
                       
		// add all the words to the tree
            int linenumber = 1;
            String fileName = "pg2240.txt";
		String line = null;
		BufferedReader bufferedReader;

		try {
			bufferedReader= new BufferedReader(new FileReader(fileName));
			while(bufferedReader.ready()) 
                        {
                            //takes out punctuation
                            line = bufferedReader.readLine();
                            String[] split = line.replace(".", "").replace(",", "").replace("?", "").replace("!","").replace(":", "").replace("@", "")
                                    .replace("'","").replace("*","").replace("(","").replace(")","").replace('"', ' ').replace("]", "").replace("-", "")
                                    .replace("#", "").replace('/', ' ').replaceAll("\\d+.*", "").replace("$", "").replace("_", "")
                                    .replace("&","").replace("=", "").replace("<", "").replace("~", "").replace("[","").split(" ");
                            
                            //calls add for every word and makes them lowercase
                            for(int i = 0; i < split.length; i++) {
                                index.add(split[i].toLowerCase(), linenumber);
                            }
                                 linenumber++;
			}

			bufferedReader.close();         
		}
		catch(FileNotFoundException e) { 
			System.err.println("File not found");                
		}
		// catch any other exception
		catch(Exception e) {
			e.printStackTrace();
		}
                
         //PRINTS OUT THE INDEX inorder
         index.printIndex();
                
         // test removing a word from the index
         //DELETE THE LAST WORD
         index.delete("zip");
         
        //reprint the index to test delete
       index.printIndex();
        
        /*
       //tests contains
       boolean x = index.contains("the");
       if(x == true) {
           System.out.println("true");

       }
            */       
        }
}
