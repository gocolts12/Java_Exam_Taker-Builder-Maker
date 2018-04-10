
package HW_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Java 8 Imports
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
import java.util.*;
import java.text.*;

public class Exam {

	private ArrayList<Question> qArray = new ArrayList<Question>(20); // 20 Questions is max for now
	private ArrayList<Question> qArraySkip = new ArrayList<Question>(20);
	private String examName;
	private String timeStamp;
	private String versionNumber;
	private double examPoints = 0.0;

	private boolean debugMode = false;

	private static String studentName;

	public Exam(String msg) {
		examName = msg;
		// System.out.println("Exam Name: " + examName);
	}

	// NEW Constructor
	public Exam(Scanner sc) {

		if (sc.hasNextLine()) {

			String exName = sc.nextLine();
			examName = exName;

			String tiStamp = sc.nextLine();
			timeStamp = tiStamp;

			String viStamp = sc.nextLine();
			versionNumber = viStamp;

			// Debug Mode
			if (debugMode == true) {
				System.out.println("Exam.class TimeStamp: " + timeStamp);
				System.out.println("Exam.class ExamName: " + examName);
				System.out.println("Exam class versionNumber: " + versionNumber);
			}

			// This Scans the ExamFile and looks ONLY for Questions and takes in the Prompt
			// and Values
			while (sc.hasNextLine()) {

				String v = sc.nextLine();
				// System.out.println("Exam Scanner String: " + v);

				if (v.equals("MCSAQuestion")) {
					try {

						// Do Next Scanner
						// String userInput = sc.nextLine();
						// System.out.println("MCSAQ: " + userInput);
						double mVal = 0.0;
						double ansLimit = 0.0;

						String userInput = sc.nextLine();
						if (isDouble(userInput)) {
							mVal = Double.parseDouble(userInput);
						}

						String userInputMsg = sc.nextLine();

						MCSAQuestion newQuest = new MCSAQuestion(userInputMsg, mVal);

						String ansLimitM = sc.nextLine();

						if (isDouble(ansLimitM)) {
							ansLimit = Double.parseDouble(ansLimitM);
						}

						for (int i = 0; i < ansLimit; i++) {

							double ptsA = sc.nextDouble();
							String qMsgPrompt = sc.nextLine();

							MCSAAnswer a1 = new MCSAAnswer(qMsgPrompt, ptsA);

							newQuest.addAnswer(a1);
						}

						// MCSAQuestion newQuest = new MCSAQuestion(sc);

						qArray.add(newQuest);
					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("MCMAQuestion")) {
					try {

						double mVal = 0.0;
						double ansLimit = 0.0;
						double baseVal = 0.0;

						String userInput = sc.nextLine();
						if (isDouble(userInput)) {
							mVal = Double.parseDouble(userInput);
						}

						String userInputMsg = sc.nextLine();

						// System.out.println("MCMAQ: " + userInputMsg);

						String userInputBase = sc.nextLine();
						if (isDouble(userInputBase)) {
							baseVal = Double.parseDouble(userInputBase);
						}

						MCMAQuestion newQuest = new MCMAQuestion(userInputMsg, mVal, baseVal);

						String ansLimitM = sc.nextLine();

						if (isDouble(ansLimitM)) {
							ansLimit = Double.parseDouble(ansLimitM);
						}

						for (int i = 0; i < ansLimit; i++) {

							double ptsA = sc.nextDouble();
							String qMsgPrompt = sc.nextLine();

							MCMAAnswer a1 = new MCMAAnswer(qMsgPrompt, ptsA);

							newQuest.addAnswer(a1);
						}

						qArray.add(newQuest);

					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("NumQuestion")) {
					try {

						// String userInput = sc.nextLine();
						// System.out.println("MCSAQ: " + userInput);
						double mVal = 0.0;
						double ansF = 0.0;

						String userInput = sc.nextLine();
						if (isDouble(userInput)) {
							mVal = Double.parseDouble(userInput);
						}

						String userInputMsg = sc.nextLine();

						NumQuestion newQuest = new NumQuestion(userInputMsg, mVal);

						String answerRight = sc.nextLine();

						if (isDouble(answerRight)) {
							ansF = Double.parseDouble(answerRight);
						}
						NumAnswer a1 = new NumAnswer(ansF);

						newQuest.setRightAnswer(a1);

						// NumQuestion newQuest = new NumQuestion(sc);
						qArray.add(newQuest);

					} catch (Exception e) {
						System.out.println("Error: Input is not a Number");
					}

				} else if (v.equals("SAQuestion")) {
					try {

						// String userInput = sc.nextLine();
						// System.out.println("MCSAQ: " + userInput);
						double mVal = 0.0;

						String userInput = sc.nextLine();
						if (isDouble(userInput)) {
							mVal = Double.parseDouble(userInput);
						}

						String userInputMsg = sc.nextLine();

						SAQuestion newQuest = new SAQuestion(userInputMsg, mVal);

						String answerRight = sc.nextLine();
						SAAnswer a1 = new SAAnswer(answerRight);
						newQuest.setRightAnswer(a1);

						// SAQuestion newQuest = new SAQuestion(sc);

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

	// Helper Function isDouble : boolean
	private boolean isDouble(String msg) {
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// Prints all the Questions & answers out
	public void print() {
		
		//System.out.println("qArraySize: " + qArray.size());
		
		for (int i = 0; i < qArray.size(); i++) {
			System.out.print("\n" + (i + 1) + ". "); // Prints out the number for the question (0 based)
			qArray.get(i).print();
		}
	}

	// Adds a Question obj. to a Question obj Array.
	public void addQuestion(Question x) {

		// Error Check (Could also convert to try-catch exception)
		if (qArray.size() < 20) {
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
			System.out.println("Would you like to skip this question?");
			System.out.println("Enter 1 to skip and 0 to answer");
			Scanner scanskip = ScannerFactory.getKeyboardScanner();
			int skipresponse = scanskip.nextInt();
			if (skipresponse == 1) {
				qArraySkip.add(qArray.get(position));
				System.out.println("Question has been skipped");
			} else if (skipresponse == 0) {
				System.out.println("\nSelect an Answer: ");
				qArray.get(position).getAnswerFromStudent();

				// System.out.println("Value: " + qArray.get(position).getValue());
			} else {
				System.out.println("ERROR: That is not a choice question has been skipped");
				qArraySkip.add(qArray.get(position));
			}
		} else {

			System.out.println("Error: Test User attempted to put an invalid position.");
		}
		if ((qArraySkip.size() > 0) && (position == qArray.size() - 1)) {
			System.out.println("");
			System.out.println("NOTICE: These are the questions you have skipped");
			System.out.println("You must answer, no more skipping");
			for (int i = 0; qArraySkip.size() > i; i++) {
				qArraySkip.get(i).print();
				System.out.println("\nSelect an Answer: ");
				qArraySkip.get(i).getAnswerFromStudent();
				// System.out.println("Value: " + qArraySkip.get(i).getValue());
			}
		}
		if (position == qArray.size() - 1) {
			System.out.println("Would you like to go back and change your answer?");
			System.out.println("Enter 1 for Yes and 0 for No");
			Scanner scanchange = ScannerFactory.getKeyboardScanner();
			int changeanswer = scanchange.nextInt();
			if (changeanswer == 0) {
				// System.out.println("Grading exam please wait...");
			} else if (changeanswer == 1) {
				for (int j = 0; qArray.size() > j; j++) {
					qArray.get(j).print();
					// System.out.println("You answered "); //doesn't work because of MCMA
					// qArray.get(j).studentAnswer.print(); //doesn't work because of MCMA
					System.out.println("Would you like to change your answer?");
					System.out.println("Enter 1 for Yes and 0 for No");
					Scanner scanchange2 = ScannerFactory.getKeyboardScanner();
					int changeanswer2 = scanchange2.nextInt();
					if (changeanswer2 == 0) {
						// continue;
					} else if (changeanswer2 == 1) {
						System.out.println("\nSelect an Answer: ");
						qArray.get(j).getAnswerFromStudent();
					} else {
						System.out.println("ERROR: That is not an option, moving to next question");
					}
				}
			} else {
				// System.out.println("ERROR: That is not an option, grading exam please
				// wait...");
			}
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

	// This saves questions stuff to Exam
	public void save(PrintWriter savedWrite) {

		// Prints the Exam Name to the Test
		savedWrite.println(examName);

		// Exam TimeStamp JAVA 8
		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		// LocalDateTime now = LocalDateTime.now();
		// savedWrite.println(dtf.format(now));

		// Exam TimeStamp Java 7
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		savedWrite.println(sdf.format(currentDate));

		/*
		 * Random rand = new Random(); int n = rand.nextInt(9); String randomNumber =
		 * "";
		 * 
		 * for (int i = 0; i < n; i++) { randomNumber += randomNumber +
		 * Integer.toString(n); n = rand.nextInt(9); }
		 */

		// System.out.println("Verison Number: " + randomNumber);

		if (debugMode == true) {
			System.out.println("Version Number: " + versionNumber);
		}
		// Version Control
		savedWrite.println(versionNumber);
		// versionNumber = randomNumber;

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

	// This Saves the Answer to the file
	public void saveStudentAnswers(PrintWriter savedWrite) {
		savedWrite.println(studentName);
		savedWrite.println(examName);

		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		// LocalDateTime now = LocalDateTime.now();
		// savedWrite.println(dtf.format(now));

		// Exam TimeStamp Jave 7
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/DD HH:mm:ss");
		savedWrite.println(sdf.format(currentDate));

		savedWrite.println(versionNumber);

		for (int i = 0; i < qArray.size(); i++) {
			// savedWrite.print("\n" + (i + 1) + ". "); //Prints out the number for the
			// question (0 based)
			qArray.get(i).saveStudentAnswers(savedWrite);
		}

	}

	// Loads the StudentAnswers
	public void restoreStudentAnswers(Scanner sc) {

		String studentN = sc.nextLine();
		studentName = studentN;

		String ExamN = sc.nextLine();
		examName = ExamN;

		String dateN = sc.nextLine();
		timeStamp = dateN;

		String versionN = sc.nextLine();
		versionNumber = versionN;

		System.out.println("StudentName: " + studentName);
		System.out.println("Exam Name: " + examName);
		System.out.println("TimeStamp: " + timeStamp);
		System.out.println("VersionNumber: " + versionNumber);

		// Debug Size of qArray
		// System.out.println("qArray Size: " + qArray.size());

		for (int i = 0; i < qArray.size(); i++) {
			qArray.get(i).restoreStudentAnswers(sc);
		}
	}

	// Remove Question from Exam
	public int removeQuest(int pos) {
		if (debugMode == true) {
			System.out.println("    removeQuest Array Size: " + qArray.size());
		}
		pos -= 1;
		if (pos >= 0 && pos < qArray.size()) {
			qArray.remove(pos);
			return 1;
		} else {
			return 0;
		}
	}

	// Version setter
	public void setVersionNum(String randomNum) {
		versionNumber = randomNum;
	}

	// Version Getter
	public String getVersionNum() {
		return versionNumber;
	}

}
