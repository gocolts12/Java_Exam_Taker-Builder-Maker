package HW_4_ExamBuilder;

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

	// Name
	private static String myName = "Brian De Villa";
	private static String myNetID = "bdevil2";

	private static int questionSize = 10; // Change to adjust the amount of questions in the test.
	private static double examTotal = 0; // Leave Unchanged

	private static String directoryM = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW3/examfile.txt"; // CHANGE DIRECTROY
																									// HERE~!
	private static String directoryA = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW3/ansFile.txt"; // Change DIRECTORY
																									// HERE~!

	private volatile static boolean debugMode = true; // Debug Mode

	private static boolean check;

	private static Exam exam1;

	// Command_Loop (Unused but left for future implement)
	private static void commandLoop() {
		System.out.print("=>  ");
		Scanner sc = new Scanner(System.in);

		// String s = sc.nextLine();
		// StringTokenizer st = new StringTokenizer(s, " \n\t"); //delim

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
		System.out.println("   l   - Load a Saved Exam from a File");
		System.out.println("   aq  - Add Questions");
		System.out.println("   rq  - Remove Questions");
		System.out.println("   rqs - Reorder Questions");
		System.out.println("   ras - Reorder Answers");
		System.out.println("   re  - Reorder Exam");
		System.out.println("   p   - Print the Exam");
		System.out.println("   s   - Save the Exam");
	}

	// Load Exam Method
	private static void loadExam() {

		System.out.print("    Enter the Exam File Name: ");
		Scanner df = ScannerFactory.getKeyboardScanner();

		String userInput = df.nextLine(); // Holds examFileName

		directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342 HW_4\\src\\HW_4_ExamBuilder"; // CHANGE Directory HERE...
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

	}

	// Add Questions Method
	private static void questionAdd() {
		
		if (check == true) {
			System.out.println("    What Type of Question would you like to add?");
			System.out.println("    Usage: MCSAQ_5.0_Which of these cars is the best?_");
			System.out.print("    ");

			Scanner df = ScannerFactory.getKeyboardScanner();
			String userInput = df.nextLine();

			// Could use String Tokenizer
			// StringTokenizer st = new StringTokenizer(userInput, "_");

			String[] userInputRefined = userInput.split("_");

			if (userInputRefined[0] == "MCSAQ") {
				String qMsgP = userInputRefined[2];
				Double valP = Double.parseDouble(userInputRefined[1]);
				MCSAQuestion q = new MCSAQuestion(qMsgP, valP);
				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");
			} else if (userInputRefined[0] == "MCMAQ") {
				String qMsgP = userInputRefined[3];
				Double valP = Double.parseDouble(userInputRefined[1]);
				Double valBC = Double.parseDouble(userInputRefined[2]);
				MCMAQuestion q = new MCMAQuestion(qMsgP, valP, valBC);
				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");
			} else if (userInputRefined[0] == "SAQ") {
				String qMsgP = userInputRefined[2];
				Double valP = Double.parseDouble(userInputRefined[1]);
				SAQuestion q = new SAQuestion(qMsgP, valP);
				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");
			} else if (userInputRefined[0] == "NumQ") {
				String qMsgP = userInputRefined[2];
				Double valP = Double.parseDouble(userInputRefined[1]);
				NumQuestion q = new NumQuestion(qMsgP, valP);
				exam1.addQuestion(q);
				System.out.println("    Question has been successfully Added.");
			} else {
				System.out.println("    Error: User did not enter correct format.");
			}

			// Notes
			// exam1.addQuestion(q1);
			// MCSAQuestion q3 = new MCSAQuestion("Which of these games is considered a
			// First-Person shooter game?", 5.0);

			if (debugMode == true) {
				// Raw User Input
				System.out.println("    UserInputed: " + userInput);
				// Tokenized Input
				for (int i = 0; i < userInputRefined.length; i++) {
					System.out.println("    Array: " + userInputRefined[i]);
				}
			}

		} else {
			System.out.println("    Error: No Exam was Loaded.");
		}
	}

	// Remove Questions
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

	// Reorder Questions, and/or Answers Method
	private static void reorderExamQuestions() {
		if (check == true) {
			exam1.reorderQuestion();
			System.out.println("    Quesitons has been successfully Reordered.");
		} else {
			System.out.println("    Error: Cannot Reorder Questions if Exam was not Loaded.");
		}
	}

	private static void reorderExamAnswers() {
		if (check == true) {
			exam1.reorderMCAnswer(-1);
			System.out.println("    Answers has been successfully Reordered.");
		} else {
			System.out.println("    Error: Cannot Reorder Answers if Exam was not Loaded.");
		}
	}

	private static void reorderExam() {
		if (check == true) {
			reorderExamQuestions();
			reorderExamAnswers();
		} else {
			System.out.println("    Error: Cannot Reorder Exam if exam was not loaded.");
		}
	}

	// Print the Exam via Console Method
	private static void printExam() {
		if (check == true) {
			exam1.print();
		} else {
			System.out.println("    Error: Cannot print exam if exam was not loaded.");
		}
	}

	// Save the Exam, to the file Method
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PROBLEMATIC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!
	private static void saveExam() {
		if (check == true) {
			System.out.println("    Save the Exam's name: ");

			Scanner df = ScannerFactory.getKeyboardScanner();
			String userInput = df.nextLine();

			File fileNameExam = new File(directoryM, userInput);

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
}
