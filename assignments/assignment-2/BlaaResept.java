public class BlaaResept extends Resept{
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
