
package HW_4_ExamBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Exam {

	private ArrayList<Question> qArray = new ArrayList<Question>(20); // 20 Questions is max for now
	private String examName;
	private String timeStamp;
	private double examPoints = 0.0;

	private static String studentName;

	public Exam(String msg) {
		examName = msg;
		System.out.println("Exam Name: " + examName);
	}

	// NEW Constructor
	public Exam(Scanner sc) {

		if (sc.hasNextLine()) {

			String exName = sc.nextLine();
			examName = exName;			
			
			String tiStamp = sc.nextLine();
			timeStamp = tiStamp;
			
			//Debug Mode
			//System.out.println("Exam.class TimeStamp: " + timeStamp);
			//System.out.println("Exam.class ExamName: " + examName);
			
			// This Scans the ExamFile and looks ONLY for Questions and takes in the Prompt
			// and Values
			while (sc.hasNextLine()) {

				String v = sc.nextLine();

				if (v.equals("MCSAQuestion")) {
					try {
						MCSAQuestion newQuest = new MCSAQuestion(sc);
						qArray.add(newQuest);
					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("MCMAQuestion")) {
					try {
						MCMAQuestion newQuest = new MCMAQuestion(sc);
						qArray.add(newQuest);

					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("NumQuestion")) {
					try {
						NumQuestion newQuest = new NumQuestion(sc);
						qArray.add(newQuest);
					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("SAQuestion")) {
					try {
						SAQuestion newQuest = new SAQuestion(sc);
						qArray.add(newQuest);
					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}
				}
			}

		} else {
			
			
			System.out.println("Error: The File is Empty...");
		}

	}

	// Prints all the Questions & answers out
	public void print() {
		for (int i = 0; i < qArray.size(); i++) {
			System.out.print("\n" + (i + 1) + ". "); // Prints out the number for the question (0 based)
			qArray.get(i).print();
		}
	}

	// Adds a Question obj. to a Question obj Array.
	public void addQuestion(Question x) {

		// Error Check (Could also convert to try-catch exception)
		if (qArray.size() < 10) {
			qArray.add(x);
		} else {
			System.out.println("Error: Cannot test_user cannot add anymore questions beyond '10'.");
		}

	}

	// Randomizes the Questions Ordering
	public void reorderQuestion() {
		Random rand = new Random();

		for (int i = 0; i < qArray.size(); i++) {
			int n = rand.nextInt(qArray.size());
			Question tempQues = qArray.get(i);
			qArray.set(i, qArray.get(n));
			qArray.set(n, tempQues);

		}

	}

	// Reorders MCAnswers
	public void reorderMCAnswer(int position) {

		if (position >= 0) {
			if (qArray.get(position) != null && qArray.get(position) instanceof MCQuestion) {
				MCQuestion ans = (MCQuestion) qArray.get(position);

				ans.reorderAnswers();
			} else {
				System.out.println("Error: Attempting to reorder MCAnswer for Question " + (position + 1)
						+ " since it is not a MCQuestion");
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

	// Gets the Answer from Student and loops
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

	// Retrieve values from all Questions
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
			System.out.println(
					"Q" + (i + 1) + "     " + qArray.get(i).getValue() + "          " + qArray.get(i).maxValue);
		}
	}

	//This saves questions stuff to Exam
	public void save(PrintWriter savedWrite) {

		// Prints the Exam Name to the Test
		savedWrite.println(examName);

		// Exam TimeStamp
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		savedWrite.println(dtf.format(now));

		for (int i = 0; i < qArray.size(); i++) {

			// Deciphers Question Handling
			if (qArray.get(i) instanceof MCSAQuestion) {
				savedWrite.println("\nMCSAQuestion");
			} else if (qArray.get(i) instanceof MCMAQuestion) {
				savedWrite.println("\nMCMAQuestion");
			} else if (qArray.get(i) instanceof NumQuestion) {
				savedWrite.println("\nNumQuestion");
			} else if (qArray.get(i) instanceof SAQuestion) {
				savedWrite.println("\nSAQuestion");
			}

			// Calls save() in all Questions
			qArray.get(i).save(savedWrite);
		}

	}

	//This Saves the Answer to the file
	public void saveStudentAnswers(PrintWriter savedWrite) {
		savedWrite.println(studentName);
		savedWrite.println(examName);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		savedWrite.println(dtf.format(now));

		for (int i = 0; i < qArray.size(); i++) {
			// savedWrite.print("\n" + (i + 1) + ". "); //Prints out the number for the
			// question (0 based)
			qArray.get(i).saveStudentAnswers(savedWrite);
		}

	}

	//Loads the StudentAnswers
	public void restoreStudentAnswers(Scanner sc) {

		String studentN = sc.nextLine();

		studentName = studentN;

		String ExamN = sc.nextLine();

		examName = ExamN;

		System.out.println("StudentName: " + studentName);
		System.out.println("Exam Name: " + examName);

		// Debug Size of qArray
		// System.out.println("qArray Size: " + qArray.size());

		for (int i = 0; i < qArray.size(); i++) {
			qArray.get(i).restoreStudentAnswers(sc);
		}
	}
	
	//Remove Question from Exam
	public int removeQuest(int pos) {
		if (pos <= qArray.size()) {
			qArray.remove(pos);
			return 1;
		}  else {
			return 0;
		}
	}

}
