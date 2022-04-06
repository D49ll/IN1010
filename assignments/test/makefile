JFLAGS=-g
JC=javac
JVM=java 
FILE=

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES= \
	Resept.java BlaaResept.java HvitResept.java MilitaerResept.java PrevensjonsResept.java UlovligUtskrift.java \
	Legemiddel.java VanligLegemiddel.java NarkotiskLegemiddel.java VanedannendeLegemiddel.java \
	Lege.java Spesialist.java Godkjenningsfritak.java \
	Pasient.java \
	Liste.java UgyldigListeindeks.java Lenkeliste.java IndeksertListe.java  Prioritetskoe.java \
	Terminalinput.java \
	FilDataLag.java Legesystem.java \
	LegesystemUI.java AdminUI.java StatistikkUI.java \
	Hovedprogram.java

default: classes Tekstoppgaver.pdf Innlevering.zip
classes: $(CLASSES:.java=.class)

Tekstoppgaver.pdf: README.md
	pandoc README.md -o Tekstoppgaver.pdf

Innlevering.zip: $(CLASSES) Tekstoppgaver.pdf
	zip -r Innlevering.zip $(CLASSES) Tekstoppgaver.pdf > zip.log