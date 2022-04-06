import java.util.concurrent.CountDownLatch;
import java.io.FileNotFoundException;
import java.io.File;


public class LeseTrad implements Runnable{
    private File file;
    private Monitor1 monitor;
    private CountDownLatch myBarrier;
    private int threadNum;

    public LeseTrad(File file, Monitor1 monitor,CountDownLatch myBarrier, int threadNum){
        this.file = file;
        this.monitor = monitor;
        this.myBarrier = myBarrier;
        this.threadNum = threadNum;
    }

    public void run(){
        try{
            monitor.push(SubsekvensRegister.readFile(file));
            myBarrier.countDown();
            // System.out.println("Thread "+threadNum+" done.");
        }
        catch(InterruptedException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
