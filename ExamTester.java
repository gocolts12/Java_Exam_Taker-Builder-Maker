

import java.util.Random;
import java.io.File;
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


public class ExamTester {

	//Name
	private static String myName = "Brian De Villa";
	private static String myNetID = "bdevil2";

	private static int questionSize = 10;	//Change to adjust the amount of questions in the test.
	private static double examTotal = 0;	//Leave Unchange.

	private static String directoryM = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW3/examfile.txt";	//CHANGE DIRECTROY HERE~!
	private static String directoryA = "/nfsdirs/home4/home4/ugrad4/bvilla/CS342/HW3/ansFile.txt";	//Change DIRECTORY HERE~!

	
	//Command_Loop (Unused but left for future implement)
	private static void commandLoop() {
		System.out.print("=>  ");
		Scanner sc = new Scanner(System.in);

		//String s = sc.nextLine();
		//StringTokenizer st = new StringTokenizer(s, " \n\t"); //delim

		while (sc.hasNext()) {
			String command = sc.next();


			if (command == null) {
				System.out.println("Blank Line\n");
			} else if (command.equals("q")) {
				System.out.println("Exiting Program...\n");
				System.exit(0);

			} else if (command.equals("?")) {
				//showCommands()
			} else if (command.equals("r")) {

			} else if (command.equals("#")) {
				;
			} else {
				System.out.println("Command is not known \'" + command + "\'");
			}

			System.out.print("\n=>  ");
		}
		System.out.println("Loop Ended?");
	}


