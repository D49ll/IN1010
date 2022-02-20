//At klassen er abstrakt betyr at vi ikke kan opprette et objekt av superklassen Legemiddel, men bare subklassene.
public abstract class Legemiddel{
    protected int pris;
    protected double virkestoff;
    protected String navn;
    protected int id;
    protected static int antallLegemidler = 1;

    public Legemiddel(String navn, int pris, double virkestoff){
        id = antallLegemidler;
        antallLegemidler++;

        this.navn = navn;
        this.virkestoff = virkestoff;
        this.pris = pris;
    }

    public int hentId(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int pris){
        this.pris = pris;
    }

    public String toString(){
        return ("\""+navn+"\" har ID nr "+id+" og koster "+pris+"kr. Legemiddelet inneholder "+virkestoff+"mg av virkestoffet");
    }
}