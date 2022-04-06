public abstract class Legemiddel implements Comparable<Legemiddel> {
    private int _id;
    private String _navn;
    private double _pris;
    private double _virkestoff;

    protected Legemiddel( String navn, double pris, double virkestoff ) {
        _id = _utstedId();
        _navn = navn;
        _pris = pris;
        _virkestoff = virkestoff;
    }

    public int hentId() { return _id; }
    public String hentNavn() { return _navn; }
    public double hentPris() { return _pris; }
    public double hentVirkestoff() { return _virkestoff; }

    public void settNyPris(int pris) { _pris = pris; }

    public String toString() {
        return "Legemiddel #" + hentId() + " - " + hentNavn() + ", pris: " + hentPris() + ", virkestoff: " + hentVirkestoff();
    }
    
    private static int FORRIGE_ID = 0;
    private static int _utstedId() { return ++FORRIGE_ID; }
    public static void resettId() { FORRIGE_ID = 0; }

    @Override
    public int compareTo(Legemiddel o) {
        return _navn.compareTo(o._navn);
    }
}