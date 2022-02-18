public class Lege {
    protected String legeNavn;

    public Lege(String legeNavn){
        this.legeNavn = legeNavn;
    }

    public String hentNavn(){
        return legeNavn;
    }
}
