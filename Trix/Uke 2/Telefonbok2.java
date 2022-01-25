import java.util.ArrayList;
import java.util.HashMap;

public class Telefonbok2{
    public static void main(String[] args){
        //Oppgave a og b)
        //Person p[] = new Person[10];

        Person p0 = new Person("p1","1","a");
        Person p1 = new Person("p2","2","b");
        Person p2 = new Person("p3","3","c");
        
        //p[0] = p0;
        //p[1] = p1;
        //p[2] = p2;

        //for (int i = 0; i < p.length; i++){
        //    if (p[i] != null){
        //        p[i].skrivInfo();
        //    }
        //}
        
        //Oppgave c)
        //ArrayList <Person> p = new ArrayList <Person> ();
        //
        //p.add(p0);
        //p.add(p1);
        //p.add(p2);
        //
        //for(Person P : p){
        //    P.skrivInfo();
        //}

        HashMap <String,Person> alle = new HashMap<String, Person>();

        alle.put(p0.hentNavn(), p0);
        alle.put(p1.hentNavn(), p1);
        alle.put(p2.hentNavn(), p2);

        for (Person value : alle.values()){
            value.skrivInfo();
        }
    }
}




class Person {

    private String navn;
    private String telefonnummer;
    private String adresse;

    public Person(String navn, String telefonnummer, String adresse) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
    }

    public void skrivInfo() {
        System.out.println("Navn: " + navn);
        System.out.println("Telefonnummer: " + telefonnummer);
        System.out.println("Adresse: " + adresse + "\n");
    }

    public String hentNavn() {
        return navn;
    }
}