import java.util.Scanner;
import java.io.File;

public class FraFil {
    public static void main(String[] args) throws Exception{ //Dersom filen ikke finnes kastes feilen/unntaket.
        Scanner file = new Scanner(new File("tekst.txt"));

        while(file.hasNextLine()){ //Leter helt til EOF
            System.out.println(file.nextLine());
        } 
    }
}
