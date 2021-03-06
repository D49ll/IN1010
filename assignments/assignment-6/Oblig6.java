import java.io.FileNotFoundException;
import java.util.Scanner;


public class Oblig6 {
    public static void main(String[] arg){
        String filename = new String();
        
        if(arg.length > 0){
            //Argumenter finnes
            filename = arg[0];
        }else{
            System.out.println("usage: java Oblig6 <filename>");
            System.exit(1);
        }

        try{
            Labyrint lab = new Labyrint(filename);
            Scanner keyboard = new Scanner(System.in);

            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            String[] coordinates = keyboard.nextLine().trim().split(" ");

            while(!coordinates[0].equals("-1")){
                try{
                    lab.finnUtveiFra(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    System.out.println("\nUgyldig format. Skriv inn nye koordinater ('-1' for aa avslutte)");
                    coordinates = keyboard.nextLine().trim().split(" ");
                    continue;
                }

                System.out.println("\nSkriv inn nye koordinater ('-1' for aa avslutte)");
                coordinates = keyboard.nextLine().trim().split(" ");
            }
            keyboard.close();
            System.out.println("Avslutter");

        }catch(FileNotFoundException e){
            System.out.println("File "+ filename +" not found");
            System.out.println("Terminating program.");
        }

    }
}
