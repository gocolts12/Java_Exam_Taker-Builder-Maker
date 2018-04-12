package HW_4;

import java.util.Calendar;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ExamGrader {
	
	private static boolean check;
	private static Exam exam1;
	private static String studentName, versionNumber, timeStamp, examName;
	private static ArrayList<Question> arr = new ArrayList<Question>(20);

	public ExamGrader() {

	}

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Exam Grader.");
		System.out.println("Please enter the name of your answer file.");

		// scan in the file name for the answer, extract the version number, and search
		// for the corresponding exam file by appending the version number to the the exam
		// file name
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String userInput = sc.nextLine();

		//String directoryM = "C:\\Users\\Michael\\Google Drive\\School\\Junior\\Spring\\342\\342proj4";
		
		//String directoryM = "C:\\Users\\Michael\\Google Drive\\School\\Junior\\Spring\\342\\342proj4";
		String directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342 HW_4\\src\\HW_4";
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
				
				//Verify that the answer file was created after the exam file
				
//				//Grab the timestamp from the exam file
				timeStamp = exam1.getTimeStamp();
				String [] timeStampArrayExam = timeStamp.split("/");
				int [] examDateArrayInt = new int[6];
				
				//year
				examDateArrayInt[0] = Integer.parseInt(timeStampArrayExam[0]);
				//month
				examDateArrayInt[1] = Integer.parseInt(timeStampArrayExam[1]);
				
				String [] timeStampArrayExam2 = timeStampArrayExam[2].split(" ");
				
				//day of the month
				examDateArrayInt[2] = Integer.parseInt(timeStampArrayExam2[0]);

				String [] timeStampArrayExam3 = timeStampArrayExam2[1].split(":");
				
				examDateArrayInt[3] = Integer.parseInt(timeStampArrayExam3[0]);
				examDateArrayInt[4] = Integer.parseInt(timeStampArrayExam3[1]);
				examDateArrayInt[5] = Integer.parseInt(timeStampArrayExam3[2]);
				
//				Date examDate = new Date(examDateArrayInt[0], examDateArrayInt[1], examDateArrayInt[2], examDateArrayInt[3], 
//						examDateArrayInt[4], examDateArrayInt[5]);		
				Calendar examDate = Calendar.getInstance();
				examDate.set(Calendar.YEAR, examDateArrayInt[0]);
				examDate.set(Calendar.MONTH, examDateArrayInt[1]);
				examDate.set(Calendar.DAY_OF_MONTH, examDateArrayInt[2]);
				examDate.set(Calendar.HOUR_OF_DAY, examDateArrayInt[3]);
				examDate.set(Calendar.MINUTE, examDateArrayInt[4]);
				examDate.set(Calendar.SECOND, examDateArrayInt[5]);
				
//				
//				//Grab the timestamp from the answer file			
				Scanner ansScanner = new Scanner(fileNameAnswer);
				ansScanner.nextLine();
				ansScanner.nextLine();
				String ansTimeStamp = ansScanner.nextLine();
				
				String [] timeStampArrayAnswer = ansTimeStamp.split("/");
				int [] answerDateArrayInt = new int[6];
				
				//year
				answerDateArrayInt[0] = Integer.parseInt(timeStampArrayExam[0]);
				//month
				answerDateArrayInt[1] = Integer.parseInt(timeStampArrayExam[1]);
				
				String [] timeStampArrayAnswer2 = timeStampArrayAnswer[2].split(" ");
				
				//day of the month
				answerDateArrayInt[2] = Integer.parseInt(timeStampArrayExam2[0]);

				String [] timeStampArrayAnswer3 = timeStampArrayAnswer2[1].split(":");
				
				answerDateArrayInt[3] = Integer.parseInt(timeStampArrayAnswer3[0]);
				answerDateArrayInt[4] = Integer.parseInt(timeStampArrayAnswer3[1]);
				answerDateArrayInt[5] = Integer.parseInt(timeStampArrayAnswer3[2]);
				
//				Date examDate = new Date(examDateArrayInt[0], examDateArrayInt[1], examDateArrayInt[2], examDateArrayInt[3], 
//						examDateArrayInt[4], examDateArrayInt[5]);		
				Calendar answerDate = Calendar.getInstance();
				answerDate.set(Calendar.YEAR, answerDateArrayInt[0]);
				answerDate.set(Calendar.MONTH, answerDateArrayInt[1]);
				answerDate.set(Calendar.DAY_OF_MONTH, answerDateArrayInt[2]);
				answerDate.set(Calendar.HOUR_OF_DAY, answerDateArrayInt[3]);
				examDate.set(Calendar.MINUTE, answerDateArrayInt[4]);
				examDate.set(Calendar.SECOND, answerDateArrayInt[5]);
				
				
				
				
				
				//Check that the answer file did not occur before the making of the exam
				if((answerDate.compareTo(examDate) < 0))
				{
					System.out.println("Invalid timestamp, answer file predates exam file");
					System.exit(0);
				}
				
				
				// Call restoreStudentAnswers to attach the answers to each exam question
				Scanner an;
				
				try {
					an = new Scanner(fileNameAnswer);
					exam1.restoreStudentAnswers(an);
					//exam1.print();
					exam1.reportQuestionValues();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Check failed");
			}
			exam1.printToCSV();
		}
		
	}

}
