public abstract class Legemiddel{
    protected int pris;
    protected double virkestoff;
    protected String navn;
    protected static int id = 0;

    public Legemiddel(String navn, int pris, double virkestoff){
        id++;
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
}

class Vanlig extends Legemiddel{

    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }
}