import java.util.ArrayList;

public class Hovedprogram {
    public static void main(String[] args){
        //Legemidler instanser
        ArrayList<Legemiddel> legemidler = new ArrayList<Legemiddel>();
        legemidler.add(new Narkotisk("Morfin", 120, 10.4, 8));
        legemidler.add(new Vanedannende("Heroin", 70, 11.2, 9));
        legemidler.add(new Vanlig("Zyrtec", 40, 6.5));
        
        System.out.println("Printer informasjon om Legemidler-objekter:");
        for(Legemiddel legemiddel : legemidler){
            System.out.println(legemiddel.toString());
        }

        //Lege instanser
        ArrayList<Lege> leger = new ArrayList<>();
        leger.add(new Lege("Balle"));
        leger.add(new Spesialist("Nilsen", "1234"));

        System.out.println("\nPrinter informasjon om Leger-objekter:");
        for(Lege lege : leger){
            System.out.println(lege.toString());
        }
      
        //Resept instanser
        ArrayList<Resept> resepter = new ArrayList<>();
        resepter.add(new HvitResept(legemidler.get(0), leger.get(0), 1000, 6));
        resepter.add(new BlaaResept(legemidler.get(0), leger.get(0), 2000, 2));
        resepter.add(new PResept(legemidler.get(2), leger.get(0), 3000, 4));
        resepter.add(new MilResept(legemidler.get(1), leger.get(1), 4000));

        System.out.println("\nPrinter informasjon om Resept-objekter:");
        for(Resept resept : resepter){
            System.out.println(resept.toString());
        }
    }
}
