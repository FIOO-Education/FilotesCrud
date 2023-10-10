import java.sql.*;

public class ConnectionActivity {
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

    public boolean insertActivity(Activity activity){
        try {
            //Abrindo conexão com o banco
            conectar();
            pstmt = conn.prepareStatement("INSERT INTO activities (codactivity, title, image, subject, codclass) values(?, ?, ?, ?, ?)");

            //Setando o valor dos parâmetros
            pstmt.setInt(1, activity.getCodActivity());
            pstmt.setString(2, activity.getTitle());
            pstmt.setString(3, activity.getImage());
            pstmt.setString(4, activity.getSubject());
            pstmt.setInt(5, activity.getCodClass());

            pstmt.execute();
            //Executando o comando SQL do PreparedStatement
            desconectar(); //Desconectando do Banco de Dados
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTitle( String title, int codactivity){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update activities set title = ? where codactivity = ?");

            //Setando os parâmetros
            pstmt.setString(1, title);
            pstmt.setInt(2, codactivity);

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
    public boolean updateImage(Activity activity){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update activities set image = ? where codactivity = ?");

            //Setando os parâmetros
            pstmt.setString(1, activity.getImage());
            pstmt.setInt(2, activity.getCodActivity());

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
    public boolean updateSubject(Activity activity){
        //Abrindo a conexão com o banco
        try {
            conectar();

            pstmt = conn.prepareStatement("update activities set title = ? where codactivity = ?");

            //Setando os parâmetros
            pstmt.setString(1, activity.getSubject());
            pstmt.setInt(2, activity.getCodActivity());

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

    public boolean deleteActivity(int codactivity){
        try {
            //Abrindo a conexao com o banco
            conectar();

            String removerAlternative= "delete from activities where codactivity = ?";

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(removerAlternative);

            //Setando o valor ao parâmetro
            pstmt.setInt(1, codactivity);

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

    public ResultSet searchActivitiesDesc() {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM activities ORDER BY codactivity desc");

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchActivitiesAsc() {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM activities ORDER BY codactivity asc");

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            desconectar();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchTitleDesc(String title) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM activities where title = ? ORDER BY title desc");

            pstmt.setString(1, title);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchTitleAsc(String title) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM activities where title = ? ORDER BY title asc");

            pstmt.setString(1, title);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodActivityDesc(int codActivity) {
        try {
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM activities where codactivity = ? ORDER BY codactivity desc");

            pstmt.setInt(1, codActivity);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet searchCodActivityAsc(int codActivity) {
        try {
            //Abrindo conexao com o banco
            conectar();
            pstmt = conn.prepareStatement("SELECT * FROM activities where codactivity = ? ORDER BY codactivity desc");

            pstmt.setInt(1, codActivity);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

}


