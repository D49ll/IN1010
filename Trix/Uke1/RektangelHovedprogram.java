
class RektangelHovedprogram{
    public static void main(String[] args){
        Rektangel en = new Rektangel(5,9);
        Rektangel to = new Rektangel(10,2);

        System.out.println("Areal1: "+en.areal());
        System.out.println("Areal2: "+to.areal());

        en.oekLengde(1);
        to.oekBredde(1);

        System.out.println("Omkrets1: "+en.omkrets());
        System.out.println("Omkrets2: "+to.omkrets());

            
    }
}
