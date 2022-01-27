import java.util.Scanner;

public class SkattIRuritania {
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        double inntekt = Double.parseDouble(keyboard.nextLine());

        if (inntekt < 10000){
            System.out.print("Du må betale "+(inntekt*0.1)+" i skatt");
        } else{
            double del = (inntekt - 10000)*0.3;

            double tot = del + (10000*0.1);

            System.out.print("Du må betale "+tot+" i skatt");
        }

        keyboard.close();
    }
}
