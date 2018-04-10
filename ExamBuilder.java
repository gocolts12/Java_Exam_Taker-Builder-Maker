package HW_4;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Author:  Brian De Villa
 * net-id:  bdevil2
 * Class:   CS342
 * Lecture: T TR @ 3:30PM - 4:45PM
 * Project #1: Exam Test
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class ExamBuilder {
	
	/*
	// Name
	private static String myName = "Brian De Villa";
	private static String myNetID = "bdevil2";

	// private static String directoryM =
	// "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW3/examfile.txt"; // CHANGE

	private static String directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/";

	private volatile static boolean debugMode = false; // Debug Mode

	private static boolean check;
	private static int ansLimit = 0;

	private static Exam exam1;

	// Command_Loop
	private static void commandLoop() {
		System.out.print("=>  ");
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String command = sc.next();

			if (command == null) {
				System.out.println("Blank Line\n");
			} else if (command.equals("q")) { // quit
				try {
					System.out.print("\n\n");
					for (int i = 0; i < 3; i++) {
						System.out.println("Exiting Program in " + (3 - i));
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\n\nEnd of Program. \n");
				System.exit(0);
			} else if (command.equals("?")) { // Help Command
				commandHelp();
			} else if (command.equals("c")) { // Create Exam File
				createExam();
			} else if (command.equals("l")) { // Load Exam File
				loadExam();
			} else if (command.equals("aq")) { // Add Question
				questionAdd();
			} else if (command.equals("rq")) { // Remove Question
				questionRemove();
			} else if (command.equals("rqs")) { // Reorder Questions
				reorderExamQuestions();
			} else if (command.equals("ras")) { // Reorder Answers
				reorderExamAnswers();
			} else if (command.equals("re")) { // Reorder Questions / Answers
				reorderExam();
			} else if (command.equals("p")) { // Print the Exam via console
				printExam();
			} else if (command.equals("s")) { // Save the Exam
				saveExam();
			} else if (command.equals("#")) { // Empty
				;
			} else {
				System.out.println("Command is not known \'" + command + "\'");
			}

			System.out.print("\n=>  ");
		}
		System.out.println("Loop Ended?");
	}

	// Show Available Commands
	private static void commandHelp() {
		System.out.println("Here are the available commands to use: ");
		System.out.println("   c   - Create an Empty Exam");
		System.out.println("   l   - Load a Saved Exam from a File");
		System.out.println("   aq  - Add Questions");
		System.out.println("   rq  - Remove Questions");
		System.out.println("   rqs - Reorder Questions");
		System.out.println("   ras - Reorder Answers");
		System.out.println("   re  - Reorder Exam");
		System.out.println("   p   - Print the Exam");
		System.out.println("   s   - Save the Exam");
	}

	// Create Exam Method (DONE)
	private static void createExam() {
		System.out.print("    Enter a new Exam Name: ");
		Scanner df = ScannerFactory.getKeyboardScanner();
		String userInput = df.nextLine();

		check = true;
		exam1 = new Exam(userInput);
		System.out.println("    Creating an Exam was Successful");

		if (debugMode == true) {
			System.out.println("    UserInput: " + userInput);
		}

	}

	// Load Exam Method (DONE)
	private static void loadExam() {

		if (check == false) {
			System.out.print("    Enter the Exam File Name: ");
			Scanner df = ScannerFactory.getKeyboardScanner();

			String userInput = df.nextLine(); // Holds examFileName
			userInput = userInput + ".txt";

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

	// Helper function (Checks if String is an Integer)
	private static boolean isInteger(String msg) {
		try {
			Integer.parseInt(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// Helper function (Checks if String is a double)
	private static boolean isDouble(String msg) {
		try {
			Double.parseDouble(msg);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// Add Answers to Question
	private static void addAnswertoQuest(int select, Question q) { // Make this return Question / Answer?

		// Multiple Answers to MCMA or MCSA
		if (select == 0 || select == 1) {
			System.out.println("    How many Answers from 1-5 would you like to add?");
			System.out.print("    =>  ");
			Scanner df = ScannerFactory.getKeyboardScanner();
			String userInput = df.nextLine();
			ansLimit = 0;

			if (isInteger(userInput)) {
				ansLimit = Integer.parseInt(userInput);
			}

			while (ansLimit > 5 || ansLimit <= 0) {
				System.out.println("    Error: Please enter a 1,2,3,4, or 5 for an answer.");
				System.out.print("    =>  ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();

				if (isInteger(userInput)) {
					ansLimit = Integer.parseInt(userInput);
				}
			}
		}

		if (select == 0) { // MCSA Answer
			for (int i = 0; i < ansLimit; i++) {
				// MCSAAnswer a1 = new MCSAAnswer("",5.0);
				System.out.print("    \nEnter the Answer for the question: ");
				Scanner uI = ScannerFactory.getKeyboardScanner();
				String userInputMsg = uI.nextLine();
				System.out.print("    \nEnter the amount of points for the answer: ");
				uI = ScannerFactory.getKeyboardScanner();
				String userInputPts = uI.nextLine();
				double ppA = 0.0;

				if (isDouble(userInputPts)) {
					ppA = Double.parseDouble(userInputPts);
				}

				while (!(isDouble(userInputPts))) {
					System.out.println("    Error: Please enter a double for points.");
					System.out.print("    =>  ");
					uI = ScannerFactory.getKeyboardScanner();
					userInputPts = uI.nextLine();
					if (isDouble(userInputPts)) {
						ppA = Double.parseDouble(userInputPts);
					}
				}

				MCSAQuestion q1 = (MCSAQuestion) q;

				MCSAAnswer a1 = new MCSAAnswer(userInputMsg, ppA);

				q1.addAnswer(a1);

				// Debugging
				if (debugMode == true) {
					System.out.println("    User Inputed " + userInputMsg + " for an answer");
					System.out.println("    User gave " + userInputPts + " for the answer above");
				}
			}

		} else if (select == 1) { // MCMA Answer
			for (int i = 0; i < ansLimit; i++) {
				// MCSAAnswer a1 = new MCSAAnswer("",5.0);
				System.out.print("    \nEnter the Answer for the question: ");
				Scanner uI = ScannerFactory.getKeyboardScanner();
				String userInputMsg = uI.nextLine();
				System.out.print("    \nEnter the amount of points for the answer: ");
				uI = ScannerFactory.getKeyboardScanner();
				String userInputPts = uI.nextLine();
				double ppA = 0.0;

				if (isDouble(userInputPts)) {
					ppA = Double.parseDouble(userInputPts);
				}
				while (!(isDouble(userInputPts))) {
					System.out.println("    Error: Please enter a double for points.");
					System.out.print("    =>  ");
					uI = ScannerFactory.getKeyboardScanner();
					userInputPts = uI.nextLine();
					if (isDouble(userInputPts)) {
						ppA = Double.parseDouble(userInputPts);
					}
				}

				MCMAQuestion q1 = (MCMAQuestion) q;

				MCMAAnswer a1 = new MCMAAnswer(userInputMsg, ppA);

				q1.addAnswer(a1);

				// Debugging
				if (debugMode == true) {
					System.out.println("    User Inputed " + userInputMsg + " for an answer");
					System.out.println("    User gave " + userInputPts + " for the answer above");
				}
			}

		} else if (select == 2) { // SA Answer
			System.out.print("    Enter the answer to the question: ");
			Scanner uI = ScannerFactory.getKeyboardScanner();
			String userInputMsg = uI.nextLine();

			SAQuestion q1 = (SAQuestion) q;
			SAAnswer a1 = new SAAnswer(userInputMsg);
			q1.setRightAnswer(a1);

		} else if (select == 3) { // Num Answer
			System.out.print("    Enter the answer to the question: ");
			Scanner uI = ScannerFactory.getKeyboardScanner();
			String userInputMsg = uI.nextLine();
			double ansD = 0.0;

			if (isDouble(userInputMsg)) {
				ansD = Double.parseDouble(userInputMsg);
			}
			while (!(isDouble(userInputMsg))) {
				System.out.println("    Error: Please enter a double for the answer.");
				System.out.print("    =>  ");
				uI = ScannerFactory.getKeyboardScanner();
				userInputMsg = uI.nextLine();
				if (isDouble(userInputMsg)) {
					ansD = Double.parseDouble(userInputMsg);
				}
			}
			NumQuestion q1 = (NumQuestion) q;
			NumAnswer a1 = new NumAnswer(ansD);
			q1.setRightAnswer(a1);
		}

		// Debugging Purposes
		if (debugMode == true) {
			System.out.println("    User Wanted " + ansLimit + " Answers.");
		}

	}

	// Add Questions Method
	private static void questionAdd() {

		if (check == true) {
			System.out.println("    What Type of Question would you like to add?");
			// System.out.println(" Usage: MCSAQ_5.0_Which of these cars is the best?_");
			System.out.println("    i.e) MCSAQ, MCMAQ, SAQ, NumQ");
			System.out.print("    ");

			Scanner df = ScannerFactory.getKeyboardScanner();
			String userInput = df.nextLine();

			// String[] userInputRefined = userInput.split("_");
			//
			if (userInput.equals("MCSAQ")) {

				System.out.print("    \nEnter the amount of points for the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				Double valP = 0.0;

				if (isDouble(userInput)) {
					valP = Double.parseDouble(userInput);
				}

				System.out.print("    \nEnter the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				String qMsgP = userInput;

				// String qMsgP = userInputRefined[2];
				// valP = Double.parseDouble(userInputRefined[1]);

				if (debugMode == true) {
					System.out.println("qMsg: " + qMsgP);
					System.out.println("Valp: " + valP);
				}

				MCSAQuestion q = new MCSAQuestion(qMsgP, valP); // Could Replace with Scanner

				// Adds Answer here
				addAnswertoQuest(0, q);

				exam1.addQuestion(q);
				System.out.println("    \nQuestion has been successfully Added.");

			} else if (userInput.equalsIgnoreCase("MCMAQ")) {
				
				System.out.print("    \nEnter the amount of points for the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				Double valP = 0.0;

				if (isDouble(userInput)) {
					valP = Double.parseDouble(userInput);
				}
				
				System.out.print("    \nEnter the amount for the base credit: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				Double valBC = 0.0;

				if (isDouble(userInput)) {
					valBC = Double.parseDouble(userInput);
				}
				
				System.out.print("    \nEnter the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				String qMsgP = userInput;

				//String qMsgP = userInputRefined[3];
				//Double valP = Double.parseDouble(userInputRefined[1]);
				//Double valBC = Double.parseDouble(userInputRefined[2]);
				
				MCMAQuestion q = new MCMAQuestion(qMsgP, valP, valBC);

				addAnswertoQuest(1, q);

				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");

			} else if (userInput.equalsIgnoreCase("SAQ")) {
				
				System.out.print("    \nEnter the amount of points for the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				Double valP = 0.0;

				if (isDouble(userInput)) {
					valP = Double.parseDouble(userInput);
				}

				System.out.print("    \nEnter the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				String qMsgP = userInput;

				//String qMsgP = userInputRefined[2];
				//Double valP = Double.parseDouble(userInputRefined[1]);
				
				SAQuestion q = new SAQuestion(qMsgP, valP);

				addAnswertoQuest(2, q);
				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");

			} else if (userInput.equalsIgnoreCase("NumQ")) {
				
				System.out.print("    \nEnter the amount of points for the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				Double valP = 0.0;

				if (isDouble(userInput)) {
					valP = Double.parseDouble(userInput);
				}

				System.out.print("    \nEnter the question: ");
				df = ScannerFactory.getKeyboardScanner();
				userInput = df.nextLine();
				String qMsgP = userInput;

				//String qMsgP = userInputRefined[2];
				//Double valP = Double.parseDouble(userInputRefined[1]);
				
				NumQuestion q = new NumQuestion(qMsgP, valP);
				addAnswertoQuest(3, q);

				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");

			} else {
				// System.out.println(" Error: User did not enter correct format.");
			}

			if (debugMode == true) {
				// Raw User Input
				System.out.println("    UserInputed: " + userInput);
				// System.out.println(" userInputRefinedLength: " + userInputRefined.length);

				// Tokenized Input
				// for (int i = 0; i < userInputRefined.length; i++) {
				// System.out.println(" Array: " + userInputRefined[i]);
				// }
			}

		} else {
			System.out.println("    Error: No Exam was Loaded.");
		}
	}

	// Remove Questions (DONE)
	private static void questionRemove() {
		if (check == true) {
			System.out.println("    Which Question would you like to remove?");
			Scanner df = ScannerFactory.getKeyboardScanner();
			String userInput = df.nextLine();
			int position = Integer.parseInt(userInput);

			if (exam1.removeQuest(position) == 1) {
				System.out.println("    Question removed was successful.");
			} else {
				System.out.println("    Error: Invalid Question to Remove");
			}

		} else {
			System.out.println("    Error: Cannot Remove Question if Exam was not loaded.");
		}

	}

	// Reorder Questions, and/or Answers Method (DONE)
	private static void reorderExamQuestions() {
		if (check == true) {
			exam1.reorderQuestion();
			System.out.println("    Quesitons has been successfully Reordered.");
		} else {
			System.out.println("    Error: Cannot Reorder Questions if Exam was not Loaded.");
		}
	}

	// Reorder Exam Answers (DONE)
	private static void reorderExamAnswers() {
		if (check == true) {
			exam1.reorderMCAnswer(-1);
			System.out.println("    Answers has been successfully Reordered.");
		} else {
			System.out.println("    Error: Cannot Reorder Answers if Exam was not Loaded.");
		}
	}

	// Reorders the Exam (DONE)
	private static void reorderExam() {
		if (check == true) {
			reorderExamQuestions();
			reorderExamAnswers();
		} else {
			System.out.println("    Error: Cannot Reorder Exam if exam was not loaded.");
		}
	}

	// Print the Exam via Console Method (DONE)
	private static void printExam() {
		if (check == true) {
			exam1.print();
		} else {
			System.out.println("    Error: Cannot print exam if exam was not loaded.");
		}
	}

	// Save the Exam, to the file Method (DONE)
	private static void saveExam() {
		if (check == true) {
			System.out.print("    Saving the Exam");

			try {
				for (int i = 0; i < 3; i++) {
					System.out.print(".");
					Thread.sleep(1000);
				}
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Random rand = new Random();
			int n = rand.nextInt(9);
			String randomNumber = "";

			for (int i = 0; i < n; i++) {
				randomNumber += randomNumber + Integer.toString(n);
				n = rand.nextInt(9);
			}

			exam1.setVersionNum(randomNumber);

			String newExamFile = "examFile" + randomNumber + ".txt";

			File fileNameExam = new File(directoryM, newExamFile);

			try {
				fileNameExam.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				PrintWriter ldf = new PrintWriter(fileNameExam);
				exam1.save(ldf); // Saves Empty test
				ldf.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("    Error: Cannot save Exam if Exam was not loaded.");
		}
	}

	public static void main(String[] args) {
		System.out.println("CS342 Exam Builder" + "\nAuthor: " + myName + "\nNet-id: " + myNetID + "\n");
		System.out.println("Welcome Instructor to the Exam Builder App" + "\n"
				+ "For more information about the commands type '?'\n");
		commandLoop();
	}
*/	
}
