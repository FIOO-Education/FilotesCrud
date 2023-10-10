public class Classroom {
    //Nome da classe alterado por motivos de interferência com o comando
    //Class.forName("org.postgresql.Driver");

    private int codClass;
    private String nameClass;
    private String title;
    private String description;
    private String videoLink;
    private String subject;

    //Método Contrutor
    public Classroom(int codClass, String nameClass, String title, String description, String videoLink, String subject) {
        this.codClass = codClass;
        this.nameClass = nameClass;
        this.title = title;
        this.description = description;
        this.videoLink = videoLink;
        this.subject = subject;
    }

    //Get

    public int getCodClass() {
        return codClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getSubject() {
        return subject;
    }
}
