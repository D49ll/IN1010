public class Rack {
    private int maxNodes = 12;
    private int activeNodes = 0;
    private Node nodes[] = new Node[maxNodes];

    private static int activeRacks = 0;

    public void addNode(Node n){
        if(activeNodes<maxNodes){
            nodes[activeNodes] = n;
            activeNodes++;
        }else{
            activeRacks++;
            System.out.println("Rack "+activeRacks);
        }
    }

    public static int getAvailableRack(){
        return activeRacks;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        int antNoder = 0;

        for (Node node : nodes){
            if(node.getMem()<paakrevdMinne){
                antNoder++;
            }
        }
        return antNoder;
    }

    public void antProsessorer(){
        //int antPros = 0;
        for (Node node : nodes){
            System.out.println("hå");
        }
        //return antPros;
    }
}
