
package HW_4;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class MCAnswer extends Answer {
	
	protected String multiText;
	protected boolean selected;
	protected double creditSelected;
	
	//Default Constructor 
	protected MCAnswer(String aMsg, double creditifSelected) {
		multiText = aMsg;
		creditSelected = creditifSelected;
	}
	
	//NEW CONSTRUCTOR
	public MCAnswer (Scanner sc) {
		super(sc);
	}
	
	
	@Override
	public void print() {
		System.out.println(multiText);
	}
	
	
	public void setSelected(boolean user_selected) {
		selected = user_selected;
	}
	
	
	// NEW METHOD (DONE)
	public double getCredit(Answer rightAnswer) {
		
		MCAnswer ansObj = (MCAnswer) rightAnswer;
		if (multiText.equals(ansObj.multiText)) {
			return 1.0;			
		} else {
			return 0.0;
		}
	}
	
	
	
	// NEW Method 
	@Override
	public void save(PrintWriter savedWrite) {
		
		//savedWrite.print(creditSelected + " ");
		savedWrite.println(multiText.trim());
		
	}
	
	
	

}
