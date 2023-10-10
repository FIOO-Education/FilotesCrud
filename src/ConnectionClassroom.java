import java.sql.*;

public class ConnectionClassroom {

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

    public boolean insertClassroom(Classroom classroom){
        try {
            //Abrindo conexão com o banco
            conectar();
            pstmt = conn.prepareStatement("INSERT INTO classes ( codclass, nameclass, title, description, videolink, subject) values(?, ?, ?, ?, ?, ?)");

            //Setando o valor dos parâmetros
            pstmt.setInt(1, classroom.getCodClass());
            pstmt.setString(2, classroom.getNameClass());
            pstmt.setString(3, classroom.getTitle());
            pstmt.setString(4, classroom.getDescription());
            pstmt.setString(5, classroom.getVideoLink());
            pstmt.setString(6, classroom.getSubject());

            pstmt.execute();
            //Executando o comando SQL do PreparedStatement
            desconectar(); //Desconectando do Banco de Dados
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTitle(String title, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update classes set title = ? where codclass = ?");

            //Setando os parâmetros
            pstmt.setString(1, title);
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
    public boolean updateName( String nameclass, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update classes set nameclass = ? where codclass = ?");

            //Setando os parâmetros
            pstmt.setString(1, nameclass);
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
    public boolean updateDescription( String description, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update classes set description = ? where codclass = ?");

            //Setando os parâmetros
            pstmt.setString(1, description);
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

    public boolean updateVideo( String videolink, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update classes set videolink = ? where codclass = ?");

            //Setando os parâmetros
            pstmt.setString(1, videolink);
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
    public boolean updateSubject(String subject, int codclass){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update classes set subject = ? where codclass = ?");

            //Setando os parâmetros
            pstmt.setString(1, subject);
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

    //Método para remover uma classe existente do Banco de Dados da Fioo Education.
    public boolean deleteClassroom(int codClass){
        try {
            //Abrindo a conexao com o banco
            conectar();

            String removerClasse = "delete from classes where codclass = ?";

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(removerClasse);

            //Setando o valor ao parâmetro
            pstmt.setInt(1, codClass);

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

    public ResultSet searchClassesDesc(){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from classes order by codclass desc");

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

    public ResultSet searchClassesAsc(){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from classes order by codclass asc");

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

    public ResultSet searchTitleDesc(String title) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where title = ? ORDER BY title desc");

            pstmt.setString(1, title);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchTitleAsc(String title) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where title = ? ORDER BY title asc");

            pstmt.setString(1, title);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }


    public ResultSet searchNameDesc(String name) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where name = ? ORDER BY name desc");

            pstmt.setString(1, name);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchNameAsc(String name) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where name = ? ORDER BY name asc");

            pstmt.setString(1, name);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodDesc(int codClass) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where codclass = ? ORDER BY codclass desc");

            pstmt.setInt(1, codClass);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodAsc(int codClass) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM classes where codclass = ? ORDER BY codclass asc");

            pstmt.setInt(1, codClass);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

}
