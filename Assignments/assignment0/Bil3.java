public class Bil3 {
    private String bilnummer;

    public Bil3 (String n){
    bilnummer = n;
    }

    public void getInfo(){
    System.out.println("bilnummeret er "+bilnummer);
    }

    public String hentNummer(){
        return bilnummer;
    }
}