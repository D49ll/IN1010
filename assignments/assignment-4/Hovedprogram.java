import java.io.FileNotFoundException;
import java.util.Scanner;

class Hovedprogram {
    Legesystem legeSystem;

    public Hovedprogram() {
        legeSystem = new Legesystem();
    }

    void initMedTestData() throws Exception {
        System.out.println("Starter med testdata.");
        lastTestData(legeSystem);
    }

    void initMedImportData(String filnavn) throws FileNotFoundException {
        System.out.println("Laster data fra " + filnavn + "...");
        FilDataLag fd = new FilDataLag(legeSystem);
        fd.lesFraFil(filnavn);
    }

    void menyLoop() {
        Scanner scanner = new Scanner(System.in);

        try {
            // "Evig" loop - menyen for å håndtere kommandoer fra brukeren
            hovedMenyLoop(scanner);
        } catch( Exception e ) {
            System.out.println("Uventet feil: " + e.getMessage());
            System.exit(1);

        } finally {
            scanner.close();
        }
    }

    /* ****************************************************************
     *
     * Hovedmeny
     * 
     * ***************************************************************/

    private static final int KOMMANDO_BRUK_RESEPT = 1;
    private static final int KOMMANDO_OPPRETT_RESEPT = 2;
    private static final int KOMMANDO_OPPRETT_PASIENT = 3;
    private static final int KOMMANDO_STATISTIKK = 4;
    private static final int KOMMANDO_ADMIN = 5;
    private static final int KOMMANDO_AVSLUTT = 9;
    private static final int KOMMANDO_MENY = 99;

    void hovedMenyLoop(Scanner scanner) {
        Terminalinput t = new Terminalinput(scanner);

        while(true) {
            skrivHovedMeny();
            
            LegesystemUI ui = new LegesystemUI(legeSystem);
            AdminUI adminUI = new AdminUI(legeSystem);

            switch( t.hentInteger("Hovedmeny", 1, 99) ) {
                case KOMMANDO_BRUK_RESEPT:
                    ui.brukResept(scanner);
                    break;
                case KOMMANDO_OPPRETT_RESEPT:
                    ui.opprettResept(scanner);
                    break;
                case KOMMANDO_OPPRETT_PASIENT:
                    adminUI.opprettPasient(scanner);    
                    break;
                case KOMMANDO_STATISTIKK:
                    statistikkMenyLoop(scanner);
                    break;
                case KOMMANDO_ADMIN:
                    administrasjonsMenyLoop(scanner);
                    break;
                case KOMMANDO_AVSLUTT:
                    System.out.println("Avslutter. Takk for at du brukte vårt legesystem til å hjelpe folk.");
                    return;
                case KOMMANDO_MENY:
                    continue;
                default:
                    System.out.println("Ugyldig valg. Prøv igjen eller skriv 99 for å se menyen.");
            }
        }
    }

    void skrivHovedMeny() {
        System.out.println("");
        System.out.println("Hva vil du gjøre? Skriv # på et valg og trykk Enter:");
        System.out.println(KOMMANDO_BRUK_RESEPT + ". Bruk resept");
        System.out.println(KOMMANDO_OPPRETT_RESEPT + ". Opprett ny resept");
        System.out.println(KOMMANDO_OPPRETT_PASIENT + ". Opprett ny pasient");
        System.out.println("-");
        System.out.println(KOMMANDO_STATISTIKK + ". Statistikk");
        System.out.println(KOMMANDO_ADMIN + ". Administrasjon");
        System.out.println("-");
        System.out.println(KOMMANDO_AVSLUTT + ". Avslutt");
    }

    

    /* ****************************************************************
     *
     * Administrasjonsmeny
     * 
     * ***************************************************************/

