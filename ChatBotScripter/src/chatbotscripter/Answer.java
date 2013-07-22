package chatbotscripter;

import java.util.ArrayList;

/**
 *   
 * @author Garrett
 * 
 * Class that will hold an answers information
 */
public class Answer {

	ArrayList<Integer> nodes;
	String answer;
	
        /**
         * Constructor only taking the actual answer
         * @param a : The answer 
         */
	public Answer(String a){
		this.answer = a;
		nodes = new ArrayList<Integer>();
	}
        
        /**
         * Constructor, if the answer has a reply to it
         * @param a : The answer
         * @param nodes : The nodes of the responses
         */
        public Answer(String a, ArrayList<Integer> nodes){
            this.answer = a;
            this.nodes = new ArrayList<Integer>(nodes);
        }
	
        /**
         * Adds a node to the arraylist of current nodes
         * @param node : The node to add
         */
	public void addNode(int node){
            if(nodes.contains(node)) return;
            nodes.add(node);
	}
	
        /**
         * Returns a string interpretation of the Answer, same as what's used in saving and loading
         * @return : String representation of the answer
         */
        public String toString(){
            String nodesString = "";
            for(int i = 0; i < nodes.size(); i++){
                nodesString+=nodes.get(i) + " ";
            }
            
            return answer + " | " +  nodesString;
        }
}
