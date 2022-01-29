import java.util.ArrayList;

public class Dataklynge {
    private String name;
    private ArrayList <Rack> racks = new ArrayList<>();
    
    public Dataklynge(String name){
        this.name = name;
    }

    public int getRacks(){
        //adds 1 because we start at index 0
        return (Rack.getAvailableRack()+1);
    }

    public void placeNodeInRack(Node n){
        //First time adding a rack
        if(racks.isEmpty()){
            racks.add(new Rack());
        }

        if(!racks.get(Rack.getAvailableRack()).addNode(n)){
            racks.add(new Rack());
            placeNodeInRack(n);
        }
    }

    public int noderMedNokMinne(int paakrevdMinne){
        int amountNodes = 0;

        for (Rack rack : racks){
            amountNodes += rack.noderMedNokMinne(paakrevdMinne);
        }
        return amountNodes;
    }

    public int antProsessorer(){
        int amountProc = 0;

        for(Rack rack : racks){
            amountProc += rack.antProsessorer();
        }   
        
        return amountProc;
    }
}
