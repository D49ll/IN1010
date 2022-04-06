public class Spesialist extends Lege implements Godkjenningsfritak {
    private String _kontrollId;

    public Spesialist(String navn, String kontrollId) {
        super(navn);

        _kontrollId = kontrollId;
    }
    
    public String hentKontrollID() { return _kontrollId; }

    public String toString() {
        return super.toString() + ", Spesialist [KontrollId: " + hentKontrollID() + "]";
    }
}