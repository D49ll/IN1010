public class Prioritetskoe<T extends Comparable<T>> extends LenkeListe<T> {
        @Override
        public void leggTil(T element){
            if(head == null){
                head = new Node(element);
            }else if(tail == null){
                if (head.element > element){

                }
            }
        }

        @Override
        public T hent(){
            return null;
        }

        @Override
        public T fjern(){
            return null;
        }

        public int compareTo(T other){
            if(T  > other){

            }
        }


}
