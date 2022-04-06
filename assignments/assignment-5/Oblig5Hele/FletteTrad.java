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
    protected ArrayList<HashMap<String,Subsekvens>> map = new ArrayList<>();
    protected CountDownLatch myBarrier;

    public FletteTrad(Monitor2 monitor, CountDownLatch myBarrier){
        this.monitor = monitor;
        this.myBarrier = myBarrier;
    }
       
    public void run(){
        try{
            while(monitor.size()>1){
                map = monitor.pop2();
                HashMap<String,Subsekvens> map1 = map.remove(0);
                HashMap<String,Subsekvens> map2 = map.remove(0);
                
                monitor.push(SubsekvensRegister.mergeHashMap(map1, map2));
                myBarrier.countDown();       
            }
        }
        catch(InterruptedException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

}
