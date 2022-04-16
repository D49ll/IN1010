public abstract class Rute{
    protected int y, x;
    protected Rute right=null , left=null, top=null, bottom=null;
    protected Labyrint labyrint;

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

    public abstract void gaa();

    public abstract char tilTegn();
}