    private static final int KOMMANDO_ADMIN_OPPRETT_LEGE = 1;
    private static final int KOMMANDO_ADMIN_OPPRETT_SPESIALIST = 2;
    private static final int KOMMANDO_ADMIN_OPPRETT_LEGEMIDDEL = 3;
    private static final int KOMMANDO_ADMIN_SKRIV_UT = 4;
    private static final int KOMMANDO_ADMIN_LES_FIL = 5;
    private static final int KOMMANDO_ADMIN_SKRIV_FIL = 6;

    void administrasjonsMenyLoop(Scanner scanner) {
        Terminalinput t = new Terminalinput(scanner);

        while(true) {
            AdminUI ui = new AdminUI(legeSystem);

            skrivAdministrasjonsMeny();

            switch( t.hentInteger("Administrasjon", 1, 99) ) {
                case KOMMANDO_ADMIN_OPPRETT_LEGE:
                    ui.opprettLege(scanner);
                    break;
                case KOMMANDO_ADMIN_OPPRETT_SPESIALIST:
                    ui.opprettSpesialist(scanner);
                    break;
                case KOMMANDO_ADMIN_OPPRETT_LEGEMIDDEL:
                    ui.opprettLegemiddel(scanner);
                    break;
                case KOMMANDO_ADMIN_SKRIV_UT:
                    ui.skrivUtAlleObjekterTilTerminalen();
                    break;
                case KOMMANDO_ADMIN_LES_FIL:
                    legeSystem = new Legesystem();
                    Resept.resettId();
                    Pasient.resettId();
                    Legemiddel.resettId();
                    Lege.resettId();
                    
                    Legesystem nyttLegesystem = ui.lesInnLegesystemFraFil(scanner);
                    if ( nyttLegesystem != null ) {
                        legeSystem = nyttLegesystem;
                    }
                    break;
                case KOMMANDO_ADMIN_SKRIV_FIL:
                    ui.skrivTilFil(scanner);
                    break;
                case KOMMANDO_MENY:
                    continue;
                case KOMMANDO_AVSLUTT:
                    return;
                default:
                    System.out.println("Ugyldig valg. Prøv igjen eller skriv 99 for å se menyen.");
            }
        }
    }

    void skrivAdministrasjonsMeny() {
        System.out.println("\nAdministrasjonsmeny");
        System.out.println("***");
        System.out.println(KOMMANDO_ADMIN_OPPRETT_LEGE + ". Opprett lege");
        System.out.println(KOMMANDO_ADMIN_OPPRETT_SPESIALIST + ". Opprett spesialist");
        System.out.println(KOMMANDO_ADMIN_OPPRETT_LEGEMIDDEL + ". Opprett legemiddel");
        System.out.println("-");
        System.out.println(KOMMANDO_ADMIN_SKRIV_UT + ". Skriv ut alle objekter");
        System.out.println("-");
        System.out.println(KOMMANDO_ADMIN_LES_FIL + ". Last inn fra fil");
        System.out.println(KOMMANDO_ADMIN_SKRIV_FIL + ". Lagre til fil");
        System.out.println("-");
        System.out.println(KOMMANDO_AVSLUTT + ". Gå tilbake");
    }


    /* ****************************************************************
     *
     * Statistikkmeny
     * 
     * ***************************************************************/

    private static final int KOMMANDO_STAT_VANEDANNENDE_RESEPTER = 1;
    private static final int KOMMANDO_STAT_NARKOTISKE_RESEPTER = 2;
    private static final int KOMMANDO_STAT_MULIG_MISBRUK = 3;
    
