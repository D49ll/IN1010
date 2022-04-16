public class HvitRute extends Rute {
    protected char tegn = '.'; 

    public HvitRute(int y, int x, Labyrint labyrint){
        super(y,x,labyrint);
    }

    @Override
    public char tilTegn(){
        return tegn;
    }

    public void gaa(){
        left.gaa();
        right.gaa();
        top.gaa();
        bottom.gaa();
    }    
}
