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
        Resept hvit = new HvitResept(legemiddel1, hovedlege, 1000, 0);
        Resept blaa = new BlaaResept(legemiddel2, hovedlege, 1001, 2);
        Resept pre = new PResept(legemiddel3, hovedlege, 1003, 2);
        Resept mil = new MilResept(legemiddel4, hovedlege, 1004);

        //Enhetstester hver klasse
        printResultat(hvit, testResept(1, hvit, hovedlege, legemiddel1, 1000, 0));
        printResultat(blaa, testResept(2, blaa, hovedlege, legemiddel2, 1001, 2));
        printResultat(pre, testResept(3, pre, hovedlege, legemiddel3, 1002, 2));
        printResultat(mil, testResept(4, mil, hovedlege, legemiddel4, 1004, 3));
    
        //Tester toString()
        System.out.println("********************************");
        System.out.println("Tester redefineringen av toString() metoden\n");
        System.out.println(hvit.toString());
        System.out.println(blaa.toString());
        System.out.println(pre.toString());
        System.out.println(mil.toString());
        System.out.println("********************************");
    }
    
    public static void printResultat(Resept resept, boolean testResultat){
        if(testResultat){
            System.out.println("Resept med ID "+resept.hentId()+": bestod alle tester");
        }else{
            System.out.println("Resept med ID "+resept.hentId()+": bestod ikke alle tester");
        }
        System.out.println("********************************\n");
    }

    public static void feilResultat(String typeTest, int forventetResultat, int faktiskResultat){
        System.out.println("Feil \""+typeTest+"\"");
        System.out.println("Forventet "+forventetResultat+" men verdien er faktisk "+faktiskResultat);
        System.out.println();
    }

    public static boolean testResept(int reseptID, Resept resept, Lege lege, Legemiddel legemiddel, int pasientId, int reit){
        System.out.println("********************************");
        System.out.println("Tester resept med ID "+resept.hentId());
        System.out.println();
        
        //hentID() test
        if(!(resept.hentId() == reseptID)){
            feilResultat("Resept ID", reseptID, resept.hentId());
            return false;
        }

        //hentLege() test
        if(!(resept.hentLege().equals(lege))){
            System.out.println("Feil lege");
            return false;
        }

        //hentPasientId() test
        if(!(resept.hentPasientId() == pasientId)){
            feilResultat("Pasient ID", pasientId, resept.hentPasientId());
            return false;
        }

        //hentReit() test
        if(!(resept.hentReit()== reit)){
            feilResultat("reit", reit, resept.hentReit());
            return false;
        }

        //bruk() test
        System.out.println("Reit før bruk(): "+resept.hentReit());
        if(!resept.bruk()){
            System.out.println("Ingen flere reit's igjen.");
        }else{
            System.out.println("Reit etter bruk(): "+resept.hentReit());
        }
        System.out.println();

        //farge() test
        if(resept instanceof HvitResept){
            if(!(resept.farge().equalsIgnoreCase("hvit"))){
                System.out.println("Feil farge! Forventet hvit, men fikk "+resept.farge());
                return false;
            }
        }else if(resept instanceof BlaaResept){
            if(!(resept.farge().equalsIgnoreCase("blaa"))){
                System.out.println("Feil farge! Forventet blaa, men fikk "+resept.farge());
                return false;
            }
        }

        //prisAaBetale() test
        if(resept instanceof HvitResept){
            if(resept instanceof PResept){
                int pris = legemiddel.hentPris();
                if(pris<=108){
                    pris = 1;
                }else{
                    pris -= 108;
                }
                if(!(resept.prisAaBetale() == pris)){
                    feilResultat("prissjekk", pris, resept.prisAaBetale());
                    return false;
                }
            }else if(resept instanceof MilResept){
                if(!(resept.prisAaBetale()==0)){
                    feilResultat("prissjekk", 0, resept.prisAaBetale());
                    return false;
                }
            }else{
                if(!(resept.prisAaBetale() == legemiddel.hentPris())){
                    feilResultat("prissjekk", legemiddel.hentPris(), resept.prisAaBetale());
                    return false;
                }
            }
        }else if(resept instanceof BlaaResept){
            int pris = (int) Math.round(legemiddel.hentPris()*0.25);
            if(!(resept.prisAaBetale() == pris)){
                feilResultat("prissjekk", pris, resept.prisAaBetale());
                return false;
            }
        }
        
        //Alle tester bestått
        return true;
    }
}
