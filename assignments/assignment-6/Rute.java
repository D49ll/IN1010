import java.util.ArrayList;

public abstract class Rute{
    protected int y, x;
    protected Rute right=null , left=null, top=null, bottom=null;
    protected Labyrint labyrint;
    protected ArrayList<ArrayList<Tuppel>> utveier;

    public Rute(int y,int x, Labyrint labyrint){
        //Ruten tilhører denne labyrint
        this.labyrint = labyrint;

        //Rutens koordinater
        this.x = x;
        this.y = y;
    }

    public void setNeighbors(Rute left, Rute top, Rute bottom, Rute right){
        //Rutens naboer
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public void finn(Rute fra){
        if(validRute(right, fra)){
            right.finn(this);
        } 
        if(validRute(left, fra)){
            left.finn(this);
        } 
        if(validRute(top, fra)){
            top.finn(this);
        } 
        if(validRute(bottom, fra)){
            bottom.finn(this);
        } 
    }

    private boolean validRute(Rute rute, Rute fra){
        return((rute!=fra && rute !=null) || fra == null);
    }

}