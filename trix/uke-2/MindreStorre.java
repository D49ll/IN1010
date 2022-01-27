import java.util.Scanner;

public class MindreStorre {
    public static void main(String[] args){
        int tall;

        Scanner tastatur = new Scanner(System.in);

        System.out.print("Skriv inn et tall: ");
        tall = Integer.parseInt(tastatur.nextLine());

        if (tall < 10){
            System.out.println("Tallet er mindre enn 10");
        } else if (tall > 20){
            System.out.println("Tallet er større enn 20");
        } else{
            System.out.println("Tallet er mellom 10 og 20");
            }

        tastatur.close();
    }
}
