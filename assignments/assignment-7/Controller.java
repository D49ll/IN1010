public class Controller {
    private GUI gui;
    private Model model;
    private Thread thread;
    private int rows = 12, colums = 12;
    private Heading heading = Heading.SOUTH;

    public Controller(){
        gui = new GUI(this, rows, colums);
        model = new Model(gui, this, rows, colums);
        thread = new Thread(new snakeSpeed(200)); //in ms
        thread.start();

    }

    class snakeSpeed implements Runnable{
        int delay;
        snakeSpeed(int delay){
            this.delay = delay;
        }

        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(delay);
                }catch (Exception e){return;}
                move();
            }
        }
    }

    public void exitGame(){
        System.exit(0);
    }

    public void move(){
        model.move(heading);
    }

    public void setHeading(Heading heading){
        this.heading = heading;
    }

    public void end(){
        thread.interrupt();
        System.exit(0);
    }

    public void crash(){
        gui.changeTextStopButton();
        thread.interrupt();
    }

    public static void main(String[] args) {
        new Controller();
    }

}
