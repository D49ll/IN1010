public class Koe<T> extends LenkeListe<T> {
    //                 KØ
    //             _ _ _ _ _ _
    //  fjern() <-|_|_|_|_|_|_|<- leggTil()
    //         head^     tail^      
    
    //fjern() fjerner alltid det første elementet, head.
    //head er det elementet som har vært i listen lengst

    //leggTil() legger alltid til bak det siste elementet, tail
    //tail er det elementet som har vært i listen kortest
    
    //Det betyr at klassen LenkeListe allerede følger en FIFO struktur
    //Dermed er det ikke behov for å lage noen nye metoder
}
