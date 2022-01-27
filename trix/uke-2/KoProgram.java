
import java.util.Scanner;

public class KoProgram {
    public static void main(String[] args){
        KoSystem ko = new KoSystem();
        Scanner input = new Scanner(System.in);
        meny();
        int valg = Integer.parseInt(input.nextLine());

        while (valg != 4){
            if (valg == 1){
                ko.trekkKoLapp();
            }else if(valg == 2){
                ko.betjenKunde();
            }else if(valg == 3){
                ko.printKunderIKo();
            }else{
                System.out.println("Ugyldig valg. Du tastet "+valg);
            }
            meny();
            valg = Integer.parseInt(input.nextLine());
        }
        System.out.println("Programmet avsluttes.");
        input.close();
    }

    public static void meny(){
        System.out.println("\n**MENY FOR BILLETTSYSTEM");
        System.out.println("1 - Trekk ny kølapp");
        System.out.println("2 - Betjen kunde");
        System.out.println("3 - Print antall kunder i kø");
        System.out.println("4 - Avslutt\n");
    }
}
