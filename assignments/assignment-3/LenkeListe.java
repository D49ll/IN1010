public abstract class LenkeListe<T> implements Liste<T>{
    //Oppretter en intern klasse, Node, som holder styr på elementene i den doble lenkede listen
    class Node{
        Node next = null;
        Node prev = null;
        T element;

        Node (T element){
            this.element = element;
        }
    }

    //Head = første node
    //tail = siste node
    protected Node head = null;
    protected Node tail = null;
    protected int numNodes = 0;

    public int stoerrelse(){
        return numNodes;
    }

    @Override
    public void leggTil(T element){
        //Nye elementer legges alltid bakerst i listen, dvs alltid i tail-noden
        Node newNode = new Node(element);
        
        //Første element i listen
        if(head == null){
            head = newNode;
        }
        //Andre element i listen
        else if(tail == null){
            tail = newNode;
            tail.prev = head;
            head.next = tail;
        }
        //Resterende elementer
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
    public T fjern()throws UgyldigListeindeks{
        //Fjerner første element i listen og returner det.
        T res;

        //Dersom det ikke finnes noen noder i listen
        if (head == null){throw new UgyldigListeindeks(-1);}
        
        //Dersom det finnes kun en node i listen
        if(head.next == null){
            res = head.element;
            head = null;
        }
        //Dersom det finnes kun to noder i listen
        else if(head.next.equals(tail)){
            res = head.element;

            head = tail;
            tail = null;
        }
        //Alle resterende tilfeller
        else{
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
        
        String myStr = "Elementer i listen: ";

        myStr += node.element;
        while(node.next != null){
            myStr +=" "+ node.next.element;
            node = node.next;
        }
        return myStr;
    }
}
