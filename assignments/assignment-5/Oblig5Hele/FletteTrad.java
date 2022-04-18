import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;


/** FletteTrad
 * 1: Henter 2 HashMap-er fra et monitor-objekt
 * 2: Fletter HashMapene
 * 3: Returnerer resultatet til monitoren
 * 
 * OBS: Når det er én HashMap igjen i monitoren termineres trådene
 * OBS: FletteTrad har max 8 trader tilgjengelig
 */

public class FletteTrad implements Runnable{
    protected Monitor2 monitor;
    protected CountDownLatch myBarrier;
    protected int activeThreads;

    public FletteTrad(Monitor2 monitor, CountDownLatch myBarrier, int MAXTHREADS){
        this.monitor = monitor;
        this.myBarrier = myBarrier;
        this.activeThreads = MAXTHREADS;
    }
    
    public void run(){
        try{
            while(myBarrier.getCount() != 0){
                ArrayList<HashMap<String,Subsekvens>> map = monitor.pop2();
                HashMap<String,Subsekvens> map1 = map.remove(0);
                HashMap<String,Subsekvens> map2 = map.remove(0);
                
                monitor.push(SubsekvensRegister.mergeHashMap(map1, map2));
                myBarrier.countDown();

                //Dersom antall aksjoner (getCount) gjenværende er mindre enn 
                //antall aktive tråder kan vi terminere denne tråden.
                if(myBarrier.getCount()<activeThreads)
                    break;
            }
        }
        catch(InterruptedException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

}