    void statistikkMenyLoop(Scanner scanner){
        Terminalinput t = new Terminalinput(scanner);
                
        while(true){
            skrivStatMeny();
            
            StatistikkUI ui = new StatistikkUI(legeSystem);

            switch( t.hentInteger("Statistikk", 1, 99) ) {
                case KOMMANDO_STAT_VANEDANNENDE_RESEPTER:
                    ui.statVanedannendeResepter();
                    scanner.nextLine();
                    break;
                case KOMMANDO_STAT_NARKOTISKE_RESEPTER:
                    ui.statNarkotiskeResepter();
                    scanner.nextLine();
                    break;
                case KOMMANDO_STAT_MULIG_MISBRUK:
                    ui.statMuligMisbruk();
                    scanner.nextLine();
                    break;
                case KOMMANDO_MENY:
                    continue;
                case KOMMANDO_AVSLUTT:
                    return;
                default:
                    System.out.println("Ugyldig valg. Prøv igjen eller skriv 99 for å se menyen.");

            }
        }
    }
    void skrivStatMeny(){
        System.out.println("\nStatistikkmeny");
        System.out.println("***");
        System.out.println(KOMMANDO_STAT_VANEDANNENDE_RESEPTER + ". Se antall vanedannende legemiddel resepter");
        System.out.println(KOMMANDO_STAT_NARKOTISKE_RESEPTER + ". Se antall narkotiske legemiddel resepter");
        System.out.println(KOMMANDO_STAT_MULIG_MISBRUK + ". Se statistikk for mulig misbruk av narkotiske midler");
        System.out.println("-");
        System.out.println(KOMMANDO_AVSLUTT + ". Gå tilbake til hovedmeny");
    }


    /* ****************************************************************
     *
     * Main entry points
     * 
     * ***************************************************************/

