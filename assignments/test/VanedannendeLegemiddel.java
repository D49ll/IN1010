public class VanedannendeLegemiddel extends Legemiddel {
    private int _styrke;

    public VanedannendeLegemiddel( String navn, double pris, double virkestoff, int styrke ) {
        super(navn, pris, virkestoff);
        _styrke = styrke;
    }

    public int hentVanedannendeStyrke() { return _styrke; }

    public String toString() {
        return super.toString() + ", vanedannende styrke: " + hentVanedannendeStyrke();
    }
}