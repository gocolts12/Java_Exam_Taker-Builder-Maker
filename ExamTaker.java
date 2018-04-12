
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class ExamTaker {

	private static String myName = "Jonel Alcasid";
	private static String myNetID = "jalcas2";

	//private static String directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/";
	//private static String directoryA = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/";
	private static String directoryM = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW4_New";
	private static String directoryA = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW4_New";

	private static int questionSize = 10; // Change HERE~!

	private volatile static boolean debugMode = false; // Debug Mode
	private static boolean check;
	private static Exam exam1;

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

	public static void main(String[] args) {
		loadExam();

		int i = 0;
		while (i < questionSize) {
			exam1.getAnswerFromStudent(i);
			i++;
		}

		String newFileAns = "ansFile" + exam1.getVersionNum() + ".txt";

		File fileNameAns = new File(directoryA, newFileAns);

		try {
			fileNameAns.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// Writes Answers to File
			PrintWriter ldf = new PrintWriter(fileNameAns);
			// Saves Student Answers
			exam1.saveStudentAnswers(ldf);
			ldf.flush();

		} catch (IOException ex) {
			System.out.println("ERROR in Exam: " + ex);
		}
		
		System.out.println("\nEnd of Program.\n");
	}
}
