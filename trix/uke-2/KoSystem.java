
import java.util.ArrayList;

public class KoSystem{
    private static int koNummer = 0;
    private ArrayList<KoLapp> kolapper = new ArrayList<>();
    //Lager et nytt objekt av KoLapp, printer ut informasjon om den og legger den til i listen over koLapper.     
    public void trekkKoLapp(){
        koNummer ++;
        KoLapp nyKunde = new KoLapp(koNummer);
        kolapper.add(nyKunde);

        System.out.println("Du har fått tildelt billettnr "+koNummer+".");
        System.out.println("Det står "+(this.antKunder()-1)+" foran deg.");


    }

    //Henter ut, printer ut informasjon og fjerner den forste kolappen i lista. Gir feilmelding dersom ingen kunder staar i ko.
    public void betjenKunde(){
        // Kan alternativt bruke if(kolapper.isEmpty()) - else.
        try {
            KoLapp betjenes = kolapper.remove(0);
            System.out.println("Kunde med billettnr "+(betjenes.hentNummer()+" er betjent."));

        } catch (Exception e) {
            //System.out.println(e);
            System.out.println("Ingen kunder i kø");
        }
    }   

    //Returnerer antall kunder som er i ko.
    public int antKunder(){
        return kolapper.size();
    }

    //Printer alle kunder i ko
    public void printKunderIKo(){
        System.out.println("Det står "+this.antKunder()+" personer i kø.");
    }

}