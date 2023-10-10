import java.sql.*;

public class Question {
    private int codQuestion;
    private String question;
    private int codActivity;


    //Método Construtor
    public Question(int codQuestion, String question, int codActivity){
        this.codQuestion = codQuestion;
        this.question = question;
        this.codActivity = codActivity;
    }

    //Get
    public int getCodQuestion() {
        return codQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public int getCodActivity() {
        return codActivity;
    }

}
