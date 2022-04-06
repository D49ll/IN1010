import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;


public class SubsekvensRegister {
    private ArrayList<HashMap<String,Subsekvens>> hashBeholder = new ArrayList<>();

    public void push(HashMap<String,Subsekvens> subsekvenser){
        hashBeholder.add(subsekvenser);
    }

    public int size(){
        return hashBeholder.size();
    }

    public HashMap<String,Subsekvens> pop(int index){
        return hashBeholder.remove(index);
    }

    public HashMap<String,Subsekvens> get(int index){
        return hashBeholder.get(index);
    }
    
    
    
    public static HashMap<String,Subsekvens> readFile(File FILE) throws FileNotFoundException{
        HashMap<String,Subsekvens> map = new HashMap<>();
        
        //Oppretter et scanner objekt fra en fil
        Scanner file = new Scanner(FILE);

        //Sjekker hver linje i filen
        while(file.hasNextLine()){
            String line = file.nextLine();
            if(line.length() < 3) break;
                
            //Sjekker substringer med lengde 3 i hver linje
            for(int i = 0; i < line.length()-2; i++){
                String key = line.substring(i, i+3);
                if(!map.containsKey(key)){
                    Subsekvens val = new Subsekvens(1,key);
                    map.put(key, val);
                    }
            }
        }
        file.close();
        return map;   
    }

    public static HashMap<String,Subsekvens> mergeHashMap(HashMap<String,Subsekvens> map1, HashMap<String,Subsekvens> map2){

        //Kopierer alle elementer i map1 til mergeMap
        // HashMap<String,Subsekvens> mergedMap = new HashMap<>();
        // mergedMap.putAll(map1);

        //Sjekker alle elementer i mergemap mot elementer i map2.
        //Dersom det finnes key-duplikater økes val-variabelen tilsvarende val i map 2
        for(HashMap.Entry<String,Subsekvens> e : map2.entrySet()){
            String key = e.getKey();
            Subsekvens val = e.getValue();
            if(map1.containsKey(key)){
                for(int i = 0; i < val.getAmount(); i++){
                    map1.get(key).increment();
                }
            }else{
                map1.put(key, val);
            }
        }
        return map1;
    }
}
