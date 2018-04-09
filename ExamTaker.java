package HW_4;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class ExamTaker 
{
	
	private static String myName = "Jonel Alcasid";
	private static String myNetID = "jalcas2";

	//private static int questionSize = 10; // Change to adjust the amount of questions in the test.
	//private static double examTotal = 0; // Leave Unchanged

	//private static String directoryM = "C://Users//Jonel//eclipse-workspace//CS_342_proj4_V2//src/examfile.txt"; // CHANGE DIRECTROY // HERE~!
	//private static String directoryM;
	//private static String directoryA = "C://Users//Jonel//eclipse-workspace//CS_342_proj4_V2//src/ansFile.txt"; // Change DIRECTORY // HERE~!
	private static String directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/examfile.txt";
	private static String directoryA = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/ansFile.txt";
	private ArrayList<Question> qArraySkip = new ArrayList<Question>(20);
	private static int questionSize = 10;
	private static double examTotal = 0;
	private volatile static boolean debugMode = false; // Debug Mode
	private static boolean check;
	private static Exam exam1;
	private static String studentName;
	// Load Exam Method (DONE)
    private static void loadExam() 
    {

        if (check == false) 
        {
            System.out.print("    Enter the Exam File Name: ");
            Scanner df = ScannerFactory.getKeyboardScanner();

            String userInput = df.nextLine(); // Holds examFileName

            //directoryM = "C:\\Users\\DrNoodles\\eclipse-workspace\\CS342 HW_4\\src\\HW_4"; // CHANGE
            directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4";// Directory // HERE...
            //directoryM  = "C://Users//Jonel//eclipse-workspace//CS_342_proj4//src";
            userInput = userInput + ".txt";

            // boolean check = new File(directoryM, userInput).exists();

            check = new File(directoryM, userInput).exists();

            if (check == true) 
            {
                System.out.println("    File has been successfully Loaded.");
                File fileNameExam = new File(directoryM, userInput);
                try 
                {
                    Scanner fd = new Scanner(fileNameExam);
                    exam1 = new Exam(fd);
                    
                } 
                catch (FileNotFoundException e) 
                {
                    e.printStackTrace();
                }
            } 
            else 
            {
                System.out.println("    Error: No File Exist with that name");
            }

            if (debugMode == true) 
            {
                System.out.println("    User Inputted: " + userInput);
                System.out.println("    Check is: " + check);
                String current = System.getProperty("user.dir");
                System.out.println("    Current Working Dir in Java: " + current);
            }
        } 
        else 
        {

            System.out.println("    Error: An Exam has been already created or loaded.");
        }
    }
    
    private static void printExam() 
    {
		if (check == true) 
		{
			exam1.print();
		} 
		else 
		{
			System.out.println("    Error: Cannot print exam if exam was not loaded.");
		}
	}
    
    public static void main(String[] args) 
	{
    	loadExam();
    	//exam1.getAnswerFromStudent(-1);
	
    	//User-Input Here
    	//Original
	//	for (int i = 0; i < questionSize; i++) {
	//	exam1.getAnswerFromStudent(i);
	//	}
    	 //
    	int i = 0;
    	while(i<questionSize)
    	{
    		//System.out.print("Enter a zero to skip question");
    		exam1.getAnswerFromStudent(i);
    		i++;
    	}
    	//Get total User Answers
    	//System.out.println("\nUser Scored: " + exam1.getValue() + " / " + examTotal);
	
    	//Report the Questions value in the exam.
    	//exam1.reportQuestionValues();
    	
    	directoryM = "/Users/DrNoodles/eclipse-workspace/CS342 HW_4/src/HW_4/newexamfile.txt";
    	
	
    	File fileNameExam = new File(directoryM);
    	File fileNameAns = new File(directoryA);
    	try 
    	{
    		//Writes Empty Exam to File
    		//PrintWriter ldf = new PrintWriter(fileNameExam);
    		//exam1.save(ldf);	//Saves Empty test
    		//ldf.flush();
		
    		//Writes Answers to File
    		PrintWriter ldf = new PrintWriter(fileNameAns);
    		//Print Student's Name here
    		//ldf.println(studentName);
    		exam1.saveStudentAnswers(ldf);
    		ldf.flush();
		
    		//Make the Exam Null
    		//exam1 = null;
		
    		//New Scanner
    		//Scanner sc = new Scanner(fileNameExam);
		
    		//Read In the File and takes in the Exam Name
    		//exam1 = new Exam(sc);
		
    		//Soemthing Else
    		//System.out.println("-------------------------------------------------------------------------");
    		//sc.close();
		
    		//sc = new Scanner(fileNameAns);
		
    		//System.out.println("Exam_RestoreAnswers: ");
    		//exam1.restoreStudentAnswers(sc);
		
    		//exam1.print();	
    	} 
    	catch (IOException ex) 
    	{
    		System.out.println("ERROR in Exam: " + ex);
    	}
	}

}

