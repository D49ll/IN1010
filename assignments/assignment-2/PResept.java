public class PResept extends HvitResept{
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    
    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();

        //Dersom prisen er mindre enn 109 betaler kunden 1kr
        if(pris<=108){
            return 1;
        }
        return pris-108;
    }
}