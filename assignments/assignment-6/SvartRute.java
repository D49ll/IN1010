
public class SvartRute extends Rute {
    public SvartRute(int y, int x, Labyrint labyrint){
        super(y,x,labyrint);
    }
    
    @Override
    public String toString(){
        return "#";
    }

    @Override
    public void finn(Rute fra){
        if(fra == null){
            System.out.println("Kan ikke starte i en sort rute");
        }
        return;
        //Sorte ruter kan man ikke gå gjennom
    }
}