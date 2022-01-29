public class Utskrift {
    
    public static void main(String[] args){
        String tekst = "hallo";
        int x = 1, y = 2;

        utskrift(tekst);
        utskrift(x, y);
    }

    public static void utskrift(String tekst){
        System.out.println(tekst);
    }

    public static void utskrift(int x, int y){
        System.out.println((x+y));
    }
}
