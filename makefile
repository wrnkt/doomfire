JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: Frame.class

rebuild:
	make clean
	make

DoomFire.class: DoomFire.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) DoomFire.java

Frame.class: Frame.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Frame.java

run:
	java -cp $(CLASSDIR) Frame

clean:
	$(RM) $(CLASSDIR)/*.class
