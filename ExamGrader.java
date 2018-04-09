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
		System.out.println("Please enter the name of your answer file.");
		
		//scan in the file name for the answer, extract the version number, and search for 
		//the corresponding exam file by appending the version number to the the exam file name
		Scanner sc = ScannerFactory.getKeyboardScanner();
		String userInput = sc.nextLine();
		
		String directoryM = "/home/forsene/Desktop/342/342proj4";
		
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
					e.printStackTrace();
				}
		           
		         //Load Answer
				  try { 
				   Scanner fd = new Scanner(fileNameAnswer);
				    
				    int tracker = 0;
				    if (sc.hasNextLine()) {
				            String name = sc.nextLine();
				            studentName = name;
				     
				            String examName = sc.nextLine();
				            examName = examName;
				         
				            String timeStamp = sc.nextLine();
				            timeStamp = timeStamp;
				       
				            String versionNumber = sc.nextLine();
				            versionNumber = versionNumber;
				
				    //Heart of Scanning the answer file
				            while (sc.hasNextLine()) 
				            {
				            	String v = sc.nextLine();
				                if (v.equals("MCSAAnswer")) 
				                {
				                	v = sc.nextLine();
				                	//MCSAAnswer ans = new MCSAnswer(v, );
				                	exam1.qArray.get(0)
				                	
				                } 
				                else if (v.equals("MCMAAnswer"))
				                {
				                	
				                } 
				                else if (v. equals("SAAnswer")) 
				                {
				                	
				                } 
				                else if (v.equals("NumAnswer")) 
				                {
				                	
				                }
				
				            }
				
				
				} catch (FileNotFoundException e) {
				     e.printStackTrace();
				     }
				} //End of if statement
				
			}
		}
	}

  // Load Exam Method 
	private static void loadExam() {

		if (check == false) {
			System.out.print("    Enter the Exam File Name: ");
			Scanner df = ScannerFactory.getKeyboardScanner();

			String userInput = df.nextLine(); // Holds examFileName

			//directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342 HW_4\\src\\HW_4"; // CHANGE
			String directoryM = "/home/forsene/Desktop/342/342proj4";																							// Directory
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
