
package HW_4_ExamBuilder;

import java.util.Scanner;

//Done

public class MCSAAnswer extends MCAnswer {

	//private String ansMsg;
	//private double credit;
	
	//Default Constructor 	
	public MCSAAnswer (String aMsg, double creditifSelected) {
		super(aMsg, creditifSelected);
		//ansMsg = aMsg;
	}
	
	public MCSAAnswer(Scanner sc) {
		super(sc);
	}
	
	/*
	//Checks to see if the answer is correct.   (REMOVE THIS!!!)
	public double getCredit(Answer rightAnswer) {
		MCSAAnswer ansObj= (MCSAAnswer) rightAnswer;
	
			if (ansMsg.equals( ansObj.ansMsg)) {
				return 1.0;
			} else {
				return 0.0;
			}

	}
	*/
	

}
