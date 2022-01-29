public class Hovedprogram {
    public static void main(String[] args){
        Dataklynge saga = new Dataklynge("saga");

        for(int i = 0; i < 26; i++){
            saga.placeNodeInRack(new Node(4,512));
        }

        //for(int i = 0; i < 2; i++){
       //     saga.placeNodeInRack(new Node(8,1024));
       //}

       saga.antProsessorer();

        //System.out.println("Noder med minst 512 GB: "+saga.noderMedNokMinne(512));
        //System.out.println("Noder med minst 1024 GB: "+saga.noderMedNokMinne(1024));
    }   
    
    
}
