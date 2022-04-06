public class Subsekvens {
    private int antall;
    public final String subsekvens;
    
    public Subsekvens(int antall, String subsekvens){
        this.antall = antall;
        this.subsekvens = subsekvens;
    }

    public int getAmount(){
        return antall;
    }

    public void increment(){
        antall++;
    }

    @Override
    public String toString() {
        return "("+subsekvens+","+antall+") ";
    }

    // //Testprogram
    // public static void main(String args[]){
    //     Subsekvens sub1 = new Subsekvens(3, "ABC");
    //     System.out.println("Antall: "+sub1.get());
    //     sub1.increment();
    //     System.out.println("Antall: "+sub1.get());
        

    //     Subsekvens sub2 = new Subsekvens(2, "CBA");
    //     Subsekvens sub3 = new Subsekvens(6, "TDSA");

    //     System.out.println(sub1.toString());
    //     System.out.println(sub2.toString());
    //     System.out.println(sub3.toString());
    // }
}
