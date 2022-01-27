import java.util.ArrayList;

public class brukBrev{
    public static void main(String[] args){
        Brev brevet = new Brev("Per Askeladd", "Espen Askeladd");
        brevet.skrivLinje("Hvordan har du det?");
        brevet.skrivLinje("Jeg har det bare bra!");
        brevet.lesBrev();
    }
}

class Brev {
    private String avsender, mottaker;
    private ArrayList <String> linjer = new ArrayList <> ();

    public Brev(String a, String m){
        this.avsender = a;
        this.mottaker = m;
    }

    public void skrivLinje(String linje){
        linjer.add(linje);
    }

    public void lesBrev(){
        System.out.println("Hei, "+this.mottaker);
        for (String linje : this.linjer){
            System.out.println(linje);
        }
        System.out.println("Hilsen fra,");
        System.out.println(this.avsender);
    }
}
