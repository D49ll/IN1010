public class Person1 {
    private Bil3 bil;

    public Person1(Bil3 b){
        bil = b;
    }

    public void skrivUtBilNummer(){
        System.out.println("Personens bilnummer er "+bil.hentNummer());
    }
}
