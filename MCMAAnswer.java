
package HW_4;

//Done

import java.util.ArrayList;
import java.util.Scanner;

public class MCMAAnswer extends MCAnswer{
	
	//private String ansMsg;
	
	//Default Constructor 
	public MCMAAnswer(String aMsg, double creditifSelected) {
		super(aMsg, creditifSelected);
		
		//ansMsg = aMsg; //Copy the String
	}
	
	public MCMAAnswer(Scanner sc) {
		super(sc);
		
		//Already does this by passing it into MCAnswer
		
		//double ptsA = sc.nextDouble();
		//String qMsgPrompt = sc.nextLine();
	}
	
	/*
	public double getCredit(Answer rightAnswer) {
		
		MCMAAnswer ansObj = (MCMAAnswer) rightAnswer;
		
		if (ansMsg.equals(ansObj.ansMsg)) {
			return 1.0;
		} else {
			return 0.0; //placeholder
		}
		
	}
	
	//Method Overload
	public static double getCredit(ArrayList<Answer> rightAnswer, ArrayList<Answer> studentAnswer) {
		
		//.contentEquals instead of .equals
		if (rightAnswer.equals(studentAnswer)) {
			return 5.0;
		} else {
			return 0.0;		//placeholder
		}
		
	}
	
	*/
	

}
