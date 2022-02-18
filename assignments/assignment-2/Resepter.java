abstract class Resept {
    protected int legemiddelId;
    protected Legemiddel legemiddelRef;
    //protected Lege legeRef;
    //protected String pastientId
    protected int reit;

    public Resept(){

    }

}

class HvitResept extends Resept{

    public HvitResept(){
        super();
    }

}

class MilResept extends HvitResept{
    
    public MilResept(){
        super();
        //Pris = 0kr
        //reit = 3
    }

}

class PResept extends HvitResept{
    public PResept(){
        super();
        //Gjelder kun prevansjonsmidler
        //nyPris = pris-108kr
        //kan ikke koste 0kr
    }
}

class BlaResept extends Resept{
    public BlaResept(){
        super();
        //Pris reduseres med 75. Dvs pasient betaler 25%, rundet til nærmeste krone
    }
}
