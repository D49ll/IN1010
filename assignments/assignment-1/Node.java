public class Node{
    private int amountProc, amountGB;
        
    public Node(int proc, int memory){
        amountProc = proc;
        amountGB = memory;
    }

    public int getGB(){
        return amountGB;
    }

    public int getProc(){
        return amountProc;
    }

}