public class IndeksertListe<T> extends LenkeListe<T> {
    //I denne klassen bruker vi overload isteden for override i de ulike metodene
    //Det betyr at det vil finnes dobbelt av hver metode, en med argumenter og en uten.

    public void leggTil(int pos, T element) throws UgyldigListeindeks{
        //element settes inn i liste[pos]
        // 0 <= pos <= storrelse()
        //Husk at indeksen til elementene bak innsettingen må økes
        if(pos < 0 || pos > stoerrelse()){throw new UgyldigListeindeks(pos);}
        
        Node newNode = new Node(element);

        //Første elementet i listen
        if(pos == 0 && head == null){
            head = newNode;
        }
        //Ett element i listen og man ønsker å sette inn det nye elementet på posisjon 0
        else if(pos == 0 && tail == null){
            newNode.next = head;
            head.prev = newNode;
            tail = head;
            head = newNode;
        }
        //Ett element i listen og man ønsker å sette inn neste element bak første.
        //Da må dette elementet plasseres i tail posisjon.
        else if(pos == 1 && tail == null){
            head.next = newNode;
            newNode.prev = head;
            tail = newNode;
        }
        //Alle tilfeller der man ønsker å sette inn et nytt element på posisjon 0, 
        //gitt at det finnes >2 elementer i listen allerede 
        else if(pos == 0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        //Dersom man ønsker å sette elementet inn på bak siste elementet i listen
        else if (pos == stoerrelse()){
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        //Alle resterende tilfeller
        else{
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
        
        //Starter i første node (head) og itererer til ønsket posisjon
        Node getNode = head;
        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }
        //Erstatter elementet i ønsket posisjon
        getNode.element = element;   
    }

    public T hent(int pos) throws UgyldigListeindeks{
        if(pos < 0 || pos > (stoerrelse()-1)){throw new UgyldigListeindeks(pos);}
        
        //Starter i første node (head) og itererer til ønsket posisjon
        Node getNode = head;
        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }
        //Returnerer elementet i ønsket posisjon
        return getNode.element;
    }

    public T fjern(int pos) throws UgyldigListeindeks{
        if(pos < 0 || pos > (stoerrelse()-1)){throw new UgyldigListeindeks(pos);}

        //Starter i første node (head) og itererer til ønsket posisjon
        Node getNode = head;
        for(int i = 0; i < pos; i++){
            getNode = getNode.next;
        }

        //Mellomlagrer elementet i ønsket posisjon
        T res = getNode.element;

        //Dersom elementet vi fjerner er i head-noden, og det ikke finnes en neste node
        if(pos == 0 && getNode.next == null){
            head = null;
        }
        //Dersom elemenet vi fjerner er i tail-noden, blir noden før tail satt til den nye tail-noden
        else if(pos == stoerrelse()-1){
            getNode.prev.next = null;
        }
        //Alle resterende tilfeller settes fjernes noden vi står i ved at noden før peker på noden etter 
        //aktuell node. Samtidig som noden etter peker på noden før.
        else{
            getNode.next.prev = getNode.prev;
            getNode.prev.next = getNode.next;
        }
        
        numNodes--;

        //Returnerer elementet som ble fjernet
        return res;
    }
}

