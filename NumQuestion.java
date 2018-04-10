
package HW_4;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class NumQuestion extends Question {

	protected ArrayList<NumAnswer> numArray = new ArrayList<NumAnswer>(5);
	private String numQ;
	private double valu;

	private double userMsg;

	// Default Constructor
	public NumQuestion(String msgParam, double maxValue) {
		super(msgParam, maxValue);
		numQ = msgParam;
		valu = maxValue;
	}

	public NumQuestion(Scanner sc) {
		super(sc);
		
		double mVal = 0.0;
		//double ansF = 0.0;

		String userInput = sc.nextLine();
		if (isDouble(userInput)) {
			mVal = Double.parseDouble(userInput);
		}

		String userInputMsg = sc.nextLine();

		//NumQuestion newQuest = new NumQuestion(userInputMsg, mVal);
		numQ = userInputMsg;
		valu = mVal;
		
		//System.out.println("NumQ msg: " + numQ);
		//System.out.println("NumQ val: " + valu);

		/*
		String answerRight = sc.nextLine();

		if (isDouble(answerRight)) {
			ansF = Double.parseDouble(answerRight);
		}
		*/
		
		//NumAnswer a1 = new NumAnswer(ansF);
		NumAnswer a1 = new NumAnswer(sc);

		this.setRightAnswer(a1);
		
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
		NumAnswer mc = new NumAnswer(0.0);
		return mc;
	}

	public void getAnswerFromStudent() {

		// Ask Student for answer and then store it...
		// Scanner sc = new Scanner(System.in);
		Scanner sc = ScannerFactory.getKeyboardScanner();

		try {
			userMsg = sc.nextDouble(); // next or nextLine() for sentence
		} catch (Exception e) {
			System.out.println("ERROR: Input is not Double. " + e);
		}

		NumAnswer correctAnswer = (NumAnswer) rightAnswer;

		if (userMsg == correctAnswer.val) {
			studentAnswer = new NumAnswer(userMsg);
		} else {
			studentAnswer = new NumAnswer(0.0);
		}

	}

	public double getValue() {

		try {
			if (studentAnswer.getCredit(rightAnswer) == 1) {
				return valu;
			} else {
				return 0.0;
			}

		} catch (Exception e) {
			// System.out.println("NumQuestion Error: " + e);
			return maxValue;
		}
	

	}

	@Override
	public void print() {
		System.out.println(numQ + " (" + valu + ")");

	}

	@Override
	public void save(PrintWriter savedWrite) {
		savedWrite.println(valu);
		savedWrite.println(numQ);

		NumAnswer ans = (NumAnswer) rightAnswer;
		savedWrite.println(ans.val);

	}

}
