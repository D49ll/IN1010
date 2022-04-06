import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

class LegesystemUI {
    Legesystem legesystem;

    public LegesystemUI(Legesystem s) {
        legesystem = s;
    }

     /* ****************************************************************
     *
     * Opprett resept
     * 
     * ***************************************************************/

    public void opprettResept(Scanner scanner) {
        Terminalinput input = new Terminalinput(scanner);

        System.out.println("Hvilken resepttype vil du opprette?");
        System.out.println("  1. Vanlig resept");
        System.out.println("  2. Blå resept");
        System.out.println("  3. P-Resept");
        System.out.println("  4. Militærresept");

        int typeResept = input.hentInteger("Type (1-4)", 1, 4);

        Pasient pasient = null;
        while ( true ) {
            String sokestreng = input.hentString("Fnr eller navn", ".+");

            if ( sokestreng.matches("[0-9]{11}") ) {
                pasient = legesystem.finnPasientMedFnr(legesystem.listAllePasienter(), sokestreng);
                if ( pasient == null ) {
                    System.out.println("Fant ikke pasient med fødselsnummer " + sokestreng);
                    continue;
                } else {
                    System.out.println("Du har valgt pasient " + pasient.hentNavn());
                    break;
                }
            } else {
                IndeksertListe<Pasient> funnedePasienter = legesystem.finnPasienterMedNavn(legesystem.listAllePasienter(), sokestreng);
                if ( funnedePasienter.stoerrelse() == 0 ) {
                    System.out.println("Ingen pasienter funnet. Prøv igjen.");
                } else {
                    System.out.println("Disse pasientene matcher søket ditt:");
                    int ix = 1;
                    for( Pasient p : funnedePasienter ) {
                        System.out.println( String.format("#%d - %s (%s)", ix++, p.hentNavn(), p.hentFnr()));
                    }
                    ix = input.hentInteger("Pasient #", 1, funnedePasienter.stoerrelse())-1;
                    pasient = funnedePasienter.hent(ix);
                    break;
                }
            }
        }
        
        Prioritetskoe<Lege> prefererteLeger = legesystem.finnPrefererteLeger(pasient);
        if ( prefererteLeger.stoerrelse() > 0 ) {
            System.out.println("Tidligere benyttede leger:");
            Iterator<Lege> it = prefererteLeger.iterator();
            while(it.hasNext()) {
                Lege lege = it.next();
                System.out.println("  " + lege.hentId() + ": " + lege.hentNavn());
            }
        }

        System.out.print("Lege (id): ");
        int legeId = Integer.parseInt(scanner.nextLine());
        Lege lege = legesystem.finnLegeMedId(legesystem.listAlleLeger(), legeId);
        if ( lege == null ) {
            System.out.println("Fant ikke noen lege med id " + legeId);
            return;
        }
        

        IndeksertListe<Legemiddel> tidligereLegemidler = legesystem.listTidligereLegemidler(pasient);
        if( tidligereLegemidler.stoerrelse() > 0 ) {
            System.out.println("Tidligere benyttede legemidler:");
            Iterator<Legemiddel> lmIt = tidligereLegemidler.iterator();
            while( lmIt.hasNext() ) {
                Legemiddel tlm = lmIt.next();
                if (tlm instanceof NarkotiskLegemiddel && (!(lege instanceof Spesialist)))
                    continue;
                if (tlm instanceof NarkotiskLegemiddel && typeResept != 2)
                    continue;

                System.out.println(String.format("  %d. %s", tlm.hentId(), tlm.hentNavn() ));
            }
        }

        System.out.print("Legemiddel (id): ");
        int legeMiddelId = Integer.parseInt(scanner.nextLine());
        Legemiddel legemiddel = legesystem.finnLegemiddelMedId(legesystem.listAlleLegemidler(), legeMiddelId);
        if ( legemiddel == null ) {
            System.out.println("Fant ikke legemiddel med id " + legeMiddelId);
            return;
        }

        if (legemiddel instanceof NarkotiskLegemiddel) {
            if (!(lege instanceof Spesialist)) {
                System.out.println("Legen er ikke spesialist, så kan ikke bruke narkotiske legemidler.");
                return;
            }

            Spesialist spesialist = (Spesialist) lege;
            System.out.print("Kontroll-id: ");
            String kontrollId = scanner.nextLine();
            
            if ( kontrollId.compareTo(spesialist.hentKontrollID()) != 0 ) {
                System.out.println("Kontroll-id stemmer ikke. Du må kontakte spesialisten for bekreftelse.");
                return;
            }
        }

        int reit = MilitaerResept.REIT_OVERRIDE;
        if ( typeResept != 4 ) {
            System.out.print("Reit: ");
            reit = Integer.parseInt(scanner.nextLine());
        }

        try {
            Resept nyResept = null;
            
            if ( typeResept == 1 ) {
                nyResept = lege.skrivHvitResept(legemiddel, pasient, reit);
            } else if ( typeResept == 2 ) {
                nyResept = lege.skrivBlaaResept(legemiddel, pasient, reit);
            } else if ( typeResept == 3 ) {
                nyResept = lege.skrivPResept(legemiddel, pasient, reit);
            } else if ( typeResept == 4 ) {
                nyResept = lege.skrivMilResept(legemiddel, pasient);
            }

            System.out.println(String.format("Resept %d opprettet for %s.", nyResept.hentId(), pasient.hentNavn()));
        } catch( UlovligUtskrift e ) {
            System.out.println("Legen er ikke autorisert til å opprette denne resepten.");
        }
    }

