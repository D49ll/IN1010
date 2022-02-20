public class Spesialist extends Lege implements Godkjenningsfritak{
    protected String kontrollId;

    public Spesialist(String navn, String kontrollId){
        super(navn);
        this.kontrollId = kontrollId;
    }

    public String hentKontrollId(){
        return kontrollId;
    }

    public String toString(){
        return (super.toString()+" med følgende kontroll id "+kontrollId+".");
    }
}