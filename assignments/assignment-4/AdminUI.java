import java.util.Iterator;
import java.util.Scanner;

public class AdminUI {
    Legesystem legesystem;

    public AdminUI(Legesystem legesystem) {
        this.legesystem = legesystem;
    }

    public void opprettPasient(Scanner scanner) {
        Terminalinput input = new Terminalinput(scanner);

        String navn = input.hentString("Fullt navn", "[a-zA-ZæøåÆØÅ\\s]{2,}");
        String fnr = input.hentString("Fødselsnummer", "[0-9]{11}");

        legesystem.leggTilPasient(new Pasient(navn,fnr));
        System.out.println(navn + " er lagt til i databasen som pasient.");
    }

    public void opprettLege(Scanner scanner) {
        Terminalinput input = new Terminalinput(scanner);

        String navn = input.hentString("Fullt navn", "[a-zA-ZæøåÆØÅ\\s]{2,}");

        legesystem.leggTilLege(new Lege(navn));
        System.out.println(navn + " er lagt til i databasen.");
    }
    
    public void opprettSpesialist(Scanner scanner) {
        Terminalinput input = new Terminalinput(scanner);

        String navn = input.hentString("Fullt navn", "[a-zA-ZæøåÆØÅ\\s]{2,}");
        String kontrollId = input.hentString("Kontrollid", "[a-zA-ZæøåÆØÅ0-9-_]{1,}");

        legesystem.leggTilLege(new Spesialist(navn, kontrollId));

        System.out.println(navn + " er lagt til i databasen som spesialist.");
    }

    public void opprettLegemiddel(Scanner scanner) {
        Terminalinput input = new Terminalinput(scanner);

        final int VANLGI = 1;
        final int VANEDANNENDE = 2;
        final int NARKOTISK = 3;

        System.out.println("Hvilket type legemiddel vil du opprette?");
        System.out.println(VANLGI + ". Vanlig legemiddel");
        System.out.println(VANEDANNENDE + ". Vanedannende legemiddel");
        System.out.println(NARKOTISK + ". Narkotisk legemiddel");
        
        int valg = input.hentInteger("Type", 1, 3);
        
        String navn = input.hentString("Fullt navn", "[a-zA-ZæøåÆØÅ\\s]{2,}");
        int pris = input.hentInteger("Pris", 1, 99999);
        double virkestoff = input.hentDouble("Virkestoff", 1, 99999);

        switch(valg) {
            case VANEDANNENDE:
                int styrke = input.hentInteger("Styrke", 1, 999999);
                legesystem.leggTilLegemiddel(new VanedannendeLegemiddel(navn, pris, virkestoff, styrke));
                break;
            case NARKOTISK:
                int narkotiskStyrke = input.hentInteger("Styrke", 1, 999999);
                legesystem.leggTilLegemiddel(new NarkotiskLegemiddel(navn, pris, virkestoff, narkotiskStyrke));
                break;
            default:
                legesystem.leggTilLegemiddel(new VanligLegemiddel(navn,pris,virkestoff));
        }

        System.out.println("Legemiddelet er lagt til i databasen.");
    }

     public void skrivUtAlleObjekterTilTerminalen() {
        System.out.println("Skriver ut alle objekter i systemet.");
        
        System.out.println("Leger:");
        System.out.println("***");
        Iterator<Lege> legeIt = legesystem.hentAlleLegerSortert().iterator();
        while( legeIt.hasNext() ) {
            System.out.println(legeIt.next());
        }
        
        System.out.println("Pasienter:");
        System.out.println("***");
        Iterator<Pasient> pasientIt = legesystem.listAllePasienter();
        while( pasientIt.hasNext() ) {
            System.out.println(pasientIt.next());
        }
        
        System.out.println("Legemidler:");
        System.out.println("***");
        Iterator<Legemiddel> legemiddelIt = legesystem.listAlleLegemidler();
        while( legemiddelIt.hasNext() ) {
            System.out.println(legemiddelIt.next());
        }
        
        System.out.println("Resepter:");
        System.out.println("***");
        
        
        Iterator<Resept> reseptIt = legesystem.hentAlleResepterSortert().iterator();
        while( reseptIt.hasNext() ) {
            System.out.println(reseptIt.next());
        }
     }

     public Legesystem lesInnLegesystemFraFil(Scanner scanner) {  
        System.out.print("Skriv inn navnet til filen: ");
        String filnavn = scanner.nextLine();
        
        try {
            Legesystem blanktSystem = new Legesystem();
            FilDataLag fd = new FilDataLag(blanktSystem);
            fd.lesFraFil(filnavn);
            
            System.out.println("Data er importert fra fil.");

            return blanktSystem;
        } catch(Exception e) {
            System.out.println("Kunne ikke lese fra fil.");
            return null;
        }

        
    }  

     public void skrivTilFil(Scanner scanner) {
            Terminalinput t = new Terminalinput(scanner);
            String filnavn = t.hentString("Filnavn", "[a-zA-Z0-9._]+");

            try {
                FilDataLag fd = new FilDataLag(legesystem);
                fd.skrivTilFil(filnavn);
            } catch(Exception e) {
                System.out.println("Kunne ikke lese fra fil.");
                return;
            }

            System.out.println("Data er skrevet til fil.");
     }
}