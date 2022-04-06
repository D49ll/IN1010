public class Prioritetskoe<T extends Comparable<T>> extends LenkeListe<T> {
    @Override
    public void leggTil(T element){
        Node newNode = new Node(element);

        //Første element
        if(head == null){
            head = newNode;
        }
        //Andre element
        else if(tail == null){

            //Nytt element < head element
            //Nytt element tar plassen til head og head elementet flyttes til tail
            if (element.compareTo(head.element) < 0){
                newNode.next = head;
                head.prev = newNode;
                
                tail = head;
                head = newNode;
            }
            //Nytt element > head element
            //Nytt element settes til tail
            else{
                head.next = newNode;
                newNode.prev = head;
                tail = newNode;
            }
        }
        //Resterende element
        else{
            //Her må vi sjekke alle elementene i listen
            Node currentNode = head;

            //Nytt element > current element og current element != tail element
            while(element.compareTo(currentNode.element)>0 && !currentNode.equals(tail)){
                currentNode = currentNode.next;
            }
            
            if(currentNode.equals(head)){
                if(element.compareTo(currentNode.element)>0){
                    head.next = newNode;
                    newNode.prev = head;
                    head = newNode;
                }else{
                    //Node som peker på nåværende tail
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                }
            }else if(currentNode.equals(tail)){
                if(element.compareTo(currentNode.element)>0){
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }else{
                    newNode.prev = tail.prev;
                    newNode.next = tail;
                    tail.prev.next = newNode;
                    tail.prev = newNode;
                    currentNode = newNode;
                }
                
            }else{
                currentNode.next = newNode;
                newNode.prev = currentNode;
                newNode.next = currentNode.next;
                currentNode = newNode;
            }
        }
        numNodes++;
    }
}
