import java.util.ArrayList;

public class Model {
    private GUI gui;
    private Controller controller;
    private int rows, colums;
    private ArrayList<Tuple> snake = new ArrayList<>();
    private ArrayList<Tuple> treasures = new ArrayList<>();

    public Model(GUI gui, Controller controller, int rows, int colums){
        this.gui = gui;
        this.controller = controller;
        this.rows = rows;
        this.colums = colums;
        placeSnakeHead();
        placeTreasure();
    }

    private void placeSnakeHead(){
        snake.add(new Tuple(5, 5));
        moveSnake();
    }

    private void placeTreasure(){
        int x, y;
        while(treasures.size() < 10){

            y = getTreasurePos(0, 11);
            x = getTreasurePos(0, 11);
            Tuple treasure = new Tuple(x, y);
            
            if(snake.contains(treasure)){
                continue;
            }
            treasures.add(treasure);
            gui.drawTreasure(x, y);
        }

    }

    public void move(Heading heading){
        int headIndex = snake.size()-1;

        int x = snake.get(headIndex).getX();
        int y = snake.get(headIndex).getY();

        if(heading == Heading.NORTH) x--;
        if(heading == Heading.SOUTH) x++;
        
        if(heading == Heading.EAST) y++;
        if(heading == Heading.WEST) y--;
        
        Tuple nextPos = new Tuple(x, y);

        if(treasures.contains(nextPos)){
            treasures.remove(nextPos);
            snake.add(nextPos);
            gui.currentLength(snake.size());
            placeTreasure();
            moveSnake();
        }
        else{
            if(edge(x, y) || snake.contains(nextPos)){
                controller.crash();
            }else{
                Tuple tail = snake.remove(0);
                gui.hasVisit(tail.getX(), tail.getY());
                snake.add(nextPos);
                moveSnake();
            }
        }
    }

    private void moveSnake(){
        for(int i = snake.size()-1; i >= 0; i--){
            if(i == snake.size()-1){
                gui.drawSnake(snake.get(i).getX(),snake.get(i).getY(), "O");
            }
            else{
                gui.drawSnake(snake.get(i).getX(),snake.get(i).getY(), "+");
            }
        }
    }

    private boolean edge(int x, int y){
        if(x < 0 || x >= rows) return true;
        if(y < 0 || y >= colums) return true;
        return false;
    }

    static int getTreasurePos(int start, int end){
        return (int)(Math.random()*(end - (start+1))) + start;
    }
}
