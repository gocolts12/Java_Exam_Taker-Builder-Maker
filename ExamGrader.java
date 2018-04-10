package HW_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExamGrader
{
	private volatile static boolean debugMode = false;
	private static boolean check;
	private static Exam exam1;
	private static String studentName, versionNumber, timeStamp, examName;
	
	
	public ExamGrader()
	{

	}
	public static void main(String[] args)
	{
		System.out.println("Welcome to Exam Grader.");
		System.out.println("Enter the name of your answer file, and the cooresponding ");
		System.out.println("exam file will attempt to be loaded");
		
		Scanner ch = ScannerFactory.getKeyboardScanner();
		String choice = ch.nextLine();
		
		System.out.println("Please enter the name of your answer file.");
		
		//scan in the file name for the answer, extract the version number, and search for 
		//the corresponding exam file by appending the version number to the the exam file name
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String userInput = sc.nextLine();
		
		String directoryM = "C:\\Users\\Michael\\Google Drive\\School\\Junior\\Spring\\342\\342proj4";
		
		check = new File(directoryM, userInput + ".txt").exists();

		if (check == true) 
		{
			System.out.println("Answer file successfully found");
			
			String versionNum = userInput.substring(7);
			
			String examFile = "examFile" + versionNum + ".txt";
			
			boolean check2;
			
			check2 = new File(directoryM, examFile).exists();
			
			if (check2 == true)
			{
				//Both the exam and answer files were found, so both need to be loaded
				System.out.println("    Exam File has been successfully found.");
				File fileNameExam = new File(directoryM, examFile);
				File fileNameAnswer = new File(directoryM, userInput + ".txt");
				//Load exam
				try {
					Scanner fd = new Scanner(fileNameExam);
					exam1 = new Exam(fd);
	
				} catch (FileNotFoundException e) {
					System.out.println("Failure to scan exam");
					e.printStackTrace();
				}
		           
		         //Call restoreStudentAnswers to attach the answers to each exam question
				Scanner an;
				
				try {
					an = new Scanner(fileNameAnswer);
					exam1.restoreStudentAnswers(an);
					exam1.reportQuestionValues();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Corresponding exam could not be found");
			}
		}
	}
}
