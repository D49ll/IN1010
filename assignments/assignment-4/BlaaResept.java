public class BlaaResept extends Resept {
    private static final double PROSENT_RABATT = 0.25;

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() { return "blaa"; }
    public double prisAaBetale() { return (int)( hentLegemiddel().hentPris() * PROSENT_RABATT ); }
}