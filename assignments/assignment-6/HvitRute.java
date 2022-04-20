public class HvitRute extends Rute {
    protected String tegn = "."; 

    public HvitRute(int y, int x, Labyrint labyrint){
        super(y,x,labyrint);
    }

    @Override
    public String toString(){
        return tegn;
    }
}
