import java.util.HashMap;
import java.util.Scanner;

public class Telefonbok {
    public static void main(String[] args){
        HashMap<String,String> katalog = new HashMap<> ();

        katalog.put("Arne","22334455");
        katalog.put("Lisa","95959595");
        katalog.put("Jonas","97959795");
        katalog.put("Peder","12345678");

        Scanner input = new Scanner(System.in);
        
        int cancel = 0;

        while (cancel != 1){
            System.out.println("Velg 0 for å søke.\nVelg 1 for å avslutte.");
            cancel = Integer.parseInt(input.nextLine());

            if (cancel == 0){
                System.out.print("Søk med navn: ");
                String key = input.nextLine();

                if (katalog.containsKey(key)){
                    System.out.println("Navn: "+key+"\nTlf: "+katalog.get(key));

                }else{
                    System.out.println("Fant ikke "+key+" i");
                }
            } 
        }
        System.out.println("Oversikt: ");
        for (String key : katalog.keySet()){
            System.out.println("Navn: "+key+"\nTlf: "+katalog.get(key)+"\n");
        }

        input.close();

    }
}
