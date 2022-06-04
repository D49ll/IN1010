import java.util.Iterator;

public class Hund implements Comparable<Hund>{
    String navn;
    Kull mittKull;
    Tidspunkt minFodselstid;
    Hund neste = null;

    Hund(Kull k, String navn, Tidspunkt fodt){
        this.navn = navn;
        mittKull = k;
        minFodselstid = fodt;
    }    

    @Override
    public int compareTo(Hund other){
        //Oppgave 2b
        /*En hund som er født tidligst kommer først i ordningen */
        return this.minFodselstid.compareTo(other.minFodselstid);
    }

    public Hund mor(){
        //Oppgave 2a
        /*Returner moren, dersom ikke kjent returner null */
        return mittKull.mor;
    }

    public Hund far(){
        //Oppgave 2a
        /*Returner faren, dersom ikke kjent returner null */
        return mittKull.far;
    }

    public boolean erHelsosken(Hund h){
        //Oppgave 2c
        /*Er helsøsken om de har samme mor og far. Antar da at både mor og får må være kjent*/
        return (mor() != null && far() != null && mor() == h.mor() && far() == h.far());
    }

    public boolean erHalvsosken(Hund h){
        //Oppgave 2c
        /*Er halvsøsken om de har enten samme mor eller far*/
        return ( mor() != null && mor() == h.mor() )||( far() != null && far() == h.far() ) && !erHelsosken(h);
    }

    public Hund finnEldsteKjenteOpphav(){
        //Oppgave 2d
        if(mor() == null){
            if(far() == null){
                return this;
            }
            far().finnEldsteKjenteOpphav();
        }

        if(far() == null){
            if(mor() == null){
                return this;
            }
            mor().finnEldsteKjenteOpphav();
        }

        Hund morsOpphav = mor().finnEldsteKjenteOpphav();
        Hund farsOpphav = far().finnEldsteKjenteOpphav();

        if(morsOpphav.compareTo(farsOpphav) > 0){
            //Da vil moren være fodt etter faren. Dvs faren er eldst
            return farsOpphav;
        }else if(morsOpphav.compareTo(farsOpphav) < 0){
            //Da vil faren være født etter moren, Dvs faren er eldst
            return morsOpphav;
        }else{
            //Mor og far født samtidig. Likegyldig hvem av dem som blir opphavet
            return morsOpphav;
        }


    }

    public int getIndex(){
        return minFodselstid.getSec();
    }


    public static void main(String[] args){
        KullArray test = new KullArray(null, null);
    }
}

class Tidspunkt implements Comparable<Tidspunkt>{
    int aar, mnd, dag, time, min, sek;

    public Tidspunkt(int aar, int mnd, int dag, int time, int min, int sek){
        this.aar = aar;
        this.mnd = mnd;
        this.dag = dag;
        this.time = time;
        this.min = min;
        this.sek = sek;
    }

    public int getSec(){
        return sek;
    }

    @Override
    public int compareTo(Tidspunkt other){
        /* this.compareTo(other)
         * Derson this er en dato tidligere enn other -> 0<
         * Dersom this er en senere dato -> 0>
         * Dersom this er lik other -> 0
         *
         * Fasit regnet med differansen mellom to datoer*/

        //Eks 1991 - 1997 = -6 -> betyr at 
        if(aar != other.aar) return aar-other.aar;
        if(mnd != other.mnd) return mnd-other.mnd;
        if(dag != other.dag) return dag-other.dag;
        if(time != other.time) return time-other.time;
        if(min != other.min) return min-other.min;
        return sek-other.sek;
    }
}

abstract class Kull implements Iterable<Hund>{
    Hund mor, far;

    Kull(Hund mor, Hund far){
        this.mor = mor;
        this.far = far;
    }

    public abstract void settInn(Hund h);
    public abstract Iterator<Hund> iterator();
}

class KullListe extends Kull{
    Hund head = null;

    //Denne må vi ha, for elles blir det kalt en super()-tom.
    KullListe (Hund mor, Hund far){
        super(mor,far);
    }

    /*Prioritetsliste, bruker ikke @Override når man arver en abstract funksjon*/
    public void settInn(Hund h){
        /*For å vedlikeholde med listen slik at de yngste kommer først er det 3 tilfeller:
         * 1. Listen er tom -> minste element = head
         * 2. Elementet som settes inn er yngre enn head -> Elementet blir ny head
         * 3. Alle andre tilfeller -> Lete gjennom hele listen
         * */
        if(head == null){
            head = h;
            return;
        }
        /*  h - head > 0, da vil h være yngst
         *  1997 - 1991 = 6
         */  
        if(h.compareTo(head) > 0){
            h.neste = head;
            head = h;
            return;
        }
        Hund p = head;
        while(true){
            if(p.neste == null){
                //p er siste element i listen. Dersom man er her så er alle elementer før dette yngre enn h
                p.neste = h;
                break;
            }else if(h.compareTo(p.neste) > 0){
                //Dersom dette er sant er h yngre enn p.neste, men eldre enn p.
                h.neste = p.neste;
                p.neste = h;
                break;
            }else{
                //h er ikke yngre enn p.neste. p.neste er ikke lik null. Let videre
                p = p.neste;
            }
        }
    }

    class ListeIterator implements Iterator<Hund>{
        private Hund p = head;

        @Override
        //Så lenge denne ikke er null, så finnes dette elementet og muligens et neste
        public boolean hasNext(){
            // Returns true if the iteration has more elements. (In other words, returns true if next() would return an element rather than throwing an exception.)
            return p != null;
        }

        @Override
        public Hund next(){
            // Returns the next element in the iteration.
            Hund svar = p;

            //Hopper til neste element
            p = p.neste;

            return svar;
        }
    }
    public Iterator<Hund> iterator(){
        return new ListeIterator();
    }
}

class KullArray extends Kull{
    Hund[] hunder = new Hund[60];

    KullArray(Hund mor, Hund far){
        super(mor, far);

        for(int i = 0; i < 60; i++){
            System.out.println(hunder[i]);
        }
    }

    public void settInn(Hund h){
        int index = h.getIndex();

        h.neste = hunder[index];
        hunder[index] = h;

        // if(hunder[index] == null){
        //     hunder[index] = h;
        // }else{
        //     h.neste = hunder[index];
        //     hunder[index] = h;
        // }
    }

    public void skrivUtAlle(){
        for(int i = 0; i < 60; i++){
            if(hunder[i] != null){
                Hund p = hunder[i];
                p = p.neste;
                while(p != null){
                    p = p.neste;
                }
            }
        }
    }

    public Iterator<Hund> iterator(){
        return null;
    }

}
