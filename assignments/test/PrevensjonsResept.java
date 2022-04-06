public class PrevensjonsResept extends HvitResept {
    private static final int STATISK_RABATT = 108;

    public PrevensjonsResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public double prisAaBetale() { 
        double pris = super.prisAaBetale();
        
        pris -= STATISK_RABATT;
        if ( pris < 0 ) pris = 0;

        return pris;
    }

    public String toString() {
        return super.toString() + ", Klasse: p-resept";
    }
}