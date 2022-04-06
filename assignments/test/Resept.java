public abstract class Resept implements Comparable<Resept> {
    private int _id;
    private Legemiddel _legemiddel;
    private Lege _lege;
    private Pasient _pasient;
    private int _reit;
    
    protected Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        _id = _utstedId();
        _legemiddel = legemiddel;
        _lege = utskrivendeLege;
        _pasient = pasient;
        _reit = reit;

        if ( _reit < 1 ) throw new RuntimeException("reit må være 1 eller høyere.");
    }

    public int hentId() { return _id; }
    public Legemiddel hentLegemiddel() { return _legemiddel; }
    public Lege hentLege() { return _lege; }
    public int hentPasientId() { return _pasient.hentId(); }
    public int hentReit() { return _reit; }
    public boolean gyldigResept(){
        if(_reit == 0){
            return false;
        }
        return true;
    }
    public boolean bruk() {
        if(gyldigResept()==false){
            return false;
        }
        _reit--;
        return true;
        // Tester om vi kan bruke gyldgResept
        // if ( _reit == 0 ) return false;
        // _reit--;
        // return true;
    }

    abstract public String farge();
    abstract public double prisAaBetale();

    public String toString() {
        return "Resept #" + hentId() + " - " + hentLegemiddel().hentNavn() + " for " + _pasient.hentNavn() + " fra " + hentLege().hentNavn() + ", reit " + hentReit();
    }

    private static int FORRIGE_ID = 0;
    private static int _utstedId() { return ++FORRIGE_ID; }
    public static void resettId() { FORRIGE_ID = 0; }

    @Override
    public int compareTo(Resept o) {
        return Integer.compare(_id, o._id);
    }
}