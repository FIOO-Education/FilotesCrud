import java.sql.*;

public class ConnectionAlternative {
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

    public boolean insertAlternative(Alternative alternative){
        try {
            //Abrindo conexão com o banco
            conectar();
            pstmt = conn.prepareStatement("INSERT INTO activities (codalternative, alternative, correct, codquestion) values(?, ?, ?, ?)");

            //Setando o valor dos parâmetros
            pstmt.setInt(1, alternative.getCodAlternative());
            pstmt.setString(2, alternative.getAlternative());
            pstmt.setBoolean(3, alternative.getCorrect());
            pstmt.setInt(4, alternative.getCodQuestion());

            pstmt.execute();
            //Executando o comando SQL do PreparedStatement
            desconectar(); //Desconectando do Banco de Dados
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateAlternative( String alternative, int codAlternative){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update alternatives set alternative = ? where codalternative = ?");

            //Setando os parâmetros
            pstmt.setString(1, alternative);
            pstmt.setInt(2, codAlternative);

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
    public boolean updateCorrect( boolean correct, int codAlternative){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update alternatives set correct = ? where codalternative = ?");

            //Setando os parâmetros
            pstmt.setBoolean(1, correct);
            pstmt.setInt(2, codAlternative);

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

    public boolean deleteAlternative(int codAlternative){
        try {
            //Abrindo a conexao com o banco
            conectar();

            String removerGuardiao = "delete from alternative where codAlternative = ?";

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(removerGuardiao);

            //Setando o valor ao parâmetro
            pstmt.setInt(1, codAlternative);

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

    public ResultSet searchAlternativesAsc(){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives order by codalternatives asc");

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchAlternativesDesc(){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives order by codalternatives desc");

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodAlterativeAsc(int codAlternative){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codalternative = ? order by codalternative asc");

            pstmt.setInt(1, codAlternative);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodAlternativeDesc(int codAlternative){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codalternative = ? order by codalternative desc");

            pstmt.setInt(1, codAlternative);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodQuestionAlternativeDesc(int codQuestion){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codquestion = ? order by codquestion desc");

            pstmt.setInt(1, codQuestion);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodQuestionAlternativeAsc(int codQuestion){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codquestion = ? order by codquestion asc");

            pstmt.setInt(1, codQuestion);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCorrectAlternative(String codQuestion){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codquestion = ? and correct = 'true' order by codquestion asc");

            pstmt.setString(1, codQuestion);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchIncorrectAlternative(String codQuestion){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from alternatives where codquestion = ? and correct = 'false' order by codquestion asc");

            pstmt.setString(1, codQuestion);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

}
