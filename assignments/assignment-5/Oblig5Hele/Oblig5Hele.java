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
            ArrayList<File> sFilesInDirectory = new ArrayList<>();
            ArrayList<File> hFilesInDirectory = new ArrayList<>();
            Scanner metadata = new Scanner(filesInDirectory[numFiles-1]);
            for(int i = 0; i < numFiles-1; i++){
                String line = metadata.nextLine();
                String[] words = line.split(",");
                if(words[1].equals("True")){
                    sFilesInDirectory.add(filesInDirectory[i]);
                }else{
                    hFilesInDirectory.add(filesInDirectory[i]);
                }
            }
            metadata.close();

            //Variabler til pasienter som har hatt viruset
            int sNumFiles = sFilesInDirectory.size();
            CountDownLatch sInBarrier = new CountDownLatch(sNumFiles);
            CountDownLatch sOuBarrier = new CountDownLatch(sNumFiles-1);
            Monitor2 sMonitor = new Monitor2();
            
            //Variabler til pasienter som ikke har hatt viruset
            int hNumFiles = hFilesInDirectory.size();
            CountDownLatch hInBarrier = new CountDownLatch(hNumFiles);
            CountDownLatch hOuBarrier = new CountDownLatch(hNumFiles-1);
            Monitor2 hMonitor = new Monitor2();

            //Initierer alle LeseTrader for begge grupper
            System.out.println("-------------------");
            System.out.println("Reading files from folder \""+path+"\":");
            System.out.println("-------------------");
            initiateLeseTrads(sMonitor, sFilesInDirectory, sInBarrier);
            initiateLeseTrads(hMonitor, hFilesInDirectory, hInBarrier);
            waiting(sInBarrier, hInBarrier);
            System.out.println("\nLeseTrad's finished");
            System.out.println("-------------------\n");

            //Initerer max 8 FletteTrader per gruppe
            System.out.println("-------------------");
            System.out.println("Merging files:");
            System.out.println("-------------------");
            initiateFletteTrads(sMonitor, sFilesInDirectory, sOuBarrier, MAXTHREADS);
            initiateFletteTrads(hMonitor, hFilesInDirectory, hOuBarrier, MAXTHREADS);
            waiting(sOuBarrier, hOuBarrier);
            System.out.println("\nFletteTrads's finished");
            System.out.println("----------------------\n");

            //Finner de mest dominante subsekvensene
            System.out.println("------------------------------------------------------------");                
            System.out.println("Subsekvenser med differanse mer enn "+DOMINANTFREQ+" ganger");
            System.out.println("------------------------------------------------------------");                
            
            findDominant(DOMINANTFREQ, sMonitor, hMonitor);
            System.out.println("------------------------------------------------------------\n");                
            
            System.out.println("Programmet brukte "+((System.currentTimeMillis()-startTime))/1000F+"s");
        }
    }
    
    public static void initiateLeseTrads(Monitor2 monitor, ArrayList<File> files, CountDownLatch myBarrier){
        int i;
        for(i=0; i < files.size(); i++){
            new Thread(new LeseTrad(files.get(i),monitor,myBarrier)).start();
        }
        System.out.println("Started "+i+" LeseTrad-threads.. ");
    }

    public static void initiateFletteTrads(Monitor2 monitor, ArrayList<File> files, CountDownLatch myBarrier, int MAXTHREADS){
        int i;
        //Dersom antall filer er mindre enn MAXTHREADS er det ikke behov for å opprette 8 Threads.
        //Trenger (antall filer - 1) for å flette.
        if(files.size()-1 < MAXTHREADS){
            MAXTHREADS = files.size()-1;
        }
        for(i = 0; i < MAXTHREADS; i++){
            new Thread(new FletteTrad(monitor,myBarrier)).start();
        }
        System.out.println("Started "+i+" FletteTrad-threads.. ");
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

    public static void findDominant(int freq, Monitor2 sMonitor, Monitor2 hMonitor){
        try{
            HashMap<String,Integer> dominant = new HashMap<>();
            HashMap<String,Subsekvens> healty = hMonitor.get(0);
            for(HashMap.Entry<String,Subsekvens> m : sMonitor.get(0).entrySet()){
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