public class TestLegemiddel {
    public static void main(String[] args){
        Legemiddel narko = new Narkotisk("Morfin", 120, 12.0, 9);
        Legemiddel vane = new Vanedannende("Heroin", 60, 15.0, 10);
        Legemiddel vanlig = new Vanlig("Allergimedisin", 20, 5);
    }
    
    public static boolean testLegemiddelId(Legemiddel legemiddel, int forventetId){
        return legemiddel.hentId() == forventetId;
    }

    public static boolean testLegemiddelNavn(Legemiddel legemiddel, String forventetNavn){
        //Testen er ikke case-sensitiv
        return legemiddel.hentNavn().equalsIgnoreCase(forventetNavn);
    }

    public static boolean testLegemiddelPris(Legemiddel legemiddel, int forventetPris){
        return legemiddel.hentPris() == forventetPris;
    }

    public static boolean testLegemiddelVirkestoff(Legemiddel legemiddel, double forventetVirkestoff){
        return legemiddel.hentVirkestoff() == forventetVirkestoff;
    }

    public static boolean testNarkotiskLegemiddel(Narkotisk legemiddel, double forventetStyrke){
        return legemiddel.hentNarkotiskStyrke() == forventetStyrke;
    }
    public static boolean testVanedannendeLegemiddel(Vanedannende legemiddel, double forventetStyrke){
        return legemiddel.hentVanedannendeStyrke() == forventetStyrke;
    }

    public void testLegemiddel(Narkotisk legemiddel, String[] forventetVerdier){
        int forventetId =  Integer.parseInt(forventetVerdier[0]);
        String forventetNavn = forventetVerdier[1];
        int forventetPris = Integer.parseInt(forventetVerdier[2]);
        double forventetVirkestoff = Double.parseDouble(forventetVerdier[3]);
        int styrke = Integer.parseInt(forventetVerdier[4]);

        //Id test
        System.out.println("Tester legemiddelId.");
        if (testLegemiddelId(legemiddel,forventetId)){
            System.out.println("Test ok!");
            System.out.println("Forventet Id er lik faktisk ID");
        }

        //Id test
        System.out.println("Tester legemiddel navn.");
        if (testLegemiddelId(legemiddel,forventetId)){
            System.out.println("Test ok!");
            System.out.println("Forventet Id er lik faktisk ID");
        }
        

    }
    
}
