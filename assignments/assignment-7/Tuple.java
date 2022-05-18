public class Tuple{
    int x, y;
    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o){
        Tuple other = (Tuple) o;
        if(other.getX() == this.x && other.getY() == this.y){
            return true;
        }
        return false;
    }
}
