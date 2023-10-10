public class Activity {

    private int codActivity;
    private String title;
    private String image;
    private String subject;
    private int codClass;

    //Método Construtor
    public Activity(int codActivity, String title, String image, String subject, int codClass){
        this.codActivity = codActivity;
        this.title = title;
        this.image = image;
        this.subject = subject;
        this.codClass = codClass;
    }

    //Get
    public int getCodActivity() {
        return codActivity;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getSubject() {
        return subject;
    }

    public int getCodClass() {
        return codClass;
    }


}
