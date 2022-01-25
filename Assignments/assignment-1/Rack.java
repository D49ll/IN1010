public class Rack {
    private int maxNodes = 12;
    private int currentNodes = 0;
    private Node nodes[] =  new Node[maxNodes];

    public void addNode(Node n){
        if (nodes.length<maxNodes){
            nodes[currentNodes] = n;
            currentNodes ++;
        }else{
            
        }
    }

}
