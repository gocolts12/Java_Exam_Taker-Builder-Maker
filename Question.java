
package HW_4;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Question {
	
	protected String msgPrompt;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue;
	
	
	protected Question(String qMsg, double maxVal) {
		this.msgPrompt = qMsg;
		this.maxValue = maxVal;	
	}
	
	//NEW Constructor
	public Question(Scanner sc) {
		
		double mVal = 0.0;
		
		String userInput = sc.nextLine();
		
		System.out.println("Questoin input: " + userInput);
		
		if(isDouble(userInput)) {
			mVal = Double.parseDouble(userInput);
		}
		
		
		//Takes in the Point Value
		//double mVal = sc.nextDouble();
		
		//System.out.println("Question Scanner Class: " + mVal);
		
		//Skip to Next Line
		//sc.nextLine();
		
		//Grabs the Question
		String msgP = sc.nextLine();
		
		//System.out.println("Question Scanner MSGP: " + msgP);
		
		msgPrompt = msgP;
		maxValue = mVal;
		
		//System.out.println("What is MaxValue atm : " + maxValue);
		
		/* Debugging		
		System.out.println("Question String(V): " + v);
		System.out.println("Value (mVal): " + mVal);
		*/
		
	}
	
	//Helper function 
	private boolean isDouble(String msg) {
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}		
	}
	
	
	public void print() {
		//Prints question
		System.out.println(msgPrompt + " (" + maxValue + ") ");	//Print out the questions points
		//Prints studentAnswer
		if (studentAnswer != null) {
			studentAnswer.print();
		}
		
	}
	
	public void setRightAnswer(Answer ans) {
		rightAnswer = ans;
	}
	
	public abstract Answer getNewAnswer();				//Cast as some answerType
	
	public abstract void getAnswerFromStudent();		
	
	public abstract double getValue();
	
	//NEW: Requires PrintWriter as Argument
	public abstract void save(PrintWriter savedWrite);
	
	//NEW: Requires PrintWriter
	public void saveStudentAnswers(PrintWriter savedWrite) {
		
		if (studentAnswer instanceof SAAnswer) {
			savedWrite.println("\nSAAnswer");
		} else if (studentAnswer instanceof MCSAAnswer) {
			savedWrite.println("\nMCSAAnswer");
		} else if (studentAnswer instanceof MCMAAnswer) {
			//savedWrite.println("\nMCMAAnswer");				//Bug Doesn't work? MCMA handles itself anyways
		} else if (studentAnswer instanceof NumAnswer) {
			savedWrite.println("\nNumAnswer");
		} 
		
		//Prints Value of Question then Question Prompt
		//savedWrite.println("hi");	//e.g) 5.0
	    //savedWrite.println(msgPrompt + "\n");	//e.g) Do you think the car should work?
		  
		//Prints studentAnswer
		//if ((studentAnswer instanceof SAAnswer) == false) {
			studentAnswer.save(savedWrite);	
		//} 
		
	}
	
	//NEW: Requires PrintWriter
	public void restoreStudentAnswers(Scanner sc) {
		
		Scanner tempScan = sc;
		
		while(tempScan.hasNextLine()) {
		
			String parseLine = tempScan.nextLine();
			//System.out.println("QuestionParseLine: " + parseLine);

			///* 
			if (parseLine.equals("SAAnswer")) {
				String userStringM = tempScan.nextLine();	//Grabs the String 
				//SAAnswer ansN = new SAAnswer(tempScan);
			
				SAAnswer ansN = new SAAnswer(userStringM);
				
				//Debug SAAnswer StudentAnswer
				System.out.println("\nSAAnswer: " + userStringM);
				
				//Sets Answer
				studentAnswer = ansN;
				
			} else if (parseLine.equals("MCSAAnswer")) {
				String userStringM = tempScan.nextLine();	//Grabs the string
				MCSAAnswer ansN = new MCSAAnswer(userStringM, 1.0);
				
				//Sets Answer
				studentAnswer = ansN;
		
				//Debug MCSAAnswer StudentAnswer
				System.out.println("\nMCSAAnswer: " + userStringM);

			} else if (parseLine.equals("NumAnswer")) {
			
				try {
					
					Double numQIn = tempScan.nextDouble();
					//System.out.println("numA in Question.java: " + numQIn);
					//double ansV = tempScan.nextDouble();
				
					double ansV = 0.0;
					
					//NumAnswer ansN = new NumAnswer(tempScan);
					NumAnswer ansN = new NumAnswer(numQIn);
					
					studentAnswer = ansN;
				
					//Debug SAAnswer StudentAnswer
					System.out.println("\nNumAnswer: " + ansV);
				
				} catch (Exception e) {
				
					System.out.println("ERROR in Question_RestoreAns: " + e);
				}
				
			} else if (parseLine.equals("MCMAAnswer")) {
				
				
				try {
					double rAnsCount = tempScan.nextDouble();
					tempScan.nextLine();
					
					//String userStringM = tempScan.nextLine();
			
					//Debug MCMAQuestion
					//System.out.println("MCMAQ rAns: " + rAnsCount);
					
					System.out.println("\nMCMAAnswer: ");
					for (int i = 0; i < rAnsCount; i++) {
						String userStringM = tempScan.nextLine();
						
						MCMAAnswer ansN = new MCMAAnswer(userStringM, 0.2);
						studentAnswer = ansN;
						System.out.println(userStringM);
					}

					
				} catch (Exception e) {
					System.out.println("ERROR in Question_RestoreAns: " + e);
				}
				
				
				/*
				//Do Nothing here Have it handled in MCMAQuestion
				 
				 
				try {
				
					double rAnsCount = tempScan.nextDouble();
					tempScan.nextLine();
					String userStringM = tempScan.nextLine();
									
					for (int i = 0; i < rAnsCount; i++) {
						MCMAAnswer tempAns = new MCMAAnswer(userStringM, 1.0);
						//ansArray.add(tempAns);
					}
				
					//Debug MCMAQuestion
					System.out.println("MCMAQ rAns: " + rAnsCount);
					System.out.println(userStringM);
					
				} catch (Exception e) {
					System.out.println("ERROR in Question_RestoreAns: " + e);
				}		
				*/
				
			}
			

		//*/
		} //End of While-Loop
		
	}
	
	
	
	
	
}
