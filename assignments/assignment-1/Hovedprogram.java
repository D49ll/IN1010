import java.util.Scanner;
import java.io.File;

public class Hovedprogram {
    public static void main(String[] args){
        try {
            //Fil spesifiseres fra terminal. 
            //Dersom ingen fil blir oppgitt brukes dataklynge2.txt.
            String filename;
            if (args.length > 0){
                filename = args[0];
            }else{
                filename = "dataklynge2.txt";
            }

            //Oppretter en ny dataklynge
            String[] dataklyngeNavn = filename.split("\\.");
            Dataklynge k = new Dataklynge(dataklyngeNavn[0]);

            //Leser fra fil
            Scanner file = new Scanner(new File(filename));

            while(file.hasNextLine()){
                //Leser linje for linje
                String line = file.nextLine();

                //Finner nodeparametere
                String[] params = line.split(" ");

                //Konverterer parametere fra String til int
                int numNodes = Integer.parseInt(params[0]);
                int numProc = Integer.parseInt(params[1]);
                int numGB = Integer.parseInt(params[2]);

                //Oppretter og setter inn noder inn i dataklynge
                //Dersom parameterne er ugyldig kastes et unntak ig program termineres
                if(numProc > 16 || numGB > (4*1024) && params.length > 3){
                    throw new InvalidNodeException("Invalid node, please review "+filename+".\nHave a nice day.");
                }else{
                    k.insertNode(new Node(numProc,numGB),numNodes);
                }

            }

            //Skriver ut info om dataklynge
            int[] paakrevdMinne = {128,512,1024};            
            
            System.out.println("\n********Dataklynge info********\n");
            System.out.println("Navn: "+k.getName());
            for(int minne : paakrevdMinne){
                k.noderMedNokMinne(minne);
            }
            System.out.println();
            k.antProsessorer();
            k.printRacks();
            System.out.println("\n******************************");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
