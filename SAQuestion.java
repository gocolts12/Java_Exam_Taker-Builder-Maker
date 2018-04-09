
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
