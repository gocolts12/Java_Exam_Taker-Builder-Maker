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
	ExamBuilder.java
	#ExamTester.java

CLASSES2 = \
	Question.java \
	Answer.java \
	MCAnswer.java \
	MCMAQuestion.java \
	MCQuestion.java \
	MCSAAnswer.java \
	MCSAQuestion.java \
	NumAnswer.java \
	NumQuestion.java \
	SAAnswer.java \
	SAQuestion.java \
	ExamTaker.java

CLASSES3 = \
	Question.java \
	Answer.java \
	MCAnswer.java \
	MCMAQuestion.java \
	MCQuestion.java \
	MCSAAnswer.java \
	MCSAQuestion.java \
	NumAnswer.java \
	NumQuestion.java \
	SAAnswer.java \
	SAQuestion.java \
	ExamGrader.java



default: classes

examTaker: classes2

examGrader: classes3

classes: $(CLASSES:.java=.class)

classes2: $(CLASSES2:.java=.class)

classes3: $(CLASSES3:.java=.class)

clean:
	rm -f *.class




