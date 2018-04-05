

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class MCMAQuestion extends MCQuestion{
	
	protected ArrayList<Answer> studentAnsArr = new ArrayList<Answer>(5);
	
	private double baseCreditM;
	
	private static double points;
	private char singleChar;
	private String qqMsg;
	private int rightAnsCount;
	
	//Default Constructor 
	public MCMAQuestion(String qMsg, double maxValue, double baseCredit) {
		super(qMsg, maxValue);
		qqMsg = qMsg;
		baseCreditM = baseCredit;
		points = maxValue;
	}
	
	
	//New Constructor
	public MCMAQuestion(Scanner sc) {
		super(sc);
	}
	
	public Answer getNewAnswer() {
		MCMAAnswer mc = new MCMAAnswer("",0.0);
		return mc;
	}
	
	public Answer getNewAnswer(String ansMsg, double creditifSelected) {
		MCMAAnswer mc = new MCMAAnswer(ansMsg, creditifSelected);
		return mc;
	}
	
	public void getAnswerFromStudent() {
			//Ask Student for answer and then store it...
			//Scanner sc = new Scanner(System.in); 				
			Scanner sc = ScannerFactory.getKeyboardScanner();
		
			String userMsg;
			userMsg = sc.next(); //next or nextLine() for sentence
			
			int k = 0;
	
			//System.out.println("MCMAQuestion: " + userMsg.length());
			//System.out.println("Value of K (OUTSIDE): " + k);
			
		while (k < userMsg.length()) {
				//System.out.println("Value of K (INSIDE): " + k);
				singleChar = userMsg.charAt(k); 	//Grabs the Character	
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
						if (ansArray.get(letter).multiText.equals(ansArray.get(i).multiText)) {  	

							MCMAAnswer mcAns = new MCMAAnswer (ansArray.get(letter).multiText, ansArray.get(letter).creditSelected);
							
							studentAnsArr.add(mcAns); 
						}
					} else {
						i = ansArray.size(); //Kicks out of the loop
						System.out.println("Error: Test user entered an answer that wasn't initialized.");
						MCMAAnswer mcAns = new MCMAAnswer("", 0.0);
						studentAnsArr.add(mcAns);
					}					
				} else {
					i = ansArray.size();	//Kicks out of the loop
					//System.out.println("Error: User entered a Letter other than A..E");
					MCMAAnswer mcAns = new MCMAAnswer("",0.0);
					studentAnsArr.add(mcAns);
				}
			}
			
			k++;
		} //End of While-loop
	}
	
	//This is done
	public double getValue() {
		double totalValue = 0.0;
	
		for (int i = 0; i < studentAnsArr.size(); i++) {
			//MCMAAnswer loopAns = (MCMAAnswer) studentAnsArr.get(i);
			MCAnswer loopAns = (MCAnswer) studentAnsArr.get(i);
			totalValue += loopAns.creditSelected; 
		}
		
		/* DEBUGGING
		System.out.println("TOTAL VALUE: " + totalValue);
		System.out.println("BaseCredit: " + baseCreditM);
		System.out.println("MaxValue: " + maxValue);
		*/
		
		int g = (int) ((totalValue + baseCreditM) * maxValue);
		
		return g; //Returns the integers, Does an automatic round down I guess
	}
	
	//New Method
	@Override
	public void save(PrintWriter savedWrite) {
		
		savedWrite.println(points);		 	//Prints Points for Question
		savedWrite.println(qqMsg);		 	//Prints Message Prompt
		savedWrite.println(baseCreditM); 	//Prints base Credit
		savedWrite.println(ansArray.size()); //Prints How many Choices there are
		super.save(savedWrite);			 //Loops though all answers and display the creditselcted
	}

	
	//New Method
	public void saveStudentAnswer(PrintWriter savedWrite) {
				
		savedWrite.println("\n"+ "MCMAAnswer");
		int countRight = 0;
		
		for (int i = 0; i < ansArray.size(); i++) {
			
			MCMAAnswer tempA = (MCMAAnswer) ansArray.get(i);
			if (tempA.creditSelected > 0) {
				countRight += 1;
			}
			
		}

		rightAnsCount = countRight;
		
		savedWrite.println(rightAnsCount);
		
		//Save requires multiple inputs and then print
		for (int i = 0; i < studentAnsArr.size(); i++) {
			MCMAAnswer ans = (MCMAAnswer) studentAnsArr.get(i);
			savedWrite.println(ans.multiText);
		}
	}
	
	//New Method BROKEN ATM
	@Override
	public void restoreStudentAnswers(Scanner sc) {
			
		/*
		String parseLine = sc.nextLine();
		//String parseLine = "MCMAAnswer";
		
		//Read In the File and takes in the Exam Name
		
		//Problem with Implmentation is the MCMAQuestion 
		if (parseLine.equals("MCMAAnswer")) {
			try {
				double rAnsCount = sc.nextDouble();
				rightAnsCount = (int) rAnsCount;
				
				String userStringM = sc.next();		//Takes in UserAnswer 
				
				for (int i = 0; i < rightAnsCount; i++) {
					MCMAAnswer tempAns = new MCMAAnswer(userStringM, 1.0);
					studentAnsArr.add(tempAns);
				}
			
			
			} catch (Exception e) {
				
				System.out.println("ERROR in MCMAQuestion_RestoreAns: " + e);
			}
			
		}
		*/
		
		super.restoreStudentAnswers(sc);
		
	}

}
