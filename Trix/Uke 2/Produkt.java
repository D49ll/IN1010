import java.util.Scanner;

public class Produkt {
    public static void main(String[] args){
        int x,y,p;
        Scanner in = new Scanner(System.in);

        System.out.print("x: ");
        x = in.nextInt();

        System.out.print("y: ");
        y = in.nextInt();

        p = x * y;

        System.out.println("x*y = "+p);

        in.close();
    }
}
