class Rektangel {

    // Instansvariabler
    double bredde;
    double lengde;

    public Rektangel(double l, double b){
        lengde = l;
        bredde = b;
    }   
    public void oekLengde(int okning){
        lengde += okning;
    } 
    public void oekBredde(int okning){
        bredde += okning;
    }
    public double areal(){
        return bredde*lengde;
    }
    public double omkrets(){
        return (bredde*2)+(lengde*2);
    }
    public void redLengde(int reduksjon){
        if (lengde - reduksjon < 1){
            System.out.println("Lengden kan ikke reduseres til mindre enn 1");
        }
        else{
            lengde -= reduksjon;
        } 
    }
    public void redBredde(int reduksjon){
        if (bredde - reduksjon < 1){
            System.out.println("Bredden kan ikke reduseres til mindre enn 1");
        }
        else{
            bredde -= reduksjon;
        } 
    }
}
