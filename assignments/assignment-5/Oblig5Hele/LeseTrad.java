import java.util.concurrent.CountDownLatch;
import java.io.FileNotFoundException;
import java.io.File;


public class LeseTrad implements Runnable{
    private File file;
    private Monitor1 monitor;
    private CountDownLatch myBarrier;

    public LeseTrad(File file, Monitor1 monitor,CountDownLatch myBarrier){
        this.file = file;
        this.monitor = monitor;
        this.myBarrier = myBarrier;
    }

    public void run(){
        try{
            monitor.push(SubsekvensRegister.readFile(file));
            myBarrier.countDown();
        }
        catch(InterruptedException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
