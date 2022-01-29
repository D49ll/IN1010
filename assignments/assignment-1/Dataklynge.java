import java.util.ArrayList;

public class Dataklynge {
    private String navn;
    private ArrayList <Rack> racks = new ArrayList<>();

    public Dataklynge(String n){
        navn = n;
        
        racks.add(new Rack());
    }

    public void placeNodeInRack(Node n){
        int i = Rack.getAvailableRack();
        racks.get(i).addNode(n);

        int j = Rack.getAvailableRack();
        if (j > i){
            racks.add(new Rack());
            racks.get(j).addNode(n);
        }
      

    }
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoder = 0;

        for (Rack rack : racks){
            antNoder += rack.noderMedNokMinne(paakrevdMinne);
        }
        return antNoder;
    }

    public void antProsessorer(){
        int antPros = 0;

        for(Rack rack : racks){
            rack.antProsessorer();;
        }   

        //return antPros;
    }
}
