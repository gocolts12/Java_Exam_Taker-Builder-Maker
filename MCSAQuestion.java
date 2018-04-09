
package HW_4;

import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MCSAQuestion extends MCQuestion{
	
	private static double points;
	private char singleChar;
	private String qPrompt;

	
	//Default Constructor 
	public MCSAQuestion(String qMsg, double maxVal) {
		super(qMsg, maxVal);
		points = maxVal;
		qPrompt = qMsg;
	}
	
	//NEW Constructor
	public MCSAQuestion (Scanner sc) {
		super(sc);
		/*String userInput = sc.nextLine();
		
		System.out.println("USER INPUT IN MCSA: " + userInput);
		
		if (isDouble(userInput)) {
			points = Double.parseDouble(userInput);
		}
		userInput = sc.nextLine();
		System.out.println("New USERINPUT in MCSA: " + userInput);
		qPrompt = userInput;
		*/
		
	}
	

	//Helper Function
	private boolean isDouble(String msg) {
		
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		
	}
	
	
	public Answer getNewAnswer() {
		
		MCSAAnswer mc = new MCSAAnswer("", 0.0);
			
		return mc; 
	}
	
	
	//Method Overload
	public Answer getNewAnswer(String ansMsg, double creditifSelected) {
		MCSAAnswer mc = new MCSAAnswer (ansMsg, creditifSelected);
		return mc; 
	}
	
	//Select Answer and unselectAnswer
	public void getAnswerFromStudent() {
			//Ask Student for answer and then store it...
			//Scanner sc = new Scanner(System.in); 				//Scanner sc = ScannerFactory.getKeyboardScanner();
			Scanner sc = new Scanner(System.in);
			
			String userMsg;
			userMsg = sc.next(); //next or nextLine() for sentence
		
			singleChar = userMsg.charAt(0); 	//Moved to top
		
			singleChar = Character.toUpperCase(singleChar);
		
			//Ascii checks if User entered A..E or a..e
			if (singleChar < 65 || singleChar > 69) {
				System.out.println("Error: User did not enter a letter within A..E");
				
			}

		for (int i =0; i < ansArray.size(); i++) {
				
			int letter = singleChar - 65; 
			
			//Check if the letter is in the range of A..E
			if (letter >= 0 && letter < 5) {
				//Add another check here
				if (letter < ansArray.size()) {
					if (ansArray.get(letter).multiText == ansArray.get(i).multiText) {
						studentAnswer = new MCSAAnswer (ansArray.get(letter).multiText, ansArray.get(letter).creditSelected);
					}
				} else {
					i = ansArray.size(); //Kicks out of the loop
					System.out.println("Error: Test user entered an answer that wasn't initialized.");
					studentAnswer = new MCSAAnswer("", 0.0);
				}					
			} else {
				i = ansArray.size();	//Kicks out of the loop
				//System.out.println("Error: User entered a Letter other than A..E");
				studentAnswer = new MCSAAnswer ("", 0.0);
			}
		}
	}

	
	//Calls the getCredit method and see if its a 1 or something
	public double getValue () {
		
		/*
		try {
			if (studentAnswer.getCredit(rightAnswer) == 1) {
				return points;
			} else {
				return 0.0;
			}
		
		} catch (Exception e) {
			
			//System.out.println("System Error: " + e);
			return maxValue;
		}
		*/
		
		return super.getValue((MCSAAnswer) studentAnswer);

	}

	
	//NEW: Method 
	@Override
	public void save(PrintWriter savedWrite) {
		
		/*
		//Ascii checks if User entered A..E or a..e
		if (singleChar < 65 || singleChar > 69) {
			System.out.println("Error: User did not enter a letter within A..E");
		} else {
			int letter = singleChar - 65;			
			savedWrite.println(ansArray.get(letter).multiText);
			
			//NEW
			//savedWrite.close();
		}*/
		
		savedWrite.println(points);
		savedWrite.println(qPrompt);
		savedWrite.println(ansArray.size());
		
		super.save(savedWrite);

	}
	
	
	
	

}
