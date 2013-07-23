package chatbotscripter;

import java.util.List;

/**
 * 
 * @author Garrett
 * 
 * Class that holds all a questions information
 */
public class Question {

	String question;
	int answerNode;
	boolean isResponse;
	
        /**
         * Constructor
         * @param question : Question
         * @param node : Answer node, what answer goes with this question
         * @param isResponse : If the question is a response or not
         */
	public Question(String question, int node, boolean isResponse){
		this.question = question;
		this.answerNode = node;
		this.isResponse = isResponse;
	}
        
        /**
         * Modified toString method, same format as saving the questions to make easier
         * @return : String interpretation of the class
         */
        public String toString(){
            
            String letter = (isResponse) ? "T" : "F";
            
            return question + " | " + answerNode + " | " + letter;
        }
	
}