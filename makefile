#Makefile for Java HW#1
#author: Brian De Villa
#net-id: bdevil2
#Class: CS342

.SUFFIXES: .java .class

.java.class:
	javac -g $*.java

CLASSES = \
	Question.java \
	Answer.java \
	Exam.java \
	MCAnswer.java \
	MCMAAnswer.java \
	MCMAQuestion.java \
	MCQuestion.java \
	MCSAAnswer.java \
	MCSAQuestion.java \
	NumAnswer.java \
	NumQuestion.java \
	SAAnswer.java \
	SAQuestion.java \
	ExamTester.java

default: classes

classes: $(CLASSES:.java=.class)




clean:
	rm -f *.class




