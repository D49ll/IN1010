import java.util.ArrayList;

public class Dataklynge {
    private String name;
    private ArrayList <Rack> racks = new ArrayList<>();
    
    public Dataklynge(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getRacks(){
        //Legger til 1 fordi racks starter på med indeks 0.
        return (Rack.getAvailableRack()+1);
    }

    public void insertNode(Node n, int Nodes){
        for(int i = 0; i < Nodes; i ++){
            fillRack(n);
        }
    }

    private void fillRack(Node n){
        //Dersom det ikke finnes noen rack i dataklyngen
        if(racks.isEmpty()){
            racks.add(new Rack());
        }
    
        //Legger til node i rack. Dersom rack er full vil
        //if-testen utføres. Deretter utfører vi et rekursivt kall.
        if(!racks.get(Rack.getAvailableRack()).addNode(n)){
            racks.add(new Rack());
            fillRack(n);
        }
    }

    public void noderMedNokMinne(int paakrevdMinne){
        int amountNodes = 0;

        for (Rack rack : racks){
            amountNodes += rack.noderMedNokMinne(paakrevdMinne);
        }
        System.out.println("Noder med minst "+paakrevdMinne+" GB: "+amountNodes);
    }

    public void antProsessorer(){
        int amountProc = 0;

        for(Rack rack : racks){
            amountProc += rack.antProsessorer();
        }   
        System.out.println("Antall prosessorer: "+amountProc);
    }

    public void antRacks(){
        int amountRacks = Rack.getAvailableRack()+1;
        System.out.println("Antall rack: "+amountRacks);
    }
}
