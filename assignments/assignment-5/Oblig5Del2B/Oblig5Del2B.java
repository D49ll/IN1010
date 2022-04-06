import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2B {
    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        int MAXTHREADS = 8;

        //Oppgir path til mappen
        String path = ""; 
        if (args.length > 0){
            path = args[0];
        }else{
            System.out.println("No path specified");
            System.exit(1);
        }

        //Oppretter et array av File-objekter og sorterer
        File[] filesInDirectory = new File(path).listFiles();
        if (filesInDirectory == null){
            System.out.println("Files not imported. Please check pathname");
            System.exit(1);
        }else{
            try{
                //Antar at metadata alltid vil bli sortert til nederst posisjon
                Arrays.sort(filesInDirectory);
                
                ArrayList<Thread> mergingThreads = new ArrayList<>();
                ArrayList<Thread> readingThreads = new ArrayList<>();
                
                CountDownLatch inBarrier = new CountDownLatch(filesInDirectory.length-1);
                CountDownLatch ouBarrier = new CountDownLatch(filesInDirectory.length-2);
                
                Monitor2 monitor = new Monitor2();
                int i;

                //For hver fil opprettes det et hashmap med sekvenser.
                for(i=0; i < filesInDirectory.length-1; i++){
                    readingThreads.add(new Thread(new LeseTrad(filesInDirectory[i],monitor,inBarrier,i)));
                    readingThreads.get(i).start();
                }
                System.out.print("Started "+i+" LeseTrad-threads.. ");
                inBarrier.await();
                System.out.println("LeseTrad's finished");

                
                //Det opprettes max 8 FletteTrad-objekter.
                //Dersom det finnes mindre enn 8 filer trenger
                //man kun (antallfiler-2) Flettetråder.
                if(filesInDirectory.length-2 < MAXTHREADS){
                    MAXTHREADS = filesInDirectory.length-2;
                }
                for(i = 0; i < MAXTHREADS; i++){
                    mergingThreads.add(new Thread(new FletteTrad(monitor,ouBarrier)));
                    mergingThreads.get(i).start();
                }
                System.out.print("Started "+i+" FletteTrad-threads.. ");
                               
                // System.out.println("Fra filer i \""+path+"\" ble det lest inn subsekvenser fra totalt "+monitor.size()+" personer.");
                
                ouBarrier.await();
                System.out.println("FletteTrad's finished");
                
                
                if(monitor.size()>1){
                    System.out.println("HashMaps:"+monitor.size()+"! Should be size = 1.");
                }

                //Finner den subsekvensen som forekom flest ganger
                int max = 1; //Alle subsekvensene har minst 1 forekomst
                String key=null;
                HashMap<String,Subsekvens> mergedMap = monitor.get(0);
                for(HashMap.Entry<String,Subsekvens> e : mergedMap.entrySet()){
                    if(e.getValue().getAmount() > max){
                        max = e.getValue().getAmount();
                        key = e.getKey();
                    }
                }
                if(key!=null){
                    System.out.println("Subsekvensen med flest forekomster er: "+mergedMap.get(key));
                }else{
                    System.out.println("Ingen subsekvenser forekom flere ganger");
                }

                System.out.println("Tid brukt: "+((System.currentTimeMillis()-startTime))/1000F+"s");
                
                //Sjekker at alle tråder er terminert
                for(i = 0; i < readingThreads.size(); i++){
                    if(readingThreads.get(i).isAlive()){
                        System.out.println("LeseTrad["+i+"] is still alive");
                    }
                }
                for(i = 0; i < mergingThreads.size(); i++){
                    if(mergingThreads.get(i).isAlive()){
                        System.out.println("FletteTrad["+i+"] is still alive");
                    }
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
