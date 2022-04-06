class StatistikkUI {
    Legesystem legesystem;

    public StatistikkUI(Legesystem s) {
        legesystem = s;
    }

    public void statVanedannendeResepter(){
        int antVanedannendeResepter = 0;
        for(Lege l : legesystem.hentAlleLeger()){
            antVanedannendeResepter += l.hentAntVanedannendeResepter();
        }
        System.out.println("Det er utskrevet "+antVanedannendeResepter+" resepter for vanedannende legemidler.");
        System.out.print("\nTrykk enter for å gå tilbake til statistikkmeny");
    }

    public void statNarkotiskeResepter(){
        int antNarkotiskeResepter = 0;
        for(Lege l : legesystem.hentAlleLeger()){
            antNarkotiskeResepter += l.hentAntNarkotiskResepter();
        }
        System.out.println("Det er utskrevet "+antNarkotiskeResepter+" resepter for narkotiske legemidler.");
        System.out.print("\nTrykk enter for å gå tilbake til statistikkmeny");

    }

    public void statMuligMisbruk(){
        Prioritetskoe<Lege> legerSort = new Prioritetskoe<Lege>();

        System.out.println("\nOversikt over utskrevne narkotiske resepter");
        System.out.println("\nLeger:");
        System.out.println("---");
        for(Lege l : legesystem.hentAlleLeger()){
            if(l.harUtskrevetNarkotisk()){
                legerSort.leggTil(l);
            }
        }

        for(Lege l : legerSort){
            if(l.hentAntNarkotiskResepter()==1){
                System.out.println(l.hentNavn()+", har utskrevet "+l.hentAntNarkotiskResepter()+" narkotisk resept");
            }else{
                System.out.println(l.hentNavn()+", har utskrevet "+l.hentAntNarkotiskResepter()+" narkotiske resepter");
            }
        }

        System.out.println("\nPasienter:");
        System.out.println("---");
        for(Pasient p : legesystem.hentAllePasienter()){
            if(p.gyldigNarkotiskResepter()){
                if(p.hentGyldigeNarkotiskeResepter()==1){
                    System.out.println(p.hentNavn()+", har "+p.hentGyldigeNarkotiskeResepter()+" narkotisk resept som enda er gyldig");
                }else{
                    System.out.println(p.hentNavn()+", har "+p.hentGyldigeNarkotiskeResepter()+" narkotiske resepter som enda er gyldig");
                }
            }
        }
        System.out.print("\nTrykk enter for å gå tilbake til statistikkmeny");
    }
}