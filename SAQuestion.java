
package HW_4;

import java.io.PrintWriter;
import java.util.Scanner;

public class SAQuestion extends Question {

	private double points;
	private String userMsg;
	private String qPrompt;
	
	public SAQuestion(String qMsg, double maxValue) {
		super(qMsg, maxValue);
		points = maxValue;
		qPrompt = qMsg;
	}
	
	public SAQuestion(Scanner sc) {
		super(sc);
		
		
		// String userInput = sc.nextLine();
		// System.out.println("MCSAQ: " + userInput);
		double mVal = 0.0;

		String userInput = sc.nextLine();
		if (isDouble(userInput)) {
			mVal = Double.parseDouble(userInput);
		}

		String userInputMsg = sc.nextLine();

		//SAQuestion newQuest = new SAQuestion(userInputMsg, mVal);
		qPrompt = userInputMsg;
		points = mVal;
		
		//String answerRight = sc.nextLine();
		//SAAnswer a1 = new SAAnswer(answerRight);
		
		SAAnswer a1 = new SAAnswer(sc);
		
		this.setRightAnswer(a1);

		// SAQuestion newQuest = new SAQuestion(sc);
		 
		
	}
	
	private boolean isDouble(String msg) {
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public Answer getNewAnswer() {
		SAAnswer sa = new SAAnswer("");
		return sa; 
	}
	
	public Answer getNewAnswer(String msgParam) {
		SAAnswer sa = new SAAnswer(msgParam);
		return sa; 
	}
	
	public void getAnswerFromStudent() {
		
		//Ask Student for answer and then store it...
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = ScannerFactory.getKeyboardScanner();
		Scanner sc = new Scanner(System.in);
		
	
		userMsg = sc.nextLine(); //next or nextLine() for sentence
		
		SAAnswer correctAnswer = (SAAnswer) rightAnswer;
		
		if (userMsg.equalsIgnoreCase(correctAnswer.wordAns)) {
			studentAnswer = new SAAnswer(userMsg);
		} else {
			
			studentAnswer = new SAAnswer(userMsg);
		}
		 
	}
	
	@Override
	public double getValue() {
		
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
		

	}

	//NEW: Requires PrintWriter
	@Override
	public void save(PrintWriter savedWrite) {
		
		//Prints Value
		savedWrite.println(points);
		
		//Prints the Prompt
		savedWrite.println(qPrompt);
		
		SAAnswer ans = (SAAnswer) rightAnswer;
		
		savedWrite.println(ans.wordAns);
		
		/*
		if (userMsg != null) {
			savedWrite.println(userMsg);
		}
		*/
		
	}
	

}
