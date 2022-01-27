import java.util.Scanner;
import java.io.File;

public class Linjenummer {
    public static void main(String[] args) throws Exception{
        Scanner file = new Scanner(new File("linjenummer.txt"));
        int i = 1;
        while(file.hasNextLine()){
            System.out.println("# "+i+": "+file.nextLine());
            i++;
        }
        System.out.println();
        
        Scanner file2 = new Scanner(new File("linjenummer.txt"));
        for(int j = 1; file2.hasNextLine(); j++){
            System.out.println("# "+j+": "+file2.nextLine());

        }
    }
}
