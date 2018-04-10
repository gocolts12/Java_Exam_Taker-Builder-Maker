package HW_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExamGrader {
	
	
	private volatile static boolean debugMode = false;
	private static boolean check;
	private static Exam exam1;
	private static String studentName, versionNumber, timeStamp, examName;

	public ExamGrader() {

	}

	public static void main(String[] args) {

		System.out.println("Welcome to Exam Grader.");
		System.out.println("Please enter the name of your answer file.");

		// scan in the file name for the answer, extract the version number, and search
		// for
		// the corresponding exam file by appending the version number to the the exam
		// file name
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String userInput = sc.nextLine();

		//String directoryM = "C:\\Users\\Michael\\Google Drive\\School\\Junior\\Spring\\342\\342proj4";
		
		//String directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342 HW_4\\src\\HW_4";
		String directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4";

		check = new File(directoryM, userInput + ".txt").exists();

		if (check == true) {
			System.out.println("Answer file successfully found");

			String versionNum = userInput.substring(7);

			String examFile = "examFile" + versionNum + ".txt";

			boolean check2;

			check2 = new File(directoryM, examFile).exists();

			if (check2 == true) {
				// Both the exam and answer files were found, so both need to be loaded
				System.out.println("    Exam File has been successfully found.");
				File fileNameExam = new File(directoryM, examFile);
				File fileNameAnswer = new File(directoryM, userInput + ".txt");
				
				// Load exam
				try {
					Scanner fd = new Scanner(fileNameExam);
					exam1 = new Exam(fd);

				} catch (FileNotFoundException e) {
					System.out.println("Failure to scan exam");
					e.printStackTrace();
				}

				// Call restoreStudentAnswers to attach the answers to each exam question
				Scanner an;

				try {
					an = new Scanner(fileNameAnswer);
					exam1.restoreStudentAnswers(an);
					exam1.print();
					exam1.reportQuestionValues();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Check failed");
			}
		}
		System.out.println("\nEnd of Program.\n");
	}

	// Load Exam Method
	private static void loadExam() {

		if (check == false) {
			System.out.print("    Enter the Exam File Name: ");
			Scanner df = ScannerFactory.getKeyboardScanner();

			String userInput = df.nextLine(); // Holds examFileName

			// directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342
			// HW_4\\src\\HW_4"; // CHANGE
			String directoryM = "C:\\Users\\Michael\\Google Drive\\School\\Junior\\Spring\\342\\342proj4"; // Directory
			// HERE...
			userInput = userInput + ".txt";

			// boolean check = new File(directoryM, userInput).exists();

			check = new File(directoryM, userInput).exists();

			if (check == true) {
				System.out.println("    File has been successfully Loaded.");
				File fileNameExam = new File(directoryM, userInput);
				try {
					Scanner fd = new Scanner(fileNameExam);
					exam1 = new Exam(fd);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("    Error: No File Exist with that name");
			}

			if (debugMode == true) {
				System.out.println("    User Inputted: " + userInput);
				System.out.println("    Check is: " + check);
				String current = System.getProperty("user.dir");
				System.out.println("    Current Working Dir in Java: " + current);
			}
		} else {

			System.out.println("    Error: An Exam has been already created or loaded.");
		}
	}

}
