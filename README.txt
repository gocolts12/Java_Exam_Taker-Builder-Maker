Author: Brian De Villa
Project: CS342 HW4
net-id: bdevil2
UIN: 679893951

How To Make the Program:
	Simply type "make" and you should be able to see it compile the 15 java files.

How To Run the Program:
	Type in "java ExamBuilder” without the quotes and the program will run.
	
BEFORE RUNNING PROGRAM:
	Please Change at the top of the examBuilder the directory for examfile.txt & ansFile.txt
private static String directoryM = “CHANGE THIS”;
private static String directoryA = “CHANGE THIS”;

Description:
	The purpose of the program is to have an Instructor build an Exam from ExamBuilder. For more information for the commands the instructor can enter “?” without the quotes and be able to see the available commands. 

Commands include: 
	Creating a fresh new Exam
	Load an Exam
	Add Questions
	Remove Questions
	Reorder Questions, and/or answers
	Print the Exam
	Save the Exam
	Quit

The program currently does not support the two Optional Enhancements defined in the ExamBuilder Acceptance Tests.






//************************************************************************
Author: Jonel Alcasid
Project: CS342 HW4
Net-id: jalcas2
UIN:671528474

How To Make the Program:
	Simply type "make ExamTaker" and you should be able to see it compile the 15 java files.

How To Run the Program:
	Type in "java ExamTaker" without the quotes and the program will run.
	
BEFORE RUNNING PROGRAM:
	Please Change at the top of the examTester the directory for examfile.txt & ansFile.txt
private static String directoryM = “CHANGE THIS”;
private static String directoryA = “CHANGE THIS”;

Description:
	The purpose of the program is to have a user take the loaded exam and save his/her answers into a file with corresponding version control for the exam. Options include the user being able to skip questions, go back to answer them, and change answers. When coming back to answer the skipped questions those questions cannot be skipped again and must be answered. All this functionally is in ExamTaker.java and Exam.java. In the ExamTaker class before each question it asks the user if they want to skip the question or answer it. If the user chooses to skip or enters an invalid choose then the question is skipped and will be able to be answered at a later time. If the user chooses to answer then user is prompted to answer and afterwards the program automatically advances to the next question. When all the questions are answered then the programs will ask the student if he/her would like to go back and change answers. If yes then the questions will be displayed again with a prompt asking if they wish to change their answer for the question or leave it as is. If student answers no then the answers will be saved into a text file. Re-answered questions will be overwritten and correctly shows the students last answer for that question.
//************************************************************************

Author: Michael Lederer
Project: CS342 HW4
Net-id: mleder3
UIN: 677140523

How To Make the Program:
	Simply type "make ExamGrader" and you should be able to see it compile the 15 java files.


How To Run the Program:
	Type in "java ExamGrader" without the quotes and the program will run.
	
BEFORE RUNNING PROGRAM:
	YOU MUST change the directory for examfile.txt & ansFile.txt in ExamGrader, as well as the directory in the "printToCSV" method in Exam.java
	in order to save each file to the correct locations.

Description:
	This app loads an answer file, locates the corresponding exam file, and proceeds to list the grades for the exam. This app is designed to ONLY take in an answer file, and because of the naming convention of the files, as long as an answer file was created, a corresponding exam file should exist. 
	For example, once ExamBuilder creates an exam, it will be named “examFilexxxx.txt” where the number of x’s can vary between 1-9. This is the version number. When an exam is taken, the corresponding version number is appended to the answer file, naming it “ansFilexxxx.txt,” similar to the exam file. 
	This design choice of only taking in the answer file will alleviate all issues of version differences as well as date discrepancies, although a date discrepancy checker was implemented just in case.

