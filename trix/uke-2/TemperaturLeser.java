import java.util.Scanner;
import java.io.File;

public class TemperaturLeser {
    public static void main(String[] args){
        // Bruker heller try-catch for å bedre kunne se feilen
        try {
            Scanner file = new Scanner(new File("temp.txt"));
    
            String[] stringArr = new String[12];
            int i = 0;
            
            
            while(file.hasNextLine()){
                stringArr[i] = file.nextLine();
                i++;
            }
    
            for(int j = 0; j < stringArr.length; j++){
                System.out.println("stringArr["+j+"] = "+stringArr[j]);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
}
