public class Stabel<T> extends LenkeListe<T>{
    @Override
    public void leggTil(T element){
        Node newNode = new Node(element);
        
        //Første node
        if(numNodes == 0){
            head = newNode;
        }
        //Resterende noder
        else{                        
            newNode.next = head;
            head = newNode;
        }
        numNodes++;
    }
}
