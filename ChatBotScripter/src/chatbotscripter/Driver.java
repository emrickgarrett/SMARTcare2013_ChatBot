package chatbotscripter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

	
	public static void main(String[] args){
		
		TreeStructure data = new TreeStructure();
		data.loadData();
		Scanner scan = new Scanner(System.in);
		
		String currentString = "";
		while(currentString != "Exit"){
			currentString = scan.nextLine();
			if(data.queryQuestion(currentString) == -1) System.err.println("BAD INPUT");
		}
		
//		int input = 0;
//		while(input != -1){
//			
//		}
		
	}
}
