import java.util.Iterator;

/* ********************************************************************
 *
 * IN1010 - Oblig 3 - Datastrukturer
 * chrifren@ifi.uio.no, 6 mars 2022
 * 
 * Implementasjon av lenket liste med mulighet for
 * arv til både fifo og filo og evnt andre klasser.
 * 
 * Implementasjonen benytter pattern fra en double ended queue 
 * (dequeue) og optimalisering på antall elementer i køen
 * 
 * Denne er ikke trådsikker eller optimalisert for samtidig aksess,
 * og brukeren må derfor synkronisere tilgang til objekter på tvers 
 * av tråder slik at operasjonene kjøres atomisk.
 * 
 * I utgangspunktet hadde ikke denne trengt å være atomisk, men
 * det krever oblig og det gir også penere og mere forståelige
 * konkrete implementasjoner (stack,queue,etc.)
 * 
 * ***************************************************************** */

abstract public class Lenkeliste<T> implements Liste<T> {
    // Container for ting i listen
    protected class Node {
        public T _t; // tingen vi holder
        public Node _f; // forrige element
        public Node _n; // neste element

        public Node(T t, Node f) {
            _t = t;
            _f = f;
            _n = null;
        }

        public Node(T t, Node f, Node n) {
            _t = t;
            _f = f;
            _n = n;
        }
    }

    // Datstruktur for Lenkeliste, vi holder styr på
    // første, siste og antall elementer
    protected Node _f; // første element
    protected Node _s; // siste element
    protected int _c; // antall elementer

    // Konstruktør, vi starter tomt
    public Lenkeliste() {
        _f = null;
        _s = null;
        _c = 0;
    }

    protected void leggTilFoerst(T x) {
        if ( _f == null ) {
            assert( _c == 0 );
            // legger til første element i listen
            _f = _s = new Node(x, null);
            _c = 1;
        } else {
            assert( _c > 0 );
            // Legger til nytt element på starten av listen
            assert(_f._f == null);
            _f._f = new Node(x,null,_f);
            _f = _f._f;
            _c++;
        }
    }

    protected void leggTilSist(T x) {
        if ( _s == null ) {
            assert( _c == 0 );
            // legger til første element
            _f = _s = new Node(x, null);
            _c = 1;
        } else {
            assert( _c > 0 );
            // legger til nytt element på slutten av listen
            _s._n = new Node(x, _s, null);
            _s = _s._n;
            _c++;
        }
    }

    protected T fjernFoerste() throws UgyldigListeindeks {
        assert( _f != null );
        if ( _f == null ) throw new UgyldigListeindeks(-1);

        if ( _f.equals(_s) ) {
            // ett element i listen
            assert( _c == 1 );
            
            T t = _f._t;
            _f = _s = null;
            _c = 0;

            return t;
        } else {
            // mer enn ett element i listen
            assert( _c > 1 );

            T t = _f._t;
            _f = _f._n;
            _f._f = null; // ikke lenger noen forrige peker nå for det første elementet
            _c--;

            return t;
        }
    }

    protected T fjernSiste() throws UgyldigListeindeks {
        assert( _s != null );
        if ( _s == null ) throw new UgyldigListeindeks(-1);

        if ( _s.equals(_f) ) {
            // ett element i listen
            assert( _c == 1 );
            
            T t = _s._t;
            _f = _s = null;
            _c = 0;

            return t;
        } else {
            // mer enn ett element i listen
            assert( _c > 1 );

            T t = _s._t;
            _s = _s._f;
            _s._n = null; // ikke lenger noen neste peker nå for det siste elementet

            _c--;
            return t;
        }
    }

    protected T hentFoerste() throws UgyldigListeindeks {
        assert( _f != null );
        if ( _f == null ) throw new UgyldigListeindeks(-1);

        return _f._t;
    }

    protected T hentSiste() throws UgyldigListeindeks {
        assert( _s != null );
        if ( _s == null ) throw new UgyldigListeindeks(-1);

        return _s._t;
    }

    @Override
    public int stoerrelse() {
        return _c;
    }

    @Override 
    public String toString() {
        String s = "";
        Node e = _f;
        while( e != null ) {
            if ( s.length() > 0 ) s += ", ";
            s += e._t.toString();
            //s += String.format("(%s,%s)", e._f, e._n);
            e = e._n;
        }
        return s;
    }

    /* 
        Implementerer Iterable<T>

        Vi har ikke implementert hent(), fjern() og leggTil() fordi de avhenger
        av typen liste vi skal implementere, disse må derfor implementeres i klasser
        som arver fra denne.

        Men iterator() vil være lik, vi vil uansett bevege oss fra starten til slutten
        av listen for de fleste underklasser (med unntak av stabel som må override
        denne og bevege seg baklengs gjennom listen, men den brukes ikke i Oblig 4
        så det utsetter vi.)
    */

    protected class LenkelisteIterator implements Iterator<T> {
        Node _e;

        public LenkelisteIterator(Node e) {
            _e = e;
        }

        @Override
        public boolean hasNext() {
            return _e != null;
        }

        @Override
        public T next() {
            T t = _e._t;
            _e = _e._n;
            return t;
        }

        @Override
        public void remove() {
            // Velger å ikke implementere denne for denne oblig'en, siden den ikke
            // er nødvendig. Siden vi har en deque som underliggende struktur 
            // vil det være enkelt å implementere denne.
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator(_f);
    }
}