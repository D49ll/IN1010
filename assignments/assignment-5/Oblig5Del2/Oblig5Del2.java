import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2 {
    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        Monitor1 monitor = new Monitor1();
        CountDownLatch myBarrier;
        
        //Oppgir path til mappen
        String path = ""; 
        if (args.length > 0){
            path = args[0];
        }else{
            System.out.println("No path specified");
            System.exit(1);
        }

        //Oppretter et array av File-objekter og sorterer
        //Antar at metadata alltid vil bli sortert til nederst posisjon
        File[] filesInDirectory = new File(path).listFiles();
        if (filesInDirectory == null){
            System.out.println("Files not imported. Please check pathname");
            System.exit(1);
        }else{
            try{
                Arrays.sort(filesInDirectory);
                myBarrier = new CountDownLatch(filesInDirectory.length-1);
                
                //For hver fil opprettes det et hashmap med sekvenser.
                for(int i = 0; i < filesInDirectory.length-1; i++){
                    new Thread(new LeseTrad(filesInDirectory[i],monitor,myBarrier,i)).start();
                    System.out.println("Thread "+i+" started");
                }

                //Venter på at alle threads skal bli ferdig
                myBarrier.await();
        
                System.out.println("Fra filer i \""+path+"\" ble det lest inn subsekvenser fra totalt "+monitor.size()+" personer.");
                
                //Nå vil vi merge alle hashmappene
                HashMap<String,Subsekvens> mergedMap = new HashMap<>();
                mergedMap = SubsekvensRegister.mergeHashMap(monitor.pop(1), monitor.pop(0));
                while(monitor.size()!=0){
                    mergedMap = SubsekvensRegister.mergeHashMap(mergedMap, monitor.pop(0));
                }
        
                //Finner den subsekvensen som forekom flest ganger
                int max = 1; //Alle subsekvensene har minst 1 forekomst
                String key=null;
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
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
