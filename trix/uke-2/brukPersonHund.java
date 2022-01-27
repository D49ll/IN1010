import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class brukPersonHund {
    private ArrayList <Person> personer = new ArrayList<>();
    private ArrayList <Hund> hunder = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        brukPersonHund test = new brukPersonHund();
        Scanner fil = new Scanner(new File("navn.txt"));

        while (fil.hasNextLine()){
            String temp = fil.nextLine();

            if (temp.charAt(0) == 'P'){
                test.personer.add(temp);

            }
        }
    }    
}
