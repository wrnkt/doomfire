JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: DoomFire.class

rebuild:
	make clean
	make

DoomFire.class: DoomFire.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) DoomFire.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) DoomFire

clean:
	$(RM) $(CLASSDIR)/*.class