	public static void main(String[] args) {
		System.out.println("CS342 Exam Tester" + "\nAuthor: " + myName + "\nNet-id: " + myNetID + "\n");

		Exam exam1 = new Exam("Exam_01");

		//commandLoop();    //Unused


		//Flawed Question#1
		MCSAQuestion q1 = new MCSAQuestion("Which of these cars is the best?", 5.0);

			//Create Answers
			MCSAAnswer a = new MCSAAnswer("Lancer Evo III", 0.0);
			MCSAAnswer b = new MCSAAnswer("Subaru WRX STI", 5.0);
			MCSAAnswer c = new MCSAAnswer("Toyota AE86", 0.0);
			MCSAAnswer d = new MCSAAnswer("Toyota Prius", 0.0);

			//Add Answers
			q1.addAnswer(a);
			q1.addAnswer(b);
			q1.addAnswer(c);
			q1.addAnswer(d);

		//Question #2
		SAQuestion q2 = new SAQuestion("Type in the color for apple!", 5.0);

			//Creates an Answer
			SAAnswer sA2 = new SAAnswer("red");	//Not-case sensitive


		//Question #3
		MCSAQuestion q3 = new MCSAQuestion("Which of these games is considered a First-Person shooter game?", 5.0);

			//Creates Answers
			MCSAAnswer a3 = new MCSAAnswer("Dota 2", 0.0);
			MCSAAnswer b3 = new MCSAAnswer("World of Warcraft", 0.0);
			MCSAAnswer c3 = new MCSAAnswer("Don't Starve Together", 0.0);
			MCSAAnswer d3 = new MCSAAnswer("Tom Clancy's Rainbow Six: Siege", 5.0);
			MCSAAnswer e3 = new MCSAAnswer("Monster Hunter World", 0.0);

			//Add Answers to Question
			q3.addAnswer(a3);
			q3.addAnswer(b3);
			q3.addAnswer(c3);
			q3.addAnswer(d3);
			q3.addAnswer(e3);

		//Question #4
		SAQuestion q4 = new SAQuestion("What is the capital of Wisconsin?", 5.0);
			SAAnswer sA4 = new SAAnswer("madison");	//Not-case sensitive

		//Question #5 (Polymorphism with tons of casting)
		Question q5 = new MCSAQuestion("Who is the owner of the Krusty Krab?", 5.0);

			//Creates Answers
			Answer a5 = new MCSAAnswer("Eugene H. Krabs", 5.0);
			Answer b5 = new MCSAAnswer("Spongebob Squarepants", 0.0);
			Answer c5 = new MCSAAnswer("Patrick Star", 0.0);
			Answer d5 = new MCSAAnswer("Larry the Lobster", 0.0);
			Answer e5 = new MCSAAnswer("Mrs.Puff",0.0);

			//Add Answers to Question
			((MCQuestion) q5).addAnswer((MCAnswer) a5);
			((MCQuestion) q5).addAnswer((MCAnswer) b5);
			((MCQuestion) q5).addAnswer((MCAnswer) c5);
			((MCQuestion) q5).addAnswer((MCAnswer) d5);
			((MCQuestion) q5).addAnswer((MCAnswer) e5);


		//Question #6
		SAQuestion q6 = new SAQuestion("Who created Steam or Valve?", 5.0);
			SAAnswer sA5 = new SAAnswer("Gabe Newell");


		//Question #7
		MCMAQuestion q7 = new MCMAQuestion("Who went to Hogwarts? Circle all that apply: ", 5.0, 0.4);
			MCMAAnswer a7 = new MCMAAnswer("Harry Potter", 0.2);
			MCMAAnswer b7 = new MCMAAnswer("Rubeus Hagrid", 0.2);
			MCMAAnswer c7 = new MCMAAnswer("Princess Leia", -0.2);
			MCMAAnswer d7 = new MCMAAnswer("Bilbo Baggins", -0.2);
			MCMAAnswer e7 = new MCMAAnswer("Tom Riddle", 0.2);

			//Add Answers to Question
			q7.addAnswer(a7);
			q7.addAnswer(b7);
			q7.addAnswer(c7);
			q7.addAnswer(d7);
			q7.addAnswer(e7);

		//Quesiton #8
		MCMAQuestion q8 = new MCMAQuestion ("Who was from the Halo Series? Circle all that apply: ", 5.0, 0.4);
			MCMAAnswer a8 = new MCMAAnswer("Spartan 117", 0.2);
			MCMAAnswer b8 = new MCMAAnswer("Cortana", 0.2);
			MCMAAnswer c8 = new MCMAAnswer("Spiderman", -0.2);
			MCMAAnswer d8 = new MCMAAnswer("Sgt. Johnson", 0.2);
			MCMAAnswer e8 = new MCMAAnswer("Naruto", -0.2);

			//Add Answers to Question
			q8.addAnswer(a8);
			q8.addAnswer(b8);
			q8.addAnswer(c8);
			q8.addAnswer(d8);
			q8.addAnswer(e8);


		//Question #9
		NumQuestion q9 = new NumQuestion("What is 1 + 1?", 5.0);
			NumAnswer nA9 = new NumAnswer(2);

		//Question #10
		NumQuestion q10 = new NumQuestion("What is 5 x 5?", 5.0);
			NumAnswer nA10 = new NumAnswer(25);


		//Set the Correct Answers for the Questions
		q1.setRightAnswer(b);
		q2.setRightAnswer(sA2);
		q3.setRightAnswer(d3);
		q4.setRightAnswer(sA4);
		((MCQuestion) q5).setRightAnswer((MCAnswer) a5);
		q6.setRightAnswer(sA5);
		q9.setRightAnswer(nA9);
		q10.setRightAnswer(nA10);



		//Adds questions
		exam1.addQuestion(q1);
		exam1.addQuestion(q2);
		exam1.addQuestion(q3);
		exam1.addQuestion(q4);
		exam1.addQuestion(q5);
		exam1.addQuestion(q6);
		exam1.addQuestion(q7);
		exam1.addQuestion(q8);
		exam1.addQuestion(q9);
		exam1.addQuestion(q10);

//~~~~~~~~~~~~~~~~~~~~~~~~~TEST CASES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		//Reorders the questions
		exam1.reorderQuestion();

		//Reorders MCAnswer by some Random Variable
		Random rand = new Random();
		//int n = rand.nextInt(5) - 1;
		int n = -1;

		if ( n < 0) {
			System.out.println("All MCQuestions are to be randomized.");
		} else {
			System.out.println("MCQuestion to be Randomized: " + (n+1));
		}

		exam1.reorderMCAnswer(n);

		//Total Points of the Exam
		//System.out.println("\nTotal Points of Exam: " +exam1.getValue());

		System.out.println("\nTotal Points of Exam: " + (questionSize * 5));	 		//Each Question is 5 PTS

		examTotal = (questionSize * 5);

		//Prints the exam.
		exam1.print();

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~USER Input Required Here~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("\n\nUser Answers Questions:");

		//Test for User-Input as negative
		exam1.getAnswerFromStudent(-1);

		//User-Input Here
		for (int i = 0; i < questionSize; i++) {
			exam1.getAnswerFromStudent(i);
		}

		//Get total User Answers
		System.out.println("\nUser Scored: " + exam1.getValue() + " / " + examTotal);

		//Report the Questions value in the exam.
		exam1.reportQuestionValues();


		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NEW STUFF for HW_3~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		//FILE_Saving Mechanics
		File fileNameExam = new File(directoryM);
		File fileNameAns = new File(directoryA);
		try {
			//Writes Empty Exam to File
			PrintWriter ldf = new PrintWriter(fileNameExam);
			exam1.save(ldf);	//Saves Empty test
			ldf.flush();


			//Writes Answers to File
			ldf = new PrintWriter(fileNameAns);
			//Print Student's Name here
			//ldf.println(studentName);
			exam1.saveStudentAnswers(ldf);
			ldf.flush();

			//Make the Exam Null
			exam1 = null;

			//New Scanner
			Scanner sc = new Scanner(fileNameExam);

			//Read In the File and takes in the Exam Name
			exam1 = new Exam(sc);


			//Soemthing Else
			System.out.println("-------------------------------------------------------------------------");
			sc.close();

			sc = new Scanner(fileNameAns);

			System.out.println("Exam_RestoreAnswers: ");
			exam1.restoreStudentAnswers(sc);

			//exam1.print();



		} catch (IOException ex) {
			System.out.println("ERROR in ExamT: " + ex);
		}


		//Exit
		System.out.println("\nEnd of Program.\n");
		System.exit(0);

	}

}
