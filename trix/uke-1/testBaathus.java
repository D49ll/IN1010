public class testBaathus{
    public static void main(String[] args){
        Baathus mittHus = new Baathus(3);

        Baat b1 = new Baat("b1");
        Baat b2 = new Baat("b2");
        Baat b3 = new Baat("b3");
        Baat b4 = new Baat("b4");

        mittHus.settInn(b1);
        mittHus.settInn(b2);
        mittHus.settInn(b3);
        mittHus.settInn(b4);

        mittHus.skrivUt();
    }
}

class Baathus {
    private Baat[] baathusArr;

    public Baathus(int antallPlasser){
        baathusArr = new Baat[antallPlasser];
    }

    public void settInn(Baat nyBaat){
        boolean sattInn = false;
        int i = 0;

        while(i < baathusArr.length && sattInn == false){
            if (baathusArr[i] == null){
                baathusArr[i] = nyBaat;
                sattInn = true;
            }
            i++;
        }
        
        if (sattInn == false){
            System.out.println("Ikke mer plass");
        }

    }

    public void skrivUt(){
        for(int i = 0; i < baathusArr.length; i++){
            System.out.println("Båt "+i+": "+baathusArr[i].hentInfo());
        }
    }
}

class Baat {
    private static int antProd = 0;
    private int prodnr;
    private String merke;

    public Baat(String mrk) {
        prodnr = antProd;
        antProd++;
        merke = mrk;
    }

    public String hentInfo() {
        return "Produksjonsnummer: " + prodnr + ", merke: " + merke;
    }
}
