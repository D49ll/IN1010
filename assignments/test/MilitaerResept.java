public class MilitaerResept extends HvitResept {
    public static final int REIT_OVERRIDE = 3;

    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, REIT_OVERRIDE);
    }

    public double prisAaBetale() { return 0; } // alltid 100% rabatt = 0 kr

    public String toString() {
        return super.toString() + ", Klasse: militærresept";
    }
}