
public class brukStudent{
    public static void main(String[] args){
        Student[] studenter = new Student[3];

        studenter[0] = new Student("Joakim");
        studenter[1] = new Student("Kristin");
        studenter[2] = new Student("Dag");


        for (int i = 0; i < studenter.length; i++){
            for (int j = 0; j < 3; j++){
                studenter[i].leggTilQuizScore(5);
            }
        }

        for (int i = 0; i < studenter.length; i++){
            System.out.println(studenter[i].hentNavn()+" har en total score på "+studenter[i].hentTotalScore()+" og en gjennomsnitt på "+studenter[i].hentGjennomsnittScore());
        }

    }
}

class Student {
    private int totScore = 0, antQuiz = 0;
    private String navn;

    public Student(String n){
        this.navn = n;
    }

    public String hentNavn(){
        return this.navn;
    }

    public void leggTilQuizScore(int score){
        this.totScore += score;
        this.antQuiz++;
    }

    public int hentTotalScore(){
        return this.totScore;
    }

    public float hentGjennomsnittScore(){
        return this.totScore/this.antQuiz;
    }

}
