public class MilResept extends HvitResept{
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        //Reit = 3
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public int prisAaBetale(){
        //pris = 0
        return 0;
    }
}
