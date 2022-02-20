public abstract class Resept{
    //Instansvariabler
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
        return ("Resept med ID "+reseptId+" er utskrevet av "+utskrivendeLege.hentNavn()+".\nResepten tilhører pasient med ID "+pastientId+" som har "+reit+" reeit's tilgjengelig.\nDet er en "+farge()+" resept og koster "+prisAaBetale()+"kr.\n");
    }

    //En abstrakt metode kan ikke inneholde noen kropp. 
    //En abstrakt metode MÅ defineres i subklassene
    abstract public String farge();
    abstract public int prisAaBetale();
}