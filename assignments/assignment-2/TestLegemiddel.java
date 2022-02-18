import java.util.ArrayList;
import java.util.Arrays;


public class TestLegemiddel {
    public static void main(String[] args){
        Legemiddel narko = new Narkotisk("Morfin", 120, 12.0, 9);
        Legemiddel vane = new Vanedannende("Heroin", 60, 15.0, 10);
        Legemiddel vanlig = new Vanlig("Allergimedisin", 20, 5.1);

        String[] narkotiskStoff = new String[]{"1","morfin","120","12.0","9"};
        String[] vanedannendeStoff = new String[]{"2","Heroin","60","15.0","10"};
        String[] vanligStoff = new String[]{"3","allergimedisin","20","5.1"};

        if (!testLegemiddel(narko, narkotiskStoff) || !testLegemiddel(vane, vanedannendeStoff) || !testLegemiddel(vanlig, vanligStoff)){
            System.out.println("\nEt av stoffende bestod ikke testen, se feilmelding over.");
        }else{
            System.out.println("\nAlle stoffende bestod testen!");
        }

    }
    
    public static void feilResultat(Legemiddel legemiddel, String typeTest, String forventetResultat, String faktiskResultat){
        System.out.println("Feil \""+typeTest+"\" i legemiddelet \""+legemiddel.hentNavn()+"\"");
        System.out.println("Forventet "+forventetResultat+" men verdien er faktisk "+faktiskResultat);
        System.out.println("********************************");

    }

    public static boolean testLegemiddel(Legemiddel legemiddel, String[] forventetVerdier){
        ArrayList<String> tester = new ArrayList<String>();

        //Konverterer til riktig datatype
        int forventetId =  Integer.parseInt(forventetVerdier[0]);
        String forventetNavn = forventetVerdier[1];
        int forventetPris = Integer.parseInt(forventetVerdier[2]);
        double forventetVirkestoff = Double.parseDouble(forventetVerdier[3]);
       
        tester.addAll(Arrays.asList("Unikt ID","Navn","Pris(kr)","Virkestoff(mg)"));

        //Felles sjekker for alle de 3 ulike legemidlene
        System.out.println("\nTester "+legemiddel.hentNavn());
        System.out.println("********************************");

        if(!(legemiddel.hentId() == forventetId)){
            feilResultat(legemiddel, tester.get(0), Integer.toString(forventetId), Integer.toString(legemiddel.hentId()));
            return false;
        }

        if(!(legemiddel.hentNavn().equalsIgnoreCase(forventetNavn))){
            feilResultat(legemiddel, tester.get(1), forventetNavn, legemiddel.hentNavn());
            return false;
        }

        if(!(legemiddel.hentPris() == forventetPris)){
            feilResultat(legemiddel, tester.get(2), Integer.toString(forventetPris), Integer.toString(legemiddel.hentPris()));
            return false;
        }

        if(!(legemiddel.hentVirkestoff() == forventetVirkestoff)){
            feilResultat(legemiddel, tester.get(3), Double.toString(forventetVirkestoff), Double.toString(legemiddel.hentVirkestoff()));
            return false;
        }
        
        //Dersom stoffet vi sjekker er av typen Narkotisk må vi også sjekke det den narkotiske styrken
        if(legemiddel instanceof Narkotisk){
            tester.add("Narkotisk styrke");
            int forventetStyrke = Integer.parseInt(forventetVerdier[4]);
            
            if(! (((Narkotisk)legemiddel).hentNarkotiskStyrke() == forventetStyrke)){
                feilResultat(legemiddel, tester.get(4), Integer.toString(forventetStyrke), Integer.toString(((Narkotisk)legemiddel).hentNarkotiskStyrke()));
                return false;
            }

        //Dersom stoffet vi sjekker er av typen Vanedannende må vi også sjekke det den vanedannende styrken
        }else if(legemiddel instanceof Vanedannende){
            tester.add("Vanedannende styrke");
            int forventetStyrke = Integer.parseInt(forventetVerdier[4]);
            
            if(! (((Vanedannende)legemiddel).hentVanedannendeStyrke() == forventetStyrke)){
                feilResultat(legemiddel, tester.get(4), Integer.toString(forventetStyrke), Integer.toString(((Vanedannende)legemiddel).hentVanedannendeStyrke()));
                return false;
            }
        }

        //Skriver ut info til bruker dersom alle verdier er som forventet
        System.out.println("Verdier til \""+legemiddel.hentNavn()+"\" var som forventet:");
        for(int i = 0; i < tester.size(); i++){
            System.out.println(tester.get(i)+": "+forventetVerdier[i]);
        }
        
        System.out.println("********************************");

        return true;
    }
}