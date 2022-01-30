//Oppretter en egendefinert unntaks-klasse slik at programmet kan 
//oppdage ugyldige node-parametere.
public class InvalidNodeException extends Exception {
    public InvalidNodeException(String str){
        super(str);
    }
}
