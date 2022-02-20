abstract class Resept {
    protected int reseptId;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pastientId;
    protected int reit;
    protected static int antallResepter = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pastientId = pasientId;
        this.reit = reit;

        reseptId = antallResepter;
        antallResepter++;
    }
    public int hentId(){
        return reseptId;
    }

    public Lege hentLege(){ 
        return utskrivendeLege;
    }

    public int hentPasientId(){
        return pastientId;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if(reit < 1){
            return false;
        }
        reit -= 1;
        return true;
    }

    public String toString(){
        return ("Resept med ID "+reseptId+" er utskrevet av "+utskrivendeLege.hentNavn()+".\nResepten tilhører pasient med ID "+pastientId+" som har "+reit+" reeit's tilgjengelig.\nDet er en "+farge()+" resept og koster "+prisAaBetale()+"kr.");
    }

    //En abstrakt metode kan ikke inneholde noen kropp. 
    //Kroppen blir definert i subklassene
    abstract public String farge();
    abstract public int prisAaBetale();
}

class HvitResept extends Resept{
    protected int pris;
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge(){
        return ("hvit");
    }

    @Override
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }
}

class MilResept extends HvitResept{
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        //Reit = 0
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public int prisAaBetale(){
        //pris = 0
        return 0;
    }
}

class PResept extends HvitResept{
    protected int pris;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    
    @Override
    public int prisAaBetale(){
        pris = legemiddel.hentPris();

        //Dersom prisen er mindre enn 109 betaler kunden 1kr
        if(pris<=108){
            return 1;
        }
        return pris-108;
    }
}

class BlaaResept extends Resept{
    protected int pris;
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    
    @Override
    public String farge(){
        return ("blaa");
    }
    
    @Override
    public int prisAaBetale(){
        //Pris reduseres med 75. Dvs pasient betaler 25%, rundet til nærmeste krone.
        return (int) Math.round(legemiddel.hentPris()*0.25);
    }
}
