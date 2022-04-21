import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private Rute[][] ruter;
    protected int rows, colums;

    public Labyrint(String filename) throws FileNotFoundException{
        Scanner stream = new Scanner(new File(filename));

        String[] firstLine = stream.nextLine().split(" ");
        colums = Integer.parseInt(firstLine[1]);
        rows = Integer.parseInt(firstLine[0]);
        ruter = new Rute[rows][colums];

        //Rows
        for(int y = 0; y < rows ; y++){
            String line = stream.nextLine();
            //Colums
            for(int x = 0; x < colums; x++){
                if(line.charAt(x) == '.')
                    if(isOuterWindow(y, x))
                        ruter[y][x] = new Aapning(y, x, this);
                    else
                        ruter[y][x] = new HvitRute(y, x, this);
                else
                    ruter[y][x] = new SvartRute(y, x, this);
            }
        }
        setNeighbors();

        System.out.println("Labyrint:");
        System.out.println(this);
        System.out.println();
    }

    @Override
    public String toString(){
        String labyrinten = "";
        for(int y = 0; y < rows; y++){
            String linje = "";
            for(int x = 0; x < colums; x++)
                linje += ruter[y][x].toString();
            if(y < rows-1)
                labyrinten+=linje+'\n';
            else
                labyrinten+=linje;
        }
        return labyrinten;
    }

    public void finnUtveiFra(int rad, int kol){
        //rad = y
        //kol = x

        System.out.println("Aapninger:");
        ruter[rad][kol].finn(null);
    }
    
    /*****************
     * 
     * Private methods
     * 
     *****************/
    private void setNeighbors(){
        //Top row
        for(int x = 0; x < colums; x++){
            if(isOuterRightColum(x))
                ruter[0][x].setNeighbors(ruter[0][x-1], null,ruter[1][x],null);
            else if(isOuterLeftColum(x))
                ruter[0][x].setNeighbors(null, null,ruter[1][x],ruter[0][x+1]);
            else
                ruter[0][x].setNeighbors(ruter[0][x-1],null,ruter[1][x],ruter[0][x+1]);
        }

        //Bottom row
        for(int x = 0; x < colums; x++){
            if(isOuterRightColum(x))
                ruter[rows-1][x].setNeighbors(ruter[rows-1][x-1], ruter[rows-2][x],null,null);
            else if(isOuterLeftColum(x))
                ruter[rows-1][x].setNeighbors(null, ruter[rows-2][x],null,ruter[rows-1][x+1]);
            else
                ruter[rows-1][x].setNeighbors(ruter[rows-1][x-1],ruter[rows-2][x],null,ruter[0][x+1]);
        }
        
        //Remaining windows
        for(int y = 1; y < rows-1 ; y++){
            for(int x = 0; x < colums; x++){
                if(isOuterLeftColum(x)){
                    ruter[y][x].setNeighbors(null, ruter[y-1][x], ruter[y+1][x], ruter[y][x+1]);
                }else if(isOuterRightColum(x)){
                    ruter[y][x].setNeighbors(ruter[y][x-1], ruter[y-1][x], ruter[y+1][x], null);
                }else{
                    ruter[y][x].setNeighbors(ruter[y][x-1], ruter[y-1][x], ruter[y+1][x], ruter[y][x+1]);
                }
            }
        }
    }

    private boolean isOuterLeftColum(int x){
        return (x-1 < 0);
    }
    private boolean isOuterRightColum(int x){
        return (x+1 > colums-1 );
    }
    private boolean isOuterTopRow(int y){
        return (y-1 < 0);
    }
    private boolean isOuterBottomRow(int y){
        return (y+1 > rows-1);
    }
    private boolean isOuterWindow(int y,int x){
        return (isOuterBottomRow(y) || isOuterTopRow(y) || isOuterLeftColum(x) || isOuterRightColum(x));
    }
}
