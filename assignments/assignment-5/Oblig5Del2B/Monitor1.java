import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

public class Monitor1{
    protected SubsekvensRegister subSekvenser = new SubsekvensRegister();
    protected Lock l = new ReentrantLock();

    public void push(HashMap<String,Subsekvens> m) throws InterruptedException{
        l.lock();
        try{
            subSekvenser.push(m);
        }
        finally{l.unlock();}
    }

    public int size() throws InterruptedException{
        l.lock();
        try{
            return subSekvenser.size();
        }
        finally{l.unlock();}
    }

    public HashMap<String, Subsekvens> pop(int index) throws InterruptedException{
        l.lock();
        try{
            return subSekvenser.pop(index);
        }
        finally{l.unlock();}
    }
}