    static void lastTestData(Legesystem systemet) throws UlovligUtskrift {
        // Lege marcel         = new Lege("Marcel Petiot");
        // Lege george         = new Lege("George Chapman");
        // Lege josef          = new Lege("Josef Mengele");
        // Lege jane           = new Lege("Jane Toppan");

        // Lege lecter         = new Spesialist("Hannibal Lecter", "KNK-123");
        // Lege zoidberg       = new Spesialist("Dr. Zoidberg", "KNK-456");

        // Pasient anders      = new Pasient("Anders And", "123");
        // Pasient pato        = new Pasient("Pato Donald", "321");
        // Pasient canard      = new Pasient("Donald le canard", "513");
        // Pasient aku         = new Pasient("Aku Ankka", "421");
        // Pasient paperino    = new Pasient("Paperino", "565");
        // Pasient kalle       = new Pasient("Kalle Anka", "563");
        // Pasient donald      = new Pasient("Donald Duck", "0101190012345");

        // Legemiddel paracet  = new VanligLegemiddel("Paracetamol", 29, 500);
        
        // Legemiddel cosylan  = new NarkotiskLegemiddel("Cosylan", 249, 500, 20);
        // Legemiddel n1       = new NarkotiskLegemiddel("n1", 100, 500, 30);
        // Legemiddel n2       = new NarkotiskLegemiddel("n2", 200, 300, 70);
        // Legemiddel n3       = new NarkotiskLegemiddel("n3", 300, 400, 60);
        // Legemiddel n4       = new NarkotiskLegemiddel("n4", 400, 500, 10);
        // Legemiddel n5       = new NarkotiskLegemiddel("n5", 500, 200, 20);
        
        // Legemiddel livostin = new VanedannendeLegemiddel("Livostin", 129, 100, 10);
        // Legemiddel v1       = new VanedannendeLegemiddel("v1", 800, 300, 10);
        // Legemiddel v2       = new VanedannendeLegemiddel("v2", 500, 700, 20);
        // Legemiddel v3       = new VanedannendeLegemiddel("v3", 700, 800, 30);
        // Legemiddel v4       = new VanedannendeLegemiddel("v4", 300, 400, 40);
        // Legemiddel v5       = new VanedannendeLegemiddel("v5", 200, 100, 50);

        // //Donald har 3 vanedannende og 1 narkotisk
        // zoidberg.skrivHvitResept(paracet, donald, 3);
        // zoidberg.skrivHvitResept(livostin, donald, 3);
        // zoidberg.skrivHvitResept(v1, donald, 3);
        // zoidberg.skrivHvitResept(v2, donald, 3);
        // zoidberg.skrivBlaaResept(cosylan, donald, 3);

        // //Aku har 3 narkotiske og 3 vanedannende
        // lecter.skrivHvitResept(paracet, aku, 3);
        // lecter.skrivHvitResept(livostin, aku, 3);
        // lecter.skrivHvitResept(v1, aku, 3);
        // lecter.skrivHvitResept(v2, aku, 3);
        // lecter.skrivBlaaResept(n1, aku, 3);
        // lecter.skrivBlaaResept(n2, aku, 3);
        // lecter.skrivBlaaResept(n1, paperino, 3);
        // lecter.skrivBlaaResept(n1, canard, 3);
        // lecter.skrivBlaaResept(n1, anders, 2);
        // lecter.skrivBlaaResept(n2, paperino, 3);
        // lecter.skrivBlaaResept(n3, paperino, 3);

        // //Kalle har 5 vanedannende
        // george.skrivHvitResept(v1, kalle, 3);
        // george.skrivHvitResept(v2, kalle, 3);
        // george.skrivHvitResept(v3, kalle, 3);
        // george.skrivHvitResept(v4, kalle, 3);
        // george.skrivHvitResept(v5, kalle, 3);

        // //Bruker 1 reit av alle resepter til "anders". 
        // //For å sjekke at resepter med reit = 0 ikke blir med i statistikken
        // IndeksertListe<Resept> resepter = anders.hentResepter();
        // for(Resept r : resepter){
        //     r.bruk();
        // }

        // systemet.legemidler.leggTil(n1);
        // systemet.legemidler.leggTil(n2);
        // systemet.legemidler.leggTil(n3);
        // systemet.legemidler.leggTil(n4);
        // systemet.legemidler.leggTil(n5);

        // systemet.legemidler.leggTil(v1);
        // systemet.legemidler.leggTil(v2);
        // systemet.legemidler.leggTil(v3);
        // systemet.legemidler.leggTil(v4);
        // systemet.legemidler.leggTil(v5);

        // systemet.legemidler.leggTil(paracet);
        // systemet.legemidler.leggTil(livostin);
        // systemet.legemidler.leggTil(cosylan);

        // systemet.leger.leggTil(marcel);
        // systemet.leger.leggTil(george);
        // systemet.leger.leggTil(josef);
        // systemet.leger.leggTil(jane);
        // systemet.leger.leggTil(lecter);
        // systemet.leger.leggTil(zoidberg);

        // systemet.pasienter.leggTil(anders);
        // systemet.pasienter.leggTil(pato);
        // systemet.pasienter.leggTil(canard);
        // systemet.pasienter.leggTil(aku);
        // systemet.pasienter.leggTil(paperino);
        // systemet.pasienter.leggTil(kalle);
        // systemet.pasienter.leggTil(donald);
    }

    public static void main(String[] args) {
        System.out.println("LegeSystem/1.0");
        System.out.println("Copyright (C) Milliardær, Pakalon, Danni og C! 2022");
        System.out.println("***");

        try {
            Hovedprogram instans = new Hovedprogram();

            String databaseFilnavn = null;

            if (args.length > 0 && args[0] != null) {
                if ( args[0].compareTo("-testdata") == 0) {
                    instans.initMedTestData();
                } else {
                    databaseFilnavn = args[0];

                    try {
                        instans.initMedImportData(databaseFilnavn);
                    }
                    catch (FileNotFoundException f) {
                        System.out.println("Filen ble ikke funnet. Avslutter.");
                        return;
                    }
                }
            }

            instans.menyLoop();

            if ( databaseFilnavn != null ) {
                System.out.println("Lagrer databasen i " + databaseFilnavn);
                FilDataLag fd = new FilDataLag(instans.legeSystem);
                fd.skrivTilFil(databaseFilnavn);
            }
        }
        catch( Exception e ) {
            System.out.println("Katastrofal feil: " + e.getMessage());
            System.exit(1);
        }
    }
}