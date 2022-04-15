# Table of contents
- [Table of contents](#table-of-contents)
- [GUI "Graphical User Interface"](#gui-graphical-user-interface)
  - [Fordeler](#fordeler)
  - [Ulemper](#ulemper)
- [Et minimalt GUI-program](#et-minimalt-gui-program)
- [Kjøring](#kjøring)
- [GUI: Bokser og slikt](#gui-bokser-og-slikt)
  - [Opprette trykknapper](#opprette-trykknapper)
  - [Tekst](#tekst)
  - [Filvelger](#filvelger)
  - [Layout](#layout)
- [Et quizprogram](#et-quizprogram)
# GUI "Graphical User Interface"
- Et godt nettkurs som introduserer bruk av GUI, laget av [Oracle](https://docs.oracle.com/javase/tutorial/uiswing/ "nettkurs fra oracle").
- IN1010 bruker AWT+Swing i kurset fordi det er en del av standard Java
- JavaFX er mer moderne og avansert

## Fordeler
- Mer intuitivt å bruke
- Færre muligheter for brukerfeil
- Visuelt mer tiltalende

## Ulemper
- Mer komplisert å programmere
- Mange ulike GUI-biblioteker å velge mellom
- Svært få biblioteker som fungerer på tvers av Linux, Mac og Windows

# Et minimalt GUI-program
Dette programmet vil gi et standard vindu med minimer-, maksimer- og lukk-knapper
```java
//Disse er all import vi trenger i IN1010
import java.awt.*;
import java.awt.event.*; //event er ingen klasse, men en pakke. Derfor må den implisitt importeres.
import javax.swing.*;

class Mini{
    public static void main(String[] args){
        //Deklarerer selve vinduet, "Xxx" er navnet som står i vinduet
        JFrame vindu = new JFrame("Xxx");
        //Vi må bestemme hva som skjer når vi trykker på lukk-knappen
        //Dersom du ikke gjør det vil tråden fortsatt leve etter lukking
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Oppretter tegneflaten som vi kan jobbe på og legger inn i vinduet.
        JPanel panel = new JPanel();
        vindu.add(panel);

        //Alt vi ønsket å ha inn i vinduet skal dyttes pent sammen. Det gjøres med .pack(). Deretter gjør vi vinduet synlig.
        vindu.pack();
        vindu.setVisible(true);
    }
}
```

# Kjøring
Grafiske programmer trenger mer støtte ved kjøring fra systemet enn tekstorienterte programmer. Det enkleste er å installere java på egen maskin.

# GUI: Bokser og slikt
På tegneflaten (JPanel-objekt) kan vi plassere ulike bokser:
- tekst (JLabel)
- trykknapper(JButton)
- tekstfelt (JTextField)
- tegneflater (JPanel), ny å mindre tegneflate på tegneflaten
- ...

```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Hallo{
    public static void main(String[] arg){
        JFrame vindu = new JFrame("Hei");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        vindu.add(panel);

        //Vi ber java om brukernavnet til den som kjører programmet
        String bruker = System.getProperty("user.name");

        //Oppretter en ny JLabel og legger den til på vårt panel.
        JLabel hilsen = new JLabel("Hallo, "+bruker+"!");
        panel.add(hilsen)

        vindu.pack();
        vindu.setVisible(true);
    }
}
```
## Opprette trykknapper
Den vanligste formen for interaksjon med en bruker er trykknapper. 
1. Opprett trykknappen (JButton)
2. sette den på en tegneflate (JPanel)
3. skrive kode som skal utføres ved et trykk (ActionListener)
4. koble koden til trykknappen (addActionListener)

Lager et testprogram som stopper av seg selv:

```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Exit{
    public static void main(String[] arg){
        //Standard
        JFrame vindu = new JFrame("Hei");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);

        //Oppretter knappen
        JButton exitKnapp = new JButton("Exit");

        //Oppretter en ny klasse som implementerer et interface ActionListener
        //Det eneste som kreves er at vi oppretter en ActionPerformed. 
        //(ActionEvent e) må være med som parameter, men kan egentlig ignoreres
        //Deretter må programmere hva vi ønsker at knappen skal utføre.
        class Stopper implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                System.exit(0);
            }
        }

        //Kobler sammen knapp og kode
        exitKnapp.addActionListener(new Stopper());
        //Plasserer knappen på tegneflaten
        panel.add(exitKnapp);

        //Standard
        vindu.pack();
        vindu.setVisible(true);
    }
}
```
## Tekst
Ofte er det ønskelig å motta tekst fra brukeren. Til dette brukes JTextField der man angir initiell tekst og bredde. Brukeren kan redigere denne teksten, og programmet kan lese og skrive med metodene **getText** og **setText**.

```java
JTextField tekst = new JTextField("Skriv en tekst:", 30);
```

## Filvelger

Oppretter en fil, hovedstad-belgia.txt, på følgende format:
```
Paris
Amsterdam
*Brussel
Luxembourg
```
Deretter må vi lese en fil. Den åpner en mappestruktur der man kan velge filen.
```java
//Oppretter filvelger
JFileChooser velger = new JFileChooser();

//Åpner dialogvindu
int resultat = velger.showOpenDialog(null);

//Dersom alt går bra er resultat lik denne konstanten
if(resultat == JFileChooser.APPROVE_OPTION)
    File f = velger.getSelectedFile();
else
    //Avslutter programmet
```

## Layout
Hvordan plassere elementer, standard er **FlowLayout**. Den er fleksibel og bytter linje ved behov. Noen ganger fortrekker man **GridLayour**, som er et rutenett. I IN1010 skal vi kun bruke FlowLayout og rutenett.

```java
//FlowLayout
JPanel flyt = new JPanel();
flyt.add(new JButton("Valg 1"));
flyt.add(new JButton("Valg 2"));
flyt.add(new JButton("Valg 3"));
flyt.add(new JButton("Valg 4"));
panel.add(flyt)

//Rutenett
JPanel ruter = new JPanel();
ruter.setLayout(new GridLayout(2,2)); //Angir rader og kolonner
ruter.add(new JButton("Knapp 1"));
ruter.add(new JButton("Knapp 2"));
ruter.add(new JButton("Knapp 3"));
ruter.add(new JButton("Knapp 4"));
panel.add(ruter)
```
Produserer følgende:
<img src="img/Layout.png" width="500">

# Et quizprogram
1. Programmet ber brukeren om navnet på en oppgavefil
2. Programmet leser filen og oppretter et GUI-vindu med spørsmålet og 4 trykknapper med svar-alternativer plassert i en 2x3 mønster
3. Hvis brukerer velger korrekt alternativ endres teksten på trykknappen til **OK**
4. Ved feil blir teksten på trykknappen forandret til **Nei**

Filen er definert på følgende måte:
```
Paris
Amsterdam
*Brussel
Luxembourg
```
Hele programmet:
```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.Scanner;

class Quiz{
    public static void main(String[] arg){
        //Henter fil
       JFileChooser velger = new JFileChooser();
       int res = velger.showOpenDialog(null);
       if(res != JFileChooser.APPROVE_OPTION){
           System.exit(1);
       }
       File f = velger.getSelectedFile();
       Scanner leser = null;

       try{
           leser = new Scanner(f);
       }catch(FileNotFoundException e){
           System.exit(1);
       }

       //Oppretter GUI-vindu
        JFrame vindu = new JFrame("Hei");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);

        //Leser spørsmålet
        JLabel question = new JLabel(leser.nextLine());
        panel.add(question); 

        //Oppretter 4 alternativerknapper som plasseres på sin egen 2x2-flate
        static Svar[] svar = new Svar(4); //Se klassen nedenfor

        JPanel alternativer = new JPanel();
        alternativer.setLayout(new GridLayout(2,2));
        for(int i = 0; i < 4; i++){
            String s = leser.nextLine();
            if(s.startsWith("*"))
                svar[i] = new Svar(s.substring(1),true);
            else
                svar[i] = new Svar(s, false);
            svar[i].initGUI();
            alternativer.add(svar[i]);
        }
        panel.add(alternativer);


        //Pakker alt sammen og viser vinduet
        vindu.pack();
        vindu.setVisible(true);
    }
}
```
Oppretter en egen klasse som binder sammen svar og knapper.
```java
class Svar extends JButton{
    String svar;
    boolean riktig;

    Svar(String s, boolean r){
        super(s);
        svar = s;
        riktig = r;
    }

    class Velger implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            if(riktig)
                setText("OK");
            else
                setText("NEI");
        }
    }

    //Det er lurt å installere GUI i en egen metode som kalles etter selve objekter er laget
    void initGUI(){
        addActionListiner(new Velger());
    }
}
```