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