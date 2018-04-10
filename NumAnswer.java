
package HW_4;

import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer {
	
	protected double val;
	
	//Default Constructor 
	public NumAnswer(double num) {
		val = num;
		//System.out.println("NumAnswer: " + val);
	}
	
	public NumAnswer(Scanner sc) {
		super(sc);
		
		double ansF = 0.0;
		String answerInput = sc.nextLine();

		if (isDouble(answerInput)) {
			ansF = Double.parseDouble(answerInput);
		}
		
		val = ansF;
		//System.out.println("NumA val: " + val);
	}
	
	private boolean isDouble(String msg) {
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	@Override
	public void print() {
		System.out.println(val); 	//Prints the value of the Answer
	}
	
	@Override
	public double getCredit(Answer rightAnswer) {
		
		NumAnswer ansObj = (NumAnswer) rightAnswer;
		
		if (val == ansObj.val) {
			return 1.0;
		} else {
			return 0.0;
		}

	}
	
	
	@Override
	public void save (PrintWriter savedWrite) {
		savedWrite.println(val);
	}
	

}
