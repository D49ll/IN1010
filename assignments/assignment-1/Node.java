public class Node{
    private int antPros, mem;
    
    public Node(int p, int m){
        antPros = p;
        mem = m;
    }

    public int getMem(){
        return mem;
    }

    public int getPros(){
        return antPros;
    }

}