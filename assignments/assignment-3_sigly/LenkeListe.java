public abstract class LenkeListe<T> implements Liste<T>{
    class Node{
        Node next = null;
        T element;

        Node (T element){
            this.element = element;
        }
    }

    //Head = første node, tail = siste node.
    protected Node head = null;
    protected int numNodes = 0;

    public int stoerrelse(){
        return numNodes;
    }

    @Override
    public void leggTil(T element){
        Node newNode = new Node(element);
        Node currentNode = head;
        if(numNodes == 0){head = newNode;}
        else{
            System.out.println("NumNodes: "+numNodes);
            for(int i = 0; i < numNodes-1; i++){
                System.out.println("i: "+i);
                currentNode = currentNode.next;
            }
            System.out.println("currentNode: "+currentNode.element);
            //currentNode.next = newNode;
        }
        //System.out.println(toString());
        numNodes++;
    }
    
    @Override
    public T hent(){
        if(numNodes == 0){return null;}
        return head.element;
    }

    @Override
    public T fjern(){
        //Fjerner første element i listen og returner det.
        T res;
        if (numNodes == 0){throw new UgyldigListeindeks(-1);}
        else if(numNodes == 1){
            res = head.element;
            head = null;
        }else{
            res = head.element;
            head = head.next;
        }
        numNodes--;
        return res;
    }

    @Override
    public String toString(){
        if (numNodes == 0){
            return ("Ingen elementer i listen.");
        }
        
        Node node = head;
        String myStr = "tostring: ";

        myStr += node.element;
        while(node.next != null){
            //System.out.println(node.next.element);
            myStr +=" "+ node.next.element;
            node = node.next;
        }
        return myStr;
    }
}
