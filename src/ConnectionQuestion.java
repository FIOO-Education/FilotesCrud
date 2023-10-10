import java.sql.*;

public class ConnectionQuestion {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public boolean conectar() {
        try {
            String dbPassword = System.getenv("DB_PASSWORD");
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");

            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(
                    dbUrl, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
            return false;
        }
        return true;
    }
    public boolean desconectar () {
        try {
            if (conn != null && !conn.isClosed()) {

                //Desconectando do BD
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertQuestion(Question question){
        try {
            //Abrindo conexão com o banco
            conectar();
            pstmt = conn.prepareStatement("INSERT INTO activities (codquestion, question, codactivity) values(?, ?, ?)");

            //Setando o valor dos parâmetros
            pstmt.setInt(1, question.getCodQuestion());
            pstmt.setString(2, question.getQuestion());
            pstmt.setInt(3, question.getCodActivity());

            pstmt.execute();
            //Executando o comando SQL do PreparedStatement
            desconectar(); //Desconectando do Banco de Dados
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateQuestions( String question, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update questions set question = ? where codquestion = ?");

            //Setando os parâmetros
            pstmt.setString(1, question);
            pstmt.setInt(2, codclass);

            //Executando o comando sql do objeto preparedStatement
            pstmt.execute();

            //Fechando a conexao com o banco
            desconectar();

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteQuestion(int codQuestion){
        try {
            //Abrindo a conexao com o banco
            conectar();

            String removerQuestao = "delete from questions where codquestion = ?";

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(removerQuestao);

            //Setando o valor ao parâmetro
            pstmt.setInt(1, codQuestion);

            // Executando o comando sql do objeto preparedStatement
            pstmt.execute();

            //Fechando a conexao com o banco
            desconectar();
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

}

