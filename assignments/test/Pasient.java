public class Pasient implements Comparable<Pasient>{
    //Definerer instansvariablene til klassen 
    private int id;
    private String navn;   
    private String fnr;
    private IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();
    
    private int antallGyldigeNarkotiskeResepter = 0;
    
    //Lager en privat variabel som holder styr på id og hvilken som ikke har blitt "tatt" enda. Starter denne på -1 fordi vi utsendtid() metoden øker id med 1 
    private static int forrige_id = 0;

    //Konstruktør som setter instansvariablene 
    public Pasient(String navn, String fnr) {
            //Definerer alle instansvariablene til klassen 
            this.navn = navn;
            this.fnr = fnr;
            //Kaller på metoden som setter id 
            id = utstedId();
    }

    //Metode som legger til Resept (Har lagt til denne fordi det står spesifisert i oppgaveteksten at det skal være muli å legge til nye resepter)
    public void leggTilResept(Resept resept) {
        resepter.leggTil(resept);
    }

    //Hent metoder for klassen 
    public int hentId() {
        return id; 
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFnr() {
        return fnr;
    }

    public IndeksertListe<Resept> hentResepter() {
        return resepter;
    }

    public boolean gyldigNarkotiskResepter(){
        boolean narkotiskResept = false;
        for(Resept r : resepter){
            if((r.hentLegemiddel() instanceof NarkotiskLegemiddel) && (r.gyldigResept())){
                narkotiskResept = true;
                antallGyldigeNarkotiskeResepter ++;
            }
        }
        return narkotiskResept;
    }

    public int hentGyldigeNarkotiskeResepter(){
        return antallGyldigeNarkotiskeResepter;
    }

    //Privat metode som setter id 
    private static int utstedId() { 
        forrige_id++;
        return forrige_id; 
    }

    public static void resettId() {
        forrige_id = 0;
    }

    //toString metode for klassen. Kan brukes til testing 
    public String toString() {
        //Formatering må endres her 
        return "Pasient #" + hentId() + " - " + hentNavn() + ", f.nr.: " + hentFnr();
    }

    @Override
    public int compareTo(Pasient o) {
        return navn.compareTo(o.navn);
    }  
}

    
