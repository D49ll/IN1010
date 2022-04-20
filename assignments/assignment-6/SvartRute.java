import java.util.ArrayList;


public class SvartRute extends Rute {
    protected String tegn = "#"; 
    
    public SvartRute(int y, int x, Labyrint labyrint){
        super(y,x,labyrint);
    }
    
    @Override
    public String toString(){
        return tegn;
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