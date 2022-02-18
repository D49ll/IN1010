//At klassen er abstrakt betyr at vi ikke kan opprette et objekt av superklassen Legemiddel, men bare subklassene.
abstract class Legemiddel{
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

class Narkotisk extends Legemiddel{
    protected int styrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return (super.toString()+" med en narkotisk styrke lik "+styrke+".");
    }
}

class Vanedannende extends Legemiddel{
    protected int styrke;
    
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        return (super.toString()+" med en vanedannende styrke lik "+styrke+".");
    }
}

class Vanlig extends Legemiddel{

    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }
    @Override
    public String toString(){
        return (super.toString()+".");
    }
}