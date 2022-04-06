public class Stabel<T> extends LenkeListe<T>{
    @Override
    public void leggTil(T element){
        Node newNode = new Node(element);
        
        //Første node
        if(head == null){
            head = newNode;
        }
        //Andre node
        else if(tail == null){
            newNode.next = head;
            head.prev = newNode;
            tail = head;
            head = newNode;
        }
        //Resterende noder
        else{                        
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        numNodes++;
    }
}
