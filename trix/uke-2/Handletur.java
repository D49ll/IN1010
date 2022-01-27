import java.util.Scanner;

public class Handletur{
    public static void main(String[] args){
        int brod = 20;
        int melk = 15;
        int ost = 40;
        int yogurt = 12;
        int total = 0;
        Scanner input = new Scanner(System.in);

        // Interaksjon
        System.out.println("Hei! Velkommen til IFI-butikken");
        System.out.println("Hvor mange brød vil du ha?");
        total += (brod * Integer.parseInt(input.nextLine()));

        System.out.println("Hvor mange melk vil du ha?");
        total += (melk * Integer.parseInt(input.nextLine()));

        System.out.println("Hvor mange ost vil du ha?");
        total += (ost * Integer.parseInt(input.nextLine()));

        System.out.println("Hvor mange yogurt vil du ha?");
        total += (yogurt * Integer.parseInt(input.nextLine()));

        System.out.println("Du skal betale: "+total+"kr.");
    
        input.close();
    }
}
