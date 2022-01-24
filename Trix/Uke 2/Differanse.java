import java.util.Scanner;


public class Differanse {
    public static void main(String[] args){
        int tall1, tall2, diff; 

        Scanner inn = new Scanner(System.in);
        
        System.out.println("x: ");
        tall1 = inn.nextInt();
        System.out.println("y: ");
        tall2 = inn.nextInt();

        diff = tall1 - tall2;

        System.out.println("x-y = "+diff);
    
        inn.close();
    }
}