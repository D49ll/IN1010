public class Lege implements Comparable<Lege> {
    /********************
     * Instance variables
     ********************/
    //Disse burde bli initiert som private
    int _id;
    String _navn;
    IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();
    
    private int antallNarkotiskeResepter = 0;
    private int antallVanedannendeResepter = 0;
    private boolean harUtskrevetNarkotiskLegemiddel = false;
    
    /*****************
     * Private methods
     *****************/
    private boolean isVanedannede(Resept resept){
        return (resept.hentLegemiddel() instanceof VanedannendeLegemiddel);
    }

    private boolean isNarkotisk(Resept resept){
        return (resept.hentLegemiddel() instanceof NarkotiskLegemiddel);
    }

    private static int FORRIGE_ID = 0;
    private static int _utstedId() { return ++FORRIGE_ID; }
    public static void resettId() { FORRIGE_ID = 0; }

    /****************
     * Public methods
     ****************/
    public Lege(String navn) {
        _id = _utstedId();
        _navn = navn;
    }
    
    public boolean harUtskrevetNarkotisk(){return harUtskrevetNarkotiskLegemiddel;}

    public int hentAntNarkotiskResepter(){return antallNarkotiskeResepter;}

    public int hentAntVanedannendeResepter(){return antallVanedannendeResepter;}

    public String toString() {
        return "Lege #" + hentId() + " - " + hentNavn();
    }

    public int hentId() { return _id; }
    
    public String hentNavn() { return _navn; }
    
    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    @Override
    public int compareTo(Lege lege) {
        return hentNavn().compareTo(lege.hentNavn());
    }

    // Antar at id-en til pasient hentes med hentId() for alle
    HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof NarkotiskLegemiddel) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);
        
        if (isVanedannede(nyResept)){antallVanedannendeResepter++;}

        return nyResept;
    }

    MilitaerResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof NarkotiskLegemiddel) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        MilitaerResept nyResept = new MilitaerResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);

        if (isVanedannede(nyResept)){antallVanedannendeResepter++;}

        return nyResept;
    }

    PrevensjonsResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof NarkotiskLegemiddel) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        PrevensjonsResept nyResept = new PrevensjonsResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);

        if (isVanedannede(nyResept)){antallVanedannendeResepter++;}

        return nyResept;
    }

    BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof NarkotiskLegemiddel && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);

        if (isVanedannede(nyResept)){antallVanedannendeResepter++;}
        if (isNarkotisk(nyResept)){
            harUtskrevetNarkotiskLegemiddel = true;
            antallNarkotiskeResepter++;
        }

        return nyResept;
    }
    //Main metode - bare for testing.
    public static void main(String[] args) throws UlovligUtskrift {
        Lege lege = new Lege("TestBruker");
        Spesialist spesialist = new Spesialist("Spesialistlege", "001");
        NarkotiskLegemiddel narkotiskLegemiddel = new NarkotiskLegemiddel("QQQ", 1000, 1.5, 5);
        Pasient pasient = new Pasient("Ola", "3001");
        System.out.println(pasient);
        lege.skrivBlaaResept(narkotiskLegemiddel, pasient, 3);
        spesialist.skrivHvitResept(narkotiskLegemiddel, pasient, 3);
    }
}