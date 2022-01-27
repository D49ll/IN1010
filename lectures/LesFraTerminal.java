import java.util.Scanner;

class LesFraTerminal {
    public static void main(String[] args){
        int alder;
        String navn, adresse;
        Scanner minInn = new Scanner(System.in);

        System.out.print("Skriv adressen din: ");
        System.out.flush(); //Flush forsikrer at vi faktisk skriver til terminal
        adresse = minInn.nextLine();

        System.out.print("Skriv fornavnet ditt: ");
        System.out.flush(); 
        navn = minInn.next();
        
        System.out.print("Skriv alderen din: ");
        alder = minInn.nextInt();

        System.out.println(navn+", du bor i "+adresse+" og er "+alder+" år.");

        minInn.close();
    }
}   