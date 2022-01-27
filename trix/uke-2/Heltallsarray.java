public class Heltallsarray {
    
    public static void main(String[] args){
        int a=0, b=1, c=2, d=3, e=4;
        
        System.out.println("Printer heltallsvariablene:");
        System.out.println("a="+a+" b="+b+" c="+c+" d="+d+" e="+e);
    
        //Create a int-array
        int[] arr = new int[5];

        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }

        System.out.println("Printer arrayet:");

        for(int val : arr){
            System.out.println("arr["+val+"]="+val);
        }
    }
}
