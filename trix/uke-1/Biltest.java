
public class Biltest{
    public static void main(String[] args){
        Bil bil1 = new Bil("dani","kia");
        Bil bil2 = new Bil("airik","golf");

        bil1.getInfo();
        bil2.getInfo();
    }
}

class Bil {
    private String eier;
    private String merke;

    public Bil(String e, String m){
        eier = e;
        merke = m;
    }

    public void getInfo(){
        System.out.println("Eier: "+eier);
        System.out.println("Merke: "+merke);
    }
}
