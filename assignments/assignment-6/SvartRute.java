public class SvartRute extends Rute {
    protected char tegn = '#'; 
    
    public SvartRute(int y, int x, Labyrint labyrint){
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