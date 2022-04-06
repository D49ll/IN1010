import java.util.Iterator;

class Legesystem {

    // Objekter i systemet (merk at resepter lagres på hhv leger og pasienter)
    private IndeksertListe<Lege> leger = new IndeksertListe<Lege>();
    private IndeksertListe<Pasient> pasienter = new IndeksertListe<Pasient>();
    private IndeksertListe<Legemiddel> legemidler = new IndeksertListe<Legemiddel>();



    /* ****************************************************************
     *
     * Data aksess
     * 
     * ***************************************************************/

    IndeksertListe<Lege> hentAlleLeger() {
        return leger;
    }

     Iterator<Lege> listAlleLeger() {
        return leger.iterator();
    }

    IndeksertListe<Pasient> hentAllePasienter() {
        return pasienter;
    }

    Iterator<Pasient> listAllePasienter() {
        return pasienter.iterator();
    }

    IndeksertListe<Legemiddel> hentAlleLegemidler() {
        return legemidler;
    }

    Iterator<Legemiddel> listAlleLegemidler() {
        return legemidler.iterator();
    }



    /* ****************************************************************
     *
     * Data manipulasjon
     * 
     * ***************************************************************/

    void leggTilLegemiddel(Legemiddel l) {
        legemidler.leggTil(l);
    }

    void leggTilPasient(Pasient p) {
        pasienter.leggTil(p);
    }

    void leggTilLege(Lege l) {
        leger.leggTil(l);
    }
        

    
    /* ****************************************************************
     *
     * Forretningslogikk
     * 
     * ***************************************************************/
    

    Pasient finnPasientMedFnr(Iterator<Pasient> pasienter, String fnr) {
        while( pasienter.hasNext() ) {
            Pasient pasient = pasienter.next();
            if( pasient.hentFnr().compareTo(fnr) == 0 ) {
                return pasient;
            }
        }

        return null;
    }

    IndeksertListe<Pasient> finnPasienterMedNavn(Iterator<Pasient> pasienter, String sokestreng) {
        Prioritetskoe<Pasient> sortertePasienter = new Prioritetskoe<Pasient>();

        String uc_sokestreng = sokestreng.toUpperCase();

        while( pasienter.hasNext() ) {
            Pasient pasient = pasienter.next();
            if( pasient.hentNavn().toUpperCase().indexOf(uc_sokestreng) >= 0 ) {
                sortertePasienter.leggTil(pasient);
            }
        }

        IndeksertListe<Pasient> liste = new IndeksertListe<Pasient>();
        sortertePasienter.forEach(e -> liste.leggTil(e));
        return liste;
    }

    Pasient finnPasientMedId(Iterator<Pasient> pasienter, int id) {
        while( pasienter.hasNext() ) {
            Pasient pasient = pasienter.next();
            if( pasient.hentId() == id ) {
                return pasient;
            }
        }
        return null;
    }

    Lege finnLegeMedId(Iterator<Lege> leger, int id) {
        while( leger.hasNext() ) {
            Lege lege = leger.next();
            if( lege.hentId() == id ) {
                return lege;
            }
        }

        return null;
    }

    IndeksertListe<Lege> hentAlleLegerSortert() {
        Prioritetskoe<Lege> sorterteLeger = new Prioritetskoe<Lege>();
        leger.forEach( l -> sorterteLeger.leggTil(l) );
        IndeksertListe<Lege> liste = new IndeksertListe<Lege>();
        sorterteLeger.forEach( l -> liste.leggTil(l) );
        return liste;
    }

    Legemiddel finnLegemiddelMedId(Iterator<Legemiddel> legemidler, int id) {
        while( legemidler.hasNext() ) {
            Legemiddel legemiddel = legemidler.next();
            if( legemiddel.hentId() == id ) {
                return legemiddel;
            }
        }

        return null;
    }

    Lege finnLegeMedNavn(Iterator<Lege> leger, String navn) {
        while( leger.hasNext() ) {
            Lege lege = leger.next();
            if( lege.hentNavn().compareTo(navn) == 0 ) {
                return lege;
            }
        }

        return null;
    }

    Prioritetskoe<Lege> finnPrefererteLeger(Pasient pasient) {
        Prioritetskoe<Lege> prefererteLeger = new Prioritetskoe<Lege>();

        Iterator<Resept> resepter = pasient.hentResepter().iterator();
        while( resepter.hasNext() ) {
            Resept resept = resepter.next();

            // Sjekk om legen allerede er i listen
            boolean alleredeDer = false;
            Iterator<Lege> leger = prefererteLeger.iterator();
            while( leger.hasNext() ) {
                if( leger.next().hentId() == resept.hentLege().hentId() ) {
                    alleredeDer = true;
                    break;
                }
            }
            
            if ( !alleredeDer ) prefererteLeger.leggTil(resept.hentLege());
        }

        return prefererteLeger;
    }

    IndeksertListe<Legemiddel> listTidligereLegemidler(Pasient pasient) {
        Prioritetskoe<Legemiddel> sortertLegemiddelListe = new Prioritetskoe<Legemiddel>();
        Iterator<Resept> reseptIt = pasient.hentResepter().iterator();
        while( reseptIt.hasNext() ) {
            Resept resept = reseptIt.next();

            // Sjekk om legemiddelet allerede er i listen
            boolean funnet = false;
            Iterator<Legemiddel> it = sortertLegemiddelListe.iterator();
            while(it.hasNext()) {
                if (it.next().hentId() == resept.hentLegemiddel().hentId()) {
                    funnet = true;
                    break;
                }
            }

            if ( !funnet )
                sortertLegemiddelListe.leggTil(resept.hentLegemiddel());
        }

        IndeksertListe<Legemiddel> liste = new IndeksertListe<Legemiddel>();
        sortertLegemiddelListe.forEach(e -> liste.leggTil(e));
        return liste;
    }

    IndeksertListe<Resept> hentAlleResepterSortert() {
        IndeksertListe<Resept> alleResepter = new IndeksertListe<Resept>();
        
        Iterator<Lege> legeIt = leger.iterator();
        while(legeIt.hasNext()) {
            Lege lege = legeIt.next();
            Iterator<Resept> reseptIt = lege.utskrevneResepter.iterator();
            while( reseptIt.hasNext() ) {
                Resept resept = reseptIt.next();
                alleResepter.leggTil(resept);
            }
        }

        return alleResepter;
    }
}