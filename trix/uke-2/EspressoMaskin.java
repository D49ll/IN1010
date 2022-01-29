class EspressoMaskin {
    private int vanntank = 0;

    public static void main(String[] args){
        EspressoMaskin kaffemaskin = new EspressoMaskin();

        kaffemaskin.lagEspresso();
        kaffemaskin.fyllVann(500);
        kaffemaskin.lagEspresso();
    }

    // Lag espresso dersom det er nok vann
    public void lagEspresso() {   
        if (vanntank >= 40){
            System.out.println("Lager espresso.");
            vanntank -= 40;
            System.out.println("Maskinen har nå "+vanntank+"ml.");
        }else{
            trengerVann("Espresso");
        }
    }

    // Lag lungo dersom det er nok vann
    public void lagLungo() {   
        if (vanntank >= 110){
            System.out.println("Lager lungo.");
            vanntank -= 110;
        }else{
            trengerVann("Lungo");
        }
    }

    public void trengerVann(String kaffekopp){
        System.out.println("Ikke nok vann til en "+kaffekopp+".");
        System.out.println("Vanntanken har  "+vanntank);
    }

    // Fyll på et gitt antall milliliter vann, dersom det er plass
    public void fyllVann(int ml) {   
        if(ml < (1000-vanntank)){
            vanntank += ml;
        }else{
            System.out.println("Ikke plass i tank.");
        }
    }

    // Les av målestrekene på vanntanken og tilgjengelig vann i ml
    public int hentVannmengde() {   
        return vanntank;
    }
}
