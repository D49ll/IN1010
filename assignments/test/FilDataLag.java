//Denne filen inneholder funksjonebe lesFraFil og skrivTilFil (og hjelpefunksjoner)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class FilDataLag {
    private Legesystem legesystem;

    public FilDataLag(Legesystem s) {
        legesystem = s;
    }

    /* ****************************************************************
     *
     * Funksjoner for å laste fra fil
     * 
     * ****************************************************************/

    void lesFraFil(String filnavn) throws FileNotFoundException{
        final int SEKSJON_PASIENTER = 0;
        final int SEKSJON_LEGEMIDLER = 1;
        final int SEKSJON_LEGER = 2;
        final int SEKSJON_RESEPTER = 3;

        //Oppretter et nytt Scanner objekt basert på filnavnet
        Scanner in = new Scanner(new File(filnavn)); 

        int antfeil = 0; // totalt antall feil i importert fil
        int linjeNr = 0; // hvilket linjenr vi er på
        int seksjon = -1; // hvilken seksjon er vi i, basert på # header linjer
        
        //While løkke som skal kjøre så lenge det er fler linjer 
        while( in.hasNextLine() ) { 
            String linje = in.nextLine();
            linjeNr++;

            //Litt ineffektiv løsning men det fungerer for vårt bruk så lenge listen er på riktig format
            //Ser her på hvorvidt linjen inneholder # og øker ant variabelen med 1 dersom dette er tilfelle 
            if (linje.indexOf("#") == 0) {
                seksjon++; 
            } else {
                //Try catch som skal sørge for at filen blir lest riktig 
                try {
                    // Import funksjonene kaster exceptions ved feilformattering eller på annen måte ugyldige data
                    String[] linje_argumenter = linje.trim().split(",");
                    trim_argumenter(linje_argumenter);

                    if (seksjon == SEKSJON_PASIENTER) {
                        importerPasient(linje_argumenter);
                    } else if (seksjon == SEKSJON_LEGEMIDLER) {
                        importerLegemiddel(linje_argumenter);
                    } else if (seksjon == SEKSJON_LEGER) {
                        importerLege(linje_argumenter);
                    } else if (seksjon == SEKSJON_RESEPTER) {
                        importerResept(linje_argumenter);
                    }
                }
                catch (Exception e) {
                    System.out.println(String.format("Feil i linje %d (%s)", linjeNr, e.getMessage()));
                    antfeil++;
                } 
            }
        }

        in.close();
        
        if ( antfeil > 0 )
            System.out.println("Filen inneholder totalt " + antfeil + " feil");
    }

    //Funksjon som skal trimme et array med argumenter 
    void trim_argumenter(String[] argumenter) {
        for (int i = 0; i < argumenter.length; i++) {
            argumenter[i] = argumenter[i].trim();
        }
    }

    void importerPasient(String[] linje_argumenter) {
        Pasient pasient = new Pasient(linje_argumenter[0],linje_argumenter[1]);
        legesystem.leggTilPasient(pasient);
    }

    void importerLegemiddel(String[] linje_argumenter) {
        Legemiddel legemiddel = null;

        //Styrke skal være en double 
        if (linje_argumenter[1].compareTo("narkotisk") == 0){
            legemiddel = new NarkotiskLegemiddel(   linje_argumenter[0], 
                                                    Double.parseDouble(linje_argumenter[2]), 
                                                    Double.parseDouble(linje_argumenter[3]), 
                                                    //Her velger vi å caste fra double til int 
                                                    (int) Double.parseDouble(linje_argumenter[4]));
        }
        else if (linje_argumenter[1].compareTo("vanedannende") == 0) {
            legemiddel = new VanedannendeLegemiddel(    linje_argumenter[0], 
                                                        Double.parseDouble(linje_argumenter[2]), 
                                                        Double.parseDouble(linje_argumenter[3]), 
                                                        (int) Double.parseDouble(linje_argumenter[4]));
        }
        else if (linje_argumenter[1].compareTo("vanlig") == 0) {
            legemiddel = new VanligLegemiddel(  linje_argumenter[0], 
                                                Double.parseDouble(linje_argumenter[2]), 
                                                Double.parseDouble(linje_argumenter[3]));
        }

        //Legger til legemidlet i legesystemet.
        legesystem.leggTilLegemiddel(legemiddel);
    }

    void importerLege(String[] linje_argumenter) {
        Lege lege = null;

        if (linje_argumenter[1].compareTo("0") == 0 ) 
            lege = new Lege(linje_argumenter[0]);
        else  { 
            lege = new Spesialist(linje_argumenter[0], linje_argumenter[1]);
        }
        
        legesystem.leggTilLege(lege); 
    }

    void importerResept(String[] linje_argumenter) throws Exception, UlovligUtskrift {
        //Alle typene av resepter krever at vi har referanser til legemiddel, utskrivendeLege og pasient.

        Legemiddel legemiddel = legesystem.finnLegemiddelMedId(legesystem.listAlleLegemidler(), Integer.parseInt(linje_argumenter[0]));
        if ( legemiddel == null )
            throw new Exception("Fant ikke legemiddel med id " + linje_argumenter[0]);

        Lege lege = legesystem.finnLegeMedNavn(legesystem.listAlleLeger(), linje_argumenter[1]);
        if ( lege == null )
            throw new Exception("Fant ikke lege ved navn " + linje_argumenter[1]);            

        Pasient pasient = legesystem.finnPasientMedId(legesystem.listAllePasienter(), Integer.parseInt(linje_argumenter[2]));
        if ( pasient == null )
            throw new Exception("Fant ikke pasient med id " + linje_argumenter[2]);


        // Nå skal vi opprette resepten, vi må sjekke hvilken type det er ok så utstede med referansene vi har over

        if (linje_argumenter[3].compareTo("hvit") == 0) {
            lege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(linje_argumenter[4]));
        }
        else if((linje_argumenter[3].compareTo("militaer") == 0 )) {
            lege.skrivMilResept(legemiddel, pasient);   
        }
        else if(linje_argumenter[3].compareTo("p") == 0 ) {
            lege.skrivPResept(legemiddel, pasient, Integer.parseInt(linje_argumenter[4]));
        }
        else if(linje_argumenter[3].compareTo("blaa") == 0 ) {
            lege.skrivBlaaResept(legemiddel, pasient, Integer.parseInt(linje_argumenter[4]));
        }
    }

    // Litt janky løsning for å skrive inn typer, men funker
    void skrivTilFil(String filnavn) {
        try {
            FileWriter skriver = new FileWriter(filnavn);          

            skriver.write("# Pasienter (navn, fnr)\n");
            for (Pasient p : legesystem.hentAllePasienter()) {
                skriver.write(p.hentNavn() + "," + p.hentFnr() + "\n");
            }

            skriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");
            for (Legemiddel l : legesystem.hentAlleLegemidler() ) {
                String utskrift = l.hentNavn() + "," + l.getClass().getSimpleName().replace("Legemiddel", "").toLowerCase()
                 + "," + l.hentPris() + "," + l.hentVirkestoff();
                if (l instanceof NarkotiskLegemiddel) {
                    utskrift += "," + ((NarkotiskLegemiddel) l).hentNarkotiskStyrke();
                }
                if (l instanceof VanedannendeLegemiddel) {
                    utskrift += "," + ((VanedannendeLegemiddel) l).hentVanedannendeStyrke();
                }
                utskrift += "\n";
                skriver.write(utskrift);
            }

            skriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege l : legesystem.hentAlleLeger()) {
                String utskrift = l.hentNavn();
                if (l instanceof Spesialist) {
                    utskrift += "," + ((Spesialist) l).hentKontrollID();
                } else {
                    utskrift += ",0";
                }
                utskrift += "\n";
                skriver.write(utskrift);
            }

            skriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Resept r : legesystem.hentAlleResepterSortert()) {
                String utskrift = r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasientId() + ",";
                if (r instanceof PrevensjonsResept) {
                    utskrift += "p";
                } else {
                    utskrift += r.getClass().getSimpleName().replace("Resept", "").toLowerCase();
                }
                if (r instanceof HvitResept) {
                    utskrift += "," + ((HvitResept) r).hentReit();
                }
                if (r instanceof BlaaResept) {
                    utskrift += "," + ((BlaaResept) r).hentReit();
                }
                utskrift += "\n";
                skriver.write(utskrift);
            }
            
            skriver.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}