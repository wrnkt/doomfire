JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: Frame.class FramePrinter.class

rebuild:
	make clean
	make

DoomFire.class: DoomFire.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) DoomFire.java

Frame.class: Frame.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Frame.java

FramePrinter.class: FramePrinter.java Frame.class
	$(JC) $(JCFLAGS) -d $(CLASSDIR) FramePrinter.java

run:
	java -cp $(CLASSDIR) FramePrinter

clean:
	$(RM) $(CLASSDIR)/*.class
