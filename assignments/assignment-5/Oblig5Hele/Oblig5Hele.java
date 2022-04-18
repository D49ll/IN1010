import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Hele{
    public static void main(String args[]) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();
        int MAXTHREADS = 8;
        int DOMINANTFREQ = 7;
        ArrayList<Thread> sickLeseT, sickFletteT, healtyLeseT, healtyFletteT;
        
        //Oppgir path til mappen
        String path = ""; 
        if (args.length > 0){
            path = args[0];
        }else{
            System.out.println("No path specified");
            System.exit(1);
        }

        //Oppretter et array av File-objekter
        File[] filesInDirectory = new File(path).listFiles();
        if (filesInDirectory == null){
            System.out.println("Files not imported. Please check pathname");
            System.exit(1);
        }else{
            //Antar at metadata alltid vil bli sortert til nederst posisjon
            Arrays.sort(filesInDirectory);
            int numFiles = filesInDirectory.length;

            //Sorterer personer basert på tidligere sykdom
            ArrayList<File> sickFiles = new ArrayList<>();
            ArrayList<File> healtyFiles = new ArrayList<>();
            Scanner metadata = new Scanner(filesInDirectory[numFiles-1]);
            
            for(int i = 0; i < numFiles-1; i++){
                String line = metadata.nextLine();
                String[] words = line.split(",");
                if(words[1].equals("True")){
                    sickFiles.add(filesInDirectory[i]);
                }else{
                    healtyFiles.add(filesInDirectory[i]);
                }
            }
            metadata.close();

            //Variabler til pasienter som har hatt viruset
            int sickNumFiles = sickFiles.size();
            CountDownLatch sickLeseBarriere = new CountDownLatch(sickNumFiles);
            CountDownLatch sickFletteBarriere = new CountDownLatch(sickNumFiles-1);
            Monitor2 sickMonitor = new Monitor2(sickFletteBarriere);
            
            //Variabler til pasienter som ikke har hatt viruset
            int healtyNumFiles = healtyFiles.size();
            CountDownLatch healtyLeseBarriere = new CountDownLatch(healtyNumFiles);
            CountDownLatch healtyFletteBarriere = new CountDownLatch(healtyNumFiles-1);
            Monitor2 healtyMonitor = new Monitor2(healtyFletteBarriere);

            //Initierer alle LeseTrader for begge grupper
            System.out.println("Reading files from folder \""+path+"\":");
            sickLeseT = initiateLeseTrads(sickMonitor, sickFiles, sickLeseBarriere);
            healtyLeseT = initiateLeseTrads(healtyMonitor, healtyFiles, healtyLeseBarriere);
    
            //Initerer FletteTrader, max 8 per gruppe
            System.out.println("\nMerging files:");
            sickFletteT = initiateFletteTrads(sickMonitor, sickFiles, sickFletteBarriere, MAXTHREADS);
            healtyFletteT = initiateFletteTrads(healtyMonitor, healtyFiles, healtyFletteBarriere, MAXTHREADS);
            
            //Venter på at trådene skal bli ferdig.
            System.out.println("\nWaiting on threads to finish:");
            waiting(sickLeseBarriere, healtyLeseBarriere);
            System.out.println("LeseTrad's finished");
            waiting(sickFletteBarriere, healtyFletteBarriere);
            System.out.println("FletteTrads's finished");

            //Finner de mest dominante subsekvensene
            System.out.println("\nSubsekvenser med differanse mer enn "+DOMINANTFREQ+" ganger");
            System.out.println("------------------------------------------------------------");                
            findDominant(DOMINANTFREQ, sickMonitor, healtyMonitor);
            System.out.println("------------------------------------------------------------\n");                
            
            //Sjekker om det enda finnes levende tråder.
            isAlive(sickLeseT, "Sick Lesetråd");
            isAlive(healtyLeseT, "Healty Lesetråd");
            isAlive(sickFletteT, "Sick Flettetråd");
            isAlive(healtyFletteT, "Healty Flettetråd");
            System.out.println("Programmet brukte "+((System.currentTimeMillis()-startTime))/1000F+"s");
        }
    }

    private static void isAlive(ArrayList<Thread> threads, String msg){
        for(int i = 0; i < threads.size(); i++){
            if(threads.get(i).isAlive()){
                System.out.println(msg+"["+i+"] is still alive");     
            }
        }
    }
    
    public static ArrayList<Thread> initiateLeseTrads(Monitor2 monitor, ArrayList<File> files, CountDownLatch myBarrier){
        int i;
        ArrayList<Thread> threads = new ArrayList<>();

        for(i=0; i < files.size(); i++){
            threads.add(new Thread(new LeseTrad(files.get(i),monitor,myBarrier)));
            threads.get(i).start();
        }
        System.out.println("Started "+i+" LeseTrad-threads.. ");
        return threads;
    }

    public static ArrayList<Thread> initiateFletteTrads(Monitor2 monitor, ArrayList<File> files, CountDownLatch myBarrier, int MAXTHREADS){
        int i;
        ArrayList<Thread> threads = new ArrayList<>();

        // Dersom antall filer er mindre enn MAXTHREADS er det ikke behov for å opprette 8 Threads.
        // Trenger (antall filer - 1) for å flette.
        if(files.size()-1 < MAXTHREADS){
            MAXTHREADS = files.size()-1;
        }
        for(i = 0; i < MAXTHREADS; i++){
            threads.add(new Thread(new FletteTrad(monitor,myBarrier,MAXTHREADS)));
            threads.get(i).start();
        }
        System.out.println("Started "+i+" FletteTrad-threads.. ");
        return threads;
    }

    public static void waiting(CountDownLatch sBarrier, CountDownLatch hBarrier){
        //Venter til CountDown() tilhørende begge barrierer er 0.
        try{
            sBarrier.await();
            hBarrier.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void findDominant(int freq, Monitor2 sickMonitor, Monitor2 healtyMonitor){
        try{
            HashMap<String,Integer> dominant = new HashMap<>();
            HashMap<String,Subsekvens> healty = healtyMonitor.get(0);
            
            for(HashMap.Entry<String,Subsekvens> m : sickMonitor.get(0).entrySet()){
                String key = m.getKey();
                Subsekvens val = m.getValue();
                
                if(healty.containsKey(key)){
                    int diff = val.getAmount() - healty.get(key).getAmount();
                    if(diff>=freq){
                        dominant.put(key,diff);
                    }
                }
                else{
                    if(val.getAmount()>=freq){
                        dominant.put(key,val.getAmount());
                    }
                }
            }
            if(dominant.size()>0){
                int maxVal = 0;
                String maxKey = "";
                
                for(HashMap.Entry<String,Integer> m:dominant.entrySet()){
                    String key = m.getKey();
                    int val = m.getValue();
                    System.out.println(key+" forekommer "+val+" flere ganger i personer som har hatt viruset.");
                    if(maxVal<val){
                        maxVal = val;
                        maxKey = key;
                    }
                }
                System.out.println("\nDen mest dominerende subsekvensen er "+maxKey+" med "+maxVal+" antall forekomster.");
            }
            else{
                System.out.println("\nIngen subsekvenser i forekommer mer enn "+freq+" ganger.");
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}