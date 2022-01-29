public class Rack {
    private int maxNodes = 12;
    private int activeNodes = 0;
    private Node nodes[] = new Node[maxNodes];
    private static int activeRacks = 0;

    public boolean addNode(Node n){
        if(activeNodes<maxNodes){
            nodes[activeNodes] = n;
            activeNodes++;
            return true;
        }else{
            activeRacks++;
            return false;
        }
    }

    public static int getAvailableRack(){
        return activeRacks;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        int amountNodes = 0, i = 0;

        while(i < nodes.length && nodes[i] != null){
            if(paakrevdMinne<=nodes[i].getGB()){
                amountNodes++;
            }
            i++;
        }
        return amountNodes;
    }

    public int antProsessorer(){
        int amountProc = 0, i = 0;

        while(i < nodes.length && nodes[i] != null){
            amountProc += nodes[i].getProc();
            i++;
        }
        return amountProc;
    }
}
