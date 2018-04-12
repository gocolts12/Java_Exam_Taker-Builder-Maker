
import java.util.ArrayList;
import java.util.Scanner;

import java.io.PrintWriter;

public abstract class Answer {


	protected Answer() {
		
	}
	
	public Answer(Scanner sc) {
		
		//Unused... No purpose to use Scanner to hold a Variable since there are none here
		//sc = ScannerFactory.getKeyboardScanner();	
		
	}
	
	public abstract void print();
	
	public abstract double getCredit(Answer rightAnswer);
	
	
	//New Abstract that takes in PrintWriter 
	public abstract void save(PrintWriter savedWrite);
	
	
	
}