    /* ****************************************************************
    *
    * Bruk resept
    * 
    * ***************************************************************/

    public void brukResept(Scanner scanner){
        Terminalinput input = new Terminalinput(scanner);

        // Valg av pasient
        
        if ( legesystem.hentAllePasienter().stoerrelse() == 0 ) {
            System.out.println("Det er ingen registrerte pasienter.");
            return;
        }
        
        int antPasienter = antallPasienterMeny();
        int valgtPasientIx = input.hentInteger("Pasient #", 0, antPasienter);
        Pasient valgtPasient = legesystem.hentAllePasienter().hent(valgtPasientIx);
     

        // Valg av resept basert på pasient

        if ( valgtPasient.hentResepter().stoerrelse() == 0 ) {
            System.out.println("Pasienten har ingen resepter.");
            return;
        }

        int antResepter = antallResepter(valgtPasient);
        int valgReseptIx = input.hentInteger("Resept #", 0, antResepter);
        Resept valgtResept = valgtPasient.hentResepter().hent(valgReseptIx);


        // Prøver å bruke resept

        brukResept(valgtResept);


        // Avslutter og returnerer til hovedmeny

        System.out.print("Trykk enter for å gå tilbake til hovedmenyen.");
        scanner.nextLine();
        System.out.println('\n');
        
        return;
    }
    
    private void brukResept(Resept valgtResept){
        Legemiddel reseptLegemiddel = valgtResept.hentLegemiddel();
        
        System.out.println();
        if(valgtResept.bruk()){
            System.out.println("Brukte resept paa "+reseptLegemiddel.hentNavn()+". Antall gjenvaerende reit: "+valgtResept.hentReit());
        }else{
            System.out.print("Kunne ikke bruke resept paa "+reseptLegemiddel.hentNavn()+" (ingen gjenvaerende reit).");
        }
            
    }
 
    private int antallPasienterMeny(){
        System.out.println();
        System.out.println("Hvilken pasient vil du se resepter for?");
        int i = 0;
        for(Pasient p : legesystem.hentAllePasienter()){
            System.out.println(i+": "+skrivPasientInfo(p));
            i++;
        }
        return i;
    }

    private String skrivPasientInfo(Pasient p){
        return p.hentNavn()+" (fnr "+p.hentFnr()+")";
    }

    private int antallResepter(Pasient p){
        int j = 0;
        System.out.println();
        System.out.println("Valgt pasient: "+skrivPasientInfo(p));
        for(Resept r : p.hentResepter()){
            Legemiddel l = r.hentLegemiddel();
            System.out.println(j+": "+l.hentNavn()+" ("+r.hentReit()+" reit)");
            j++;
        }
        return j;
    }
}