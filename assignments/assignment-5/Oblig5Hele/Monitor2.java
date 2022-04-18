import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.CountDownLatch;

public class Monitor2 extends Monitor1{
    Condition notTwo = l.newCondition();
    CountDownLatch myBarrier;
    boolean finished = true;
    
    public Monitor2(CountDownLatch myBarrier){
        this.myBarrier = myBarrier;
    }

    @Override
    public void push(HashMap<String,Subsekvens> m) throws InterruptedException{
        l.lock();
        try{
            subSekvenser.push(m);
            if(subSekvenser.size()>=2){
                notTwo.signalAll();
            }        
        }
        finally{l.unlock();}
    }

    public ArrayList<HashMap<String, Subsekvens>> pop2(){
        ArrayList<HashMap<String, Subsekvens>> test = new ArrayList<>();
        l.lock();
        try{
            //For å poppe 2 hashmapper må det faktisk finnes to.
            while(subSekvenser.size()<2) {
                //Venter her til signalAll() blir aktivt i push().
                // System.out.println("await");
                notTwo.await();
            }
            test.add(subSekvenser.pop(0));
            test.add(subSekvenser.pop(0));           

            return test;
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally{l.unlock();}
        return test;
    }

    public HashMap<String, Subsekvens> get(int index) throws InterruptedException{
        l.lock();
        try{
            return subSekvenser.get(index);
        }
        finally{l.unlock();}
    }

}
