import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

public class Oblig5Del1 {
    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        SubsekvensRegister alleSekvenser = new SubsekvensRegister();
        
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
            Arrays.sort(filesInDirectory);
            
            //For hver fil opprettes det et hashmap med sekvenser.
            for(int i = 0; i < filesInDirectory.length-1; i++){
                try{
                    alleSekvenser.push(SubsekvensRegister.readFile(filesInDirectory[i]));
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
    
            System.out.println("Det ble lest inn subsekvenser fra "+alleSekvenser.size()+" personer.");
    
            //Nå vil vi merge alle hashmappene

            HashMap<String,Subsekvens> mergedMap = new HashMap<>();

            mergedMap = SubsekvensRegister.mergeHashMap(alleSekvenser.pop(1), alleSekvenser.pop(0));
            while(alleSekvenser.size()!=0){
                mergedMap = SubsekvensRegister.mergeHashMap(mergedMap, alleSekvenser.pop(0));
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
    }
}
