public class IndeksertListe<T> extends LenkeListe<T> {
    //I denne klassen bruker vi overload isteden for override i de ulike metodene
    //Det betyr at det vil finnes dobbelt av hver metode, en med argumenter og en uten.

    public void leggTil(int pos, T element) throws UgyldigListeindeks{
        //element settes inn i liste[pos]
        // 0 <= pos <= storrelse()
        //Husk at indeksen til elementene bak innsettingen må økes
        if(pos < 0 || pos > stoerrelse()){throw new UgyldigListeindeks(pos);}

        Node newNode = new Node(element);

        if(pos == 0 && head == null){
            head = newNode;
        }else if(pos == 0 && tail == null){
            newNode.next = head;
            head.prev = newNode;
            tail = head;
            head = newNode;
        }else if(pos == 1 && tail == null){
            head.next = newNode;
            newNode.prev = head;
            tail = newNode;
        }else if(pos == 0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }else if (pos == stoerrelse()){
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }else{
            Node indexNode = head;
            for(int i = 0; i < pos; i++){
                indexNode = indexNode.next;
            }
            newNode.prev = indexNode.prev;
            newNode.next = indexNode;
            indexNode.prev.next = newNode;
            indexNode.prev = newNode;
        }
        numNodes++;
    }

    public void sett(int pos, T element) throws UgyldigListeindeks{
        if(pos < 0 || pos > (stoerrelse()-1)){throw new UgyldigListeindeks(pos);}
        
        Node getNode = head;
        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }
        getNode.element = element;   
    }

    public T hent(int pos) throws UgyldigListeindeks{
        if(pos < 0 || pos > (stoerrelse()-1)){throw new UgyldigListeindeks(pos);}
        
        Node getNode = head;
        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }
        return getNode.element;
    }

    public T fjern(int pos) throws UgyldigListeindeks{
        if(pos < 0 || pos > (stoerrelse()-1)){throw new UgyldigListeindeks(pos);}
        Node getNode = head;

        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }
        T res = getNode.element;

        if(pos == 0 && getNode.next == null){
            head = null;
        }else if(pos == stoerrelse()-1 && getNode.next == null){
            getNode.prev.next = null;
        }else{
            getNode.next.prev = getNode.prev;
            getNode.prev.next = getNode.next;
        }
        
        numNodes--;
        return res;
    }
}

