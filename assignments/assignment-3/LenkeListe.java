public abstract class LenkeListe<T> implements Liste<T>{
    class Node{
        Node next = null;
        Node prev = null;
        T element;

        Node (T element){
            this.element = element;
        }
    }

    //Head = første node, tail = siste node.
    protected Node head = null;
    protected Node tail = null;
    protected int numNodes = 0;

    public int stoerrelse(){
        return numNodes;
    }

    @Override
    public void leggTil(T element){
        Node newNode = new Node(element);
        
        //Første node
        if(head == null){
            head = newNode;
        }
        //Andre node
        else if(tail == null){
            tail = newNode;
            tail.prev = head;
            head.next = tail;
        }
        //Resterende noder legges bakerst
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        }
        numNodes++;
    }
    
    @Override
    public T hent(){
        if(head == null){
            return null;
        }
        return head.element;
    }

    @Override
    public T fjern(){
        //Fjerner første element i listen og returner det.
        T res;
        if (head == null){
            throw new UgyldigListeindeks(-1);
        }else if(head.next == null){
            res = head.element;

            head = null;
            tail = null;
        }else{
            res = head.element;
            
            head = head.next;
            head.prev = null;
        }
        numNodes--;
        return res;
    }

    @Override
    public String toString(){
        if (head == null){
            return ("Ingen elementer i listen.");
        }
        
        Node node = head;
        String myStr = "";

        myStr += (String) node.element;
        while(node.next != null){
            //System.out.println(node.next.element);
            myStr +=" "+(String) node.next.element;
            node = node.next;
        }
        return myStr;
    }
}
