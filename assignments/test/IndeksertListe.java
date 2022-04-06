import java.util.Iterator;

/* ********************************************************************
 *
 * IN1010 - Oblig 3 - Datastrukturer
 * chrifren@ifi.uio.no, 6 mars 2022
 * 
 * Implementasjon av IndeksertListe, underliggende Lenkeliste
 * men støtte for indeksering og å hente ut/legge til elementer
 * på spesifiserte plasser.
 * 
 * ***************************************************************** */

public class IndeksertListe<T> extends Lenkeliste<T> {
    @Override
    public T hent() {
        return hentFoerste();
    }
    
    @Override
    public void leggTil(T x) {
        leggTilSist(x);
    }

    @Override
    public T fjern() throws UgyldigListeindeks {
       return fjernFoerste();
    }

    protected Node hentElementIx(int pos) {
        // sjekk om det er noen elementer i listen
        assert( _c > 0 );
        if ( _c == 0 ) throw new UgyldigListeindeks(pos);
        // ingen grunn til å traversere listen for å finne ut dette
        if ( pos > _c - 1) throw new UgyldigListeindeks(pos);

        // Merk: her er det en mulig optimalisering som jeg ikke
        // har regnet i skop for oblig'en. Om man ber om en pos
        // som er >_c/2 så vil det lønne seg å iterere baklengs
        // fra _s for å minimere antall steg (istedetfor fremover
        // fra _f som jeg gjør her.)

        Node e = _f;
        int i = 0;
        while( e != null ) {
            if ( i == pos ) {
                return e;
            } else {
                e = e._n;
                i++;
            }
        }

        // Vi er utenfor pos (vi kommer aldri hit pga if-sjekk #2 over)
        // men kompilatoren vet ikke det så tar med denne for å unngå
        // warnings

        throw new UgyldigListeindeks(pos);
    }

    public void leggTil(int pos, T x) {
        // sjekk om vi er out of bounds
        assert( pos <= _c );
        if ( pos > _c ) throw new UgyldigListeindeks(pos);

        // to edge caser: legg til først og sist,
        // ellers traverserer vi listen og setter inn i "midten"
        // eller riktigere før elementet på gitt posisjon
        if ( pos == 0 ) {
            leggTilFoerst(x);
        } else if ( pos == _c ) {
            leggTilSist(x);
        } else {
            Node e = hentElementIx(pos);
            Node n = new Node(x, e._f, e);
            
            assert(e._f != null ); // skulle vært håndtert av leggTilFoerst og if-case over
            e._f._n = n;
            e._f = n;

            _c++;
        }
    }

    public void sett(int pos, T x) {
        hentElementIx(pos)._t = x;
    }

    public T hent(int pos) {
        return hentElementIx(pos)._t;
    }

    public T fjern(int pos) {
        // håndter spesialcasene med første og siste
        if ( pos == 0 ) return fjernFoerste();
        if ( pos == _c - 1 ) return fjernSiste();
        
        // vi skal fjerne et element i midten av dequeue't
        Node e = hentElementIx(pos);

        e._f._n = e._n;
        e._n._f = e._f;

        return e._t;
    }

    // Egne tester av indeksertliste
    public static void main(String[] args) {
        
        IndeksertListe<Integer> l = new IndeksertListe<>();
        /*l.leggTil(1);
        l.leggTil(2);
        l.leggTil(3);

        System.out.println(l.hent(0));
        l.fjern(0);


        l = new IndeksertListe<>();
        l.leggTil(1);
        l.leggTil(2);
        l.leggTil(3);

        System.out.println(l.hent(1));
        l.fjern(1);


        l = new IndeksertListe<>();
        l.leggTil(1);
        l.leggTil(2);
        l.leggTil(3);

        System.out.println(l.hent(2));
        l.fjern(2);


        l = new IndeksertListe<>();
        l.leggTil(0, 1);
        l.leggTil(0, 2);
        l.leggTil(0, 33);

        System.out.println(l.hent(2));
        l.fjern(2);*/


        // Test iterator

        l = new IndeksertListe<>();
        l.leggTil(0, 1);
        l.leggTil(0, 2);
        l.leggTil(0, 33);

        Iterator<Integer> it = l.iterator();
        while( it.hasNext() ) {
            System.out.println(it.next());
        }
    }
}
