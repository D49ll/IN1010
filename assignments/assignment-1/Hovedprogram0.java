public class Hovedprogram0 {
    public static void main(String[] args){
        Dataklynge saga = new Dataklynge("saga");

        for(int i = 0; i < 450; i++){
            saga.placeNodeInRack(new Node(4,512));
        }
        for(int i = 0; i < 16; i++){
            saga.placeNodeInRack(new Node(8,1024));
        }

        noderMedNokMinne(128,saga.noderMedNokMinne(128));
        noderMedNokMinne(512,saga.noderMedNokMinne(512));
        noderMedNokMinne(1024,saga.noderMedNokMinne(1024));
        datacenterInfo(saga.antProsessorer(),saga.getRacks());
    }

    public static void noderMedNokMinne(int paakrevdMinne, int amountNodes){
        System.out.println("Noder med minst "+paakrevdMinne+" GB: "+amountNodes);
    }

    public static void datacenterInfo(int amountProc, int amountRacks){
        System.out.println("\nAntall prosessorer: "+amountProc);
        System.out.println("Antall rack: "+amountRacks);
    }


    
}
