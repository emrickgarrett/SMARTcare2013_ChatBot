package chatbotscripter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Garrett
 * @param <T> 
 * 
 * This class generates a type of Tree structure. Gives them nodes pointing to 
 * other questions or answers, and also is responsible for loading the data from
 * the database and storing it within it's local variables as well.
 */
public class TreeStructure<T> {

	ArrayList<Answer> answers;
	ArrayList<Question> questions;
	ArrayList<Integer> choices;
	
        /**
         * Constructor that initializes all of the array lists
         */
	public TreeStructure(){
		answers = new ArrayList<Answer>();
		questions = new ArrayList<Question>();
		choices = new ArrayList<Integer>();
	}
	
	
        /**
         * 
         * @param question : Question to be asked
         * @return : Whether or not it is a valid response
         * 
         * Temporary set up, will eventually determine what question was asked,
         * and then perform the necessary action according to said question
         */
	public int queryQuestion(String question){
		
		for(int i = 0; i < questions.size(); i++){
			if(questions.get(i).question.equalsIgnoreCase(question)){
				
				int isGoodResponse = 0;
				
				if( !questions.get(i).isResponse ||choices.contains(i)){
					System.out.println(answers.get(questions.get(i).answerNode).answer);
					System.out.println("I was looking for this answer.");
                                        isGoodResponse = i;
					choices = new ArrayList<Integer>(answers.get(questions.get(i).answerNode).nodes);
				}else{
					isGoodResponse = -1;
				}
				
				return isGoodResponse;
			}
		}
		
		return -1;
	}
        
        /**
         * Method that asks a question, and then queries for results
         * @param question : Question to be asked
         * @return : The answer String
         */
        public String askQuestion(String question){
            int result = queryQuestion(question);
            
            if(result == -1){
                return "I don't know what you want.";
            }else{
                return answers.get(questions.get(result).answerNode).answer;
            }
        }
	
	
	/**
         * Loads the data from the database
         * For generic Chat bot at this point
         */
	public void loadData(){
		File aFile = new File("Answers.txt");
		File qFile = new File("Questions.txt");
		BufferedReader br = null;
		
		String currentLine;
		
		//Begin Answer Getting
		try{
			
			br = new BufferedReader(new FileReader(aFile));
			
			while((currentLine = br.readLine()) != null){
				//Do work
				int indexOfPipe = currentLine.indexOf("|");
				
				System.out.println(currentLine);
				String name = currentLine.substring(0, indexOfPipe-1);
				currentLine = currentLine.substring(indexOfPipe+1, currentLine.length());
				System.out.println(currentLine);
				Scanner scan = new Scanner(currentLine);
				Answer temp = new Answer(name);
				this.answers.add(temp);
				
				try{
					
					while(true){
						temp.addNode(scan.nextInt());
					}
					
				}catch(NoSuchElementException ex){
					System.err.println("No more numbers!");
				}
				
				System.out.println("Name: " + name + " + etc.etc.etc.");
				scan.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println("Something wrong with the Answers");
			
		}

		try{
			br.close();
		}catch(Exception ex){
			//Closes the stream if it was open.
		}
		
		//Begin Question Getting
		try{
			
			br = new BufferedReader(new FileReader(qFile));
			
			currentLine = "";
			
			while((currentLine = br.readLine()) != null){
				int indexOfPipe = currentLine.indexOf("|");
				
				String question = currentLine.substring(0, indexOfPipe-1);
				Scanner scan = new Scanner(currentLine.substring(indexOfPipe+1, currentLine.lastIndexOf("|")-1));
				
				int node = scan.nextInt();
				
				boolean isResponse = currentLine.contains("T");
				Question q = new Question(question, node, isResponse);
				this.questions.add(q);
				scan.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println("Something wrong with the Answers");
		}
		
		try{
		br.close();
		}catch(Exception ex){
			//Closes stream if it was open.
		}
		
	}
        
        /**
         * Loads data from a specific database, allows different chat bots
         * @param fileQ : The question file
         * @param fileA : The answer file
         */
        public void loadData(File qFile, File aFile){
		BufferedReader br = null;
		
		String currentLine;
		
		//Begin Answer Getting
		try{
			
			br = new BufferedReader(new FileReader(aFile));
			
			while((currentLine = br.readLine()) != null){
				//Do work
				int indexOfPipe = currentLine.indexOf("|");
				
				System.out.println(currentLine);
				String name = currentLine.substring(0, indexOfPipe-1);
				currentLine = currentLine.substring(indexOfPipe+1, currentLine.length());
				System.out.println(currentLine);
				Scanner scan = new Scanner(currentLine);
				Answer temp = new Answer(name);
				this.answers.add(temp);
				
				try{
					
					while(true){
						temp.addNode(scan.nextInt());
					}
					
				}catch(NoSuchElementException ex){
					System.err.println("No more numbers!");
				}
				
				System.out.println("Name: " + name + " + etc.etc.etc.");
				scan.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println("Something wrong with the Answers");
			
		}

		try{
			br.close();
		}catch(Exception ex){
			//Closes the stream if it was open.
		}
		
		//Begin Question Getting
		try{
			
			br = new BufferedReader(new FileReader(qFile));
			
			currentLine = "";
			
			while((currentLine = br.readLine()) != null){
				int indexOfPipe = currentLine.indexOf("|");
				
				String question = currentLine.substring(0, indexOfPipe-1);
				Scanner scan = new Scanner(currentLine.substring(indexOfPipe+1, currentLine.lastIndexOf("|")-1));
				
				int node = scan.nextInt();
				
				boolean isResponse = currentLine.contains("T");
				Question q = new Question(question, node, isResponse);
				this.questions.add(q);
				scan.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.err.println("Something wrong with the Answers");
		}
		
		try{
		br.close();
		}catch(Exception ex){
			//Closes stream if it was open.
		}
        }
	
}
