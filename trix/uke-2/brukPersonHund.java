import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class brukPersonHund {
    public static void main(String[] args) throws Exception{
        File file = new File("navn.txt");

        try {
            Scanner fil = new Scanner(new File("navn.txt"));
        
            ArrayList <Hund> hunder = new ArrayList<>();
            ArrayList <Person> personer = new ArrayList<>();
        
            while (fil.hasNextLine()){
                String linje = fil.nextLine();
                String[] temp = linje.split(" ", 2);
                if (temp[0].equals("P")){
                    personer.add(new Person(temp[1]));
                }else{
                    hunder.add(new Hund(temp[1]));
                }
            }
            
            System.out.println("Personer fra fil:");
            for(int i = 0; i < personer.size(); i++){
                System.out.println((personer.get(i)).getName());
            }

            System.out.println("\nHunder fra fil:");
            for(Hund hund : hunder){
                System.out.println(hund.getName());
            }

        } catch (Exception e) {
            System.out.println(file+" not found");
        }
    }    
}
