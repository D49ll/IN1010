public class Hovedprogram0 {
    public static void main(String[] args){
        Dataklynge delA = new Dataklynge("delA");

        delA.insertNode(new Node(2,128), 1);
        delA.insertNode(new Node(8, 1024), 1);
        delA.insertNode(new Node(2, 512), 1);

        delA.antProsessorer();
        delA.printRacks();
    }
}
