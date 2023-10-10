public class Alternative {
    private int codAlternative;
    private String alternative;
    private boolean correct;
    private int codQuestion;


    public Alternative(int codAlternative, String alternative, boolean correct, int codQuestion) {
        this.codAlternative = codAlternative;
        this.alternative = alternative;
        this.correct = correct;
        this.codQuestion = codQuestion;
    }


    //Get
    public int getCodAlternative() {
        return codAlternative;
    }

    public String getAlternative() {
        return alternative;
    }

    public boolean getCorrect() {
        return correct;
    }

    public int getCodQuestion() {
        return codQuestion;
    }


}
