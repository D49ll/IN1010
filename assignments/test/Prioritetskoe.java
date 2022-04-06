import java.util.Iterator;

/* ********************************************************************
 *
 * IN1010 - Oblig 3 - Datastrukturer
 * chrifren@ifi.uio.no, 6 mars 2022
 * 
 * Implementasjon av ordnet lenket liste.
 * 
 * For å spare meg selv for gitt arbeid og minimere behov for testing
 * og vedlikehold baserer jeg denne på IndeksertListe som har
 * funksjonaliteten som er nødvendig for å sette inn på en spesifikk
 * indeks
 * 
 * ***************************************************************** */

 public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    @Override
    public T hent() {
        return hentFoerste();
    }

    @Override
    public T fjern() throws UgyldigListeindeks {
       return fjernFoerste();
    }
    
    @Override
    public void leggTil(T x) {
        if ( _c == 0 ) {
            leggTilFoerst(x);
        } else {
            Node e = _f;
            while( e != null ) {
                if ( x.compareTo( e._t ) <= 0 ) {
                    Node n = new Node(x, e._f, e);
                    if ( e._f == null ) _f = n; else e._f._n = n;
                    e._f = n;
                    _c++;
                    return;
                }
                e = e._n;
            }
            // større enn alle, legg til sist
            leggTilSist(x);
        }
    }

    public static void main(String[] args) {
        Prioritetskoe<Integer> l = new Prioritetskoe<>();
        l.leggTil(3);
        l.leggTil(2);
        l.leggTil(1);
        l.leggTil(4);
        
        Iterator<Integer> it = l.iterator();
        while( it.hasNext() ) {
            System.out.println(it.next());
        }
    }
}