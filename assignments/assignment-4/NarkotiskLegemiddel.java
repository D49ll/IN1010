public class NarkotiskLegemiddel extends Legemiddel {
    private int _styrke;

    public NarkotiskLegemiddel( String navn, double pris, double virkestoff, int styrke ) {
        super(navn, pris, virkestoff);
        _styrke = styrke;
    }

    public int hentNarkotiskStyrke() { return _styrke; }

    public String toString() {
        return super.toString() + ", narkotisk styrke: " + hentNarkotiskStyrke();
    }
}