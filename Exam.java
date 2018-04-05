
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Exam {

	private ArrayList<Question> qArray = new ArrayList<Question>(20); //20 Questions is max for now
	private String examName;
	private double examPoints = 0.0;
	
	private static String studentName;
	
	
	public Exam(String msg) {
		examName = msg;
		System.out.println("Exam Name: " + examName);
	}
	
	//NEW Constructor
	public Exam(Scanner sc) {
		
		/*
		File fileName = new File("examfile.txt");
		sc = null;
		
		//Read In the File and takes in the Exam Name
		try {
			sc = new Scanner(fileName);
			String exName = sc.nextLine();
			examName = exName;
			
		}  catch (FileNotFoundException e) {
			System.out.println("ERROR: " + e);
		}*/
		
		//sc = ScannerFactory.getKeyboardScanner();
		
		String exName = sc.nextLine();
		examName = exName;
		
		//This Scans the ExamFile and looks ONLY for Questions and takes in the Prompt and Values
			while (sc.hasNextLine()) {

				String v = sc.nextLine();
				
				//System.out.println("THIS IS V in Exam_Const: " + v);
				
			if (v.equals("MCSAQuestion")) {
				try {
					
					//double maxPoints = sc.nextDouble();
					//sc.nextLine();
					//String qMsgPrompt = sc.nextLine();
					
					/*
					//DEBUGGING TIME
					System.out.println("MaxPointsMCSA: " + maxPoints);
					System.out.println("qMsgPRomptMCSA: " + qMsgPrompt);
					*/
					
					//MCSAQuestion newQuest = new MCSAQuestion(qMsgPrompt, maxPoints);
					
					MCSAQuestion newQuest = new MCSAQuestion(sc);
					qArray.add(newQuest);
	
				} catch (Exception e) {
					
					System.out.println("Error: Input is not a Number");
				}
				
			} else if (v.equals("MCMAQuestion")) {
				try {
					//double maxPoints = sc.nextDouble();
					//sc.nextLine();
					//String qMsgPrompt = sc.nextLine();
					
					/*
					//DEBUGGING TIME
					System.out.println("MaxPointsMCMA: " + maxPoints);
					System.out.println("qMsgPRomptMCMA: " + qMsgPrompt);
					*/
					
					//double baseCreditP = sc.nextDouble();
					//MCMAQuestion newQuest = new MCMAQuestion(qMsgPrompt, maxPoints, baseCreditP);
					MCMAQuestion newQuest = new MCMAQuestion(sc);
					
					qArray.add(newQuest);

				} catch (Exception e) {
					
					System.out.println("Error: Input is not a Number");
				}
				
				
			} else if (v.equals("NumQuestion")) {
				try {
					//double maxPoints = sc.nextDouble();
					//sc.nextLine();
					//String qMsgPrompt = sc.nextLine();
					
					/*
					//DEBUGGING TIME
					System.out.println("MaxPointsNUMQ: " + maxPoints);
					System.out.println("qMsgPRomptNUMQ: " + qMsgPrompt);
					*/
					
					//NumQuestion newQuest = new NumQuestion(qMsgPrompt, maxPoints);
					
					NumQuestion newQuest = new NumQuestion(sc);
					
					qArray.add(newQuest);
					
				} catch (Exception e) {
					
					System.out.println("Error: Input is not a Number");
				}
				
			} else if (v.equals("SAQuestion")) {
				try {
					//double maxPoints = sc.nextDouble();
					//sc.nextLine();
					//String qMsgPrompt = sc.nextLine();
					
					/*
					//DEBUGGING TIME
					System.out.println("MaxPointsSAQ: " + maxPoints);
					System.out.println("qMsgPRomptSAQ: " + qMsgPrompt);
					*/
					
					//SAQuestion newQuest = new SAQuestion(qMsgPrompt, maxPoints);
					
					SAQuestion newQuest = new SAQuestion(sc);
					
					qArray.add(newQuest);

				} catch (Exception e) {
					
					System.out.println("Error: Input is not a Number");
				}
			}
			
		}
			

	}
	
	
	//Prints all the Questions & answers out
	public void print() {
		for(int i = 0; i < qArray.size(); i++) {
			System.out.print("\n" + (i + 1) + ". ");		//Prints out the number for the question (0 based)
			qArray.get(i).print();
		}
	}
	
	//Adds a Question obj. to a Question obj Array.
	public void addQuestion(Question x) {
		
		//Error Check (Could also convert to try-catch exception)
		if (qArray.size() < 10) {
			qArray.add(x);
		} else {
			System.out.println("Error: Cannot test_user cannot add anymore questions beyond '10'.");
		}
		
	}
		
	//Randomizes the Questions Ordering
	public void reorderQuestion() {
		Random rand = new Random();
		
		for (int i = 0; i < qArray.size(); i++) {
			int n = rand.nextInt(qArray.size());	
			Question tempQues = qArray.get(i);
			qArray.set(i, qArray.get(n));
			qArray.set(n, tempQues);
			
		}
		
	}
	
	//Reorders MCAnswers
	public void reorderMCAnswer(int position) {
		
		if (position >= 0) {
			if (qArray.get(position) != null && qArray.get(position) instanceof MCQuestion) {
				MCQuestion ans = (MCQuestion) qArray.get(position);
		
				ans.reorderAnswers();
			}  else {
				System.out.println("Error: Attempting to reorder MCAnswer for Question " + (position + 1) + " since it is not a MCQuestion");
			}
			
		} else {
			for (int i = 0; i < qArray.size(); i++) {
				if (qArray.get(i) instanceof MCQuestion) {
					MCQuestion ansLoop = (MCQuestion) qArray.get(i);
				
					ansLoop.reorderAnswers();
				}
			}	
		}
	}
	
	
	
	//Gets the Answer from Student and loops
	public void getAnswerFromStudent(int position) {
		
		if (position == 0) {
			System.out.println("\nPlease Enter your Name: ");
			Scanner scName = ScannerFactory.getKeyboardScanner();
			studentName = scName.nextLine();
		}
		
		if (position >= 0) {
			System.out.print("\n" + (position + 1) + ". ");
			qArray.get(position).print();
			System.out.println("\nSelect an Answer: ");
			qArray.get(position).getAnswerFromStudent();
			
			System.out.println("Value: " + qArray.get(position).getValue());
		
		} else {
			
			System.out.println("Error: Test User attempted to put an invalid position.");
		}
	}
	

	//Retrieve values from all Questions   
	public double getValue() {
		double k = 0;
		for (int i = 0; i < qArray.size(); i++) {
			k += this.qArray.get(i).getValue();
		}
		examPoints = k;
		return examPoints;
	}
	
	public void reportQuestionValues() {
		System.out.println("\nQuestions Worth Table: ");
		System.out.println("    User Input   Total Points");
		for (int i = 0; i < qArray.size(); i++) {
			System.out.println("Q" + (i+1) + "     " + qArray.get(i).getValue() + "          " + qArray.get(i).maxValue);
		}
	}
	
	//NEW Method: This saves questions stuff to Exam
	public void save(PrintWriter savedWrite) {
		
		//Prints the Exam Name to the Test
		savedWrite.println(examName);
		
		for(int i = 0; i < qArray.size(); i++) {
			
			//Deciphers Question Handling
			if (qArray.get(i) instanceof MCSAQuestion) {
				savedWrite.println("\nMCSAQuestion");
			} else if (qArray.get(i) instanceof MCMAQuestion) {
				savedWrite.println("\nMCMAQuestion");
			} else if (qArray.get(i) instanceof NumQuestion) {
				savedWrite.println("\nNumQuestion");
			} else if (qArray.get(i) instanceof SAQuestion) {
				savedWrite.println("\nSAQuestion");
			}
			
			//Calls save() in all Questions
			qArray.get(i).save(savedWrite);
		}
		
	}
	
	//NEW Method: This Saves the Answer to the file
	public void saveStudentAnswers(PrintWriter savedWrite) {
		savedWrite.println(studentName);
		
		for(int i = 0; i < qArray.size(); i++) {
			//savedWrite.print("\n" + (i + 1) + ". ");		//Prints out the number for the question (0 based)
			qArray.get(i).saveStudentAnswer(savedWrite);
		}	
		
	}
	
	//NEW Method: Loads the StudentAnswers
	public void restoreStudentAnswers(Scanner sc) {
		
		String studentN = sc.nextLine();
		
		//sc.nextLine();
		
		studentName = studentN;
		
		System.out.println("StudentName: " + studentName);
		
		
		//Debug Size of qArray
		//System.out.println("qArray Size: " + qArray.size());
		
		for (int i = 0; i < qArray.size(); i++) {
			qArray.get(i).restoreStudentAnswers(sc);
		}
		
	}
	
	
	
}
