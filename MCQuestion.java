

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class MCQuestion extends Question {
	
	protected ArrayList<MCAnswer> ansArray = new ArrayList<MCAnswer>(5);
	private double points;
	
	protected MCQuestion(String msgParam, double maxValue) {
		super(msgParam, maxValue);
		points = maxValue;
	}
	
	protected MCQuestion (Scanner sc) {
		super(sc);
	}
	
	
	@Override	//Override the method and prints all the questions
	public void print() {
		
		System.out.println(msgPrompt + " (" + points + ") ");	//Print out the questions points
		
		for (int i = 0; i < ansArray.size(); i++) {
			int letter = 'A' + i;	//Formula: (A + 0)..(A + i) 
			
			System.out.print("   " + Character.toString((char) letter) + ") ");	
			ansArray.get(i).print();
		}
	}

	//Adds an answer into the ansArray of MCAnswer
	public void addAnswer(MCAnswer a) {
		if (ansArray.size() < 5) {
			this.ansArray.add(a);		
		} else {
			System.out.println("Error: Cannot test_user cannot add anymore answers beyond '5'.");
		}
	}
	
	//Reorders the answers 
	public void reorderAnswers() {
		Random rand = new Random();
		
		for (int i = 0; i < ansArray.size(); i++) {
			int n = rand.nextInt(ansArray.size());
			MCAnswer tempAns = ansArray.get(i);
			ansArray.set(i, ansArray.get(n));
			ansArray.set(n, tempAns);
		}
	}
	
	
	//NEW METHOD 
	public double getValue (MCAnswer ans) {

			try {
				
				if (ans.getCredit(rightAnswer) == 1) {
					return studentAnswer.getCredit(ans) * points; // * points;
				} else {
					return 0.0;
				}
			} catch (Exception e) {
				//System.out.println("MCQ Error: " + e);
				return maxValue;
			}
		
		
		/*
		double sumQ = 0.0;
		
		for (int i = 0; i < ansArray.size(); i++) {
			sumQ += ansArray.get(i).getCredit(ans);
		}
		
		return sumQ;
		*/
		
			
	}
	
	
	//NEW METHOD 
	public void save(PrintWriter savedWrite) {
		//savedWrite.println(msgPrompt + " (" + points + ") ");	//Print out the questions points
		

		for (int i = 0; i < ansArray.size(); i++) {
			//int letter = 'A' + i;	//Formula: (A + 0)..(A + i) 
			//System.out.print("   " + Character.toString((char) letter) + ") ");	
			
			//savedWrite.print(ansArray.get(i).creditSelected + " ");
			
			savedWrite.print(ansArray.get(i).creditSelected + " ");
			ansArray.get(i).save(savedWrite);
		}
	}
	

}
