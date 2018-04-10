
package HW_4;

import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer {
	
	protected double val;
	
	//Default Constructor 
	public NumAnswer(double num) {
		val = num;
		//System.out.println("NumAnswer.java: " + val);
	}
	
	public NumAnswer(Scanner sc) {
		super(sc);
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
