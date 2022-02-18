public class TestResepter {
    public static void main(String[] args){
        //Oppretter instanser av Legemidler
        Legemiddel legemiddel1 = new Narkotisk("Morfin", 100, 10.5, 5);
        Legemiddel legemiddel2 = new Vanedannende("Heroin", 150, 12.3, 15);
        Legemiddel legemiddel3 = new Vanlig("Zyrtec", 50, 5.1);
        Legemiddel legemiddel4 = new Narkotisk("Paragin Forte", 70, 9.2, 4);

        //Oppretter instans av Lege
        Lege hovedlege = new Lege("Balle");
        
        //Oppretter en av hver subklasse til superklassen Resept
        Resept hvit = new HvitResept(legemiddel1, hovedlege, 1, 2);
        Resept blaa = new BlaaResept(legemiddel2, hovedlege, 2, 2);
        Resept pre = new PResept(legemiddel3, hovedlege, 3, 2);
        Resept mil = new MilResept(legemiddel4, hovedlege, 4);
    }

    public boolean testResept(Resept resept, String[] forventetVerdier){
        int forventetReseptId = Integer.parseInt(forventetVerdier[0]);
        String forventetLegenavn = forventetVerdier[1];
        

        //hentID test

        //hentLege test

        //hentPasientId test

        //hentReit test

        //bruk test()

        //farge test

        //prisAaBetale test

        return true;
    }
}
