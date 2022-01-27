import java.util.Scanner;
import java.io.File;

class Gruppeoppmoete {
    private String[] navn = new String[14];
    private boolean[] oppmote = new boolean[14];
    private int antStudenter = 0;

    // Fyll inn med metodene nevnt over.
    public static void main(String[] args) {
        Gruppeoppmoete gruppe = new Gruppeoppmoete();
        gruppe.lesFil("gruppeliste.txt");
        gruppe.registrerOppmote();
        gruppe.printStudenter();
    
    }

    private void printStudenter(){
        for(int i = 0; i < antStudenter; i++){
            if(oppmote[i]){
                System.out.println(navn[i]+" har møtt.");

            }else{
                System.out.println(navn[i]+" har IKKE møtt.");
            }
        }
    }

    private void lesFil(String filnavn){
        try {
            Scanner file = new Scanner(new File(filnavn));

            for(int i = 0; file.hasNextLine() && antStudenter<navn.length; i++){
                navn[i] = file.nextLine();
                antStudenter++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void registrerOppmote(){
        //for enkelhetensskyld sier vi annenhver student har møtt
        for(int i = 0; i < oppmote.length; i++){
            if ((i%2==0)){
                oppmote[i] = true;
            }else{
                oppmote[i] = false;
            }
        }
    }
}