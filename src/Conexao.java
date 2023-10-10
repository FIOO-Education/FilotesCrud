import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conexao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //Método para se conectar ao Banco de Dados da Fioo Education.


    //Método para se desconectar do Banco de Dados da Fioo Education.


    //Métodos para inserir registro do guardião da criança no Banco de Dados da Fioo Education.


    //Métodos para inserir registro do estudante no Banco de Dados da Fioo Education.


    //Método para alterar email do pai do estudante no Banco de Dados da Fioo Education.

    //Método para alterar o telefone do pai da criança no Banco de Dados da Fioo Education.

    //Método para alterar a descrição do perfil da criança no Banco de Dados da Fioo Education.

    //Método para alterar a Senha do perfil da criança no Banco de Dados da Fioo Education.

    //Método para alterar a Imagem do perfil da criança no Banco de Dados da Fioo Education.


    //Método para remover um elemento do Banco de Dados da Fioo Education.



    //Método para buscar pelo estudante
    public ResultSet searchStudent(boolean filtro){

        try {
            //Abrindo conexao com o banco
            conectar();

            if(filtro){
                try {
                    //Instanciando objeto que recebe entrada
                    Scanner entrada = new Scanner(System.in);

                    //Váriavel de controle do laço
                    boolean encerrar = true;
                    do {
                        String comando = "select * from student";
                        System.out.println("""
                                Deseja filtrar por:
                                0- Confirmar
                                1- Level Cognitivo
                                2- Level Educacional

                                R:""");
                        int escolhaCateg = entrada.nextInt();

                        if (escolhaCateg == 0) {
                            encerrar = true;
                        } else {
                            if (escolhaCateg == 1) {
                                comando += "  order by cognitivelevel";
                            } else {
                                if (escolhaCateg == 2) {
                                    comando += "  order by educationallevel";
                                }
                            }
                        }

                        System.out.println("""
                                Deseja visualizar os valores:
                                1 - Crescente
                                2 - Descresente""");
                        int escolhaOrd = entrada.nextInt();

                        if(escolhaOrd == 1){
                            comando += " asc";
                        }else{
                            if(escolhaOrd == 2){
                                comando += " desc";
                            }
                        }

                        //Instanciando o objeto preparedStatement (pstmt)
                        pstmt = conn.prepareStatement(comando);

                        //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
                        rs = pstmt.executeQuery();

                        //Retornando o ResultSet
                        return rs;


                    }while(false);
                }catch(InputMismatchException ime){
                    ime.printStackTrace();
                    return null;
                }finally{ //Indendente do que acontecer, a estrutura finally sempre é executada
                    desconectar();
                }
            }

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from student order by");

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();

            //Retornando o ResultSet
            return rs;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally{ //Indendente do que acontecer, a estrutura finally sempre é executada
            desconectar();
        }
    }

    public ResultSet buscarPorNumero(int numDept){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from dept where deptno = ?");

            pstmt.setInt(1, numDept);

            //Executando o comando sql do objeto preparedStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();
            //executeUpdate

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

    public ResultSet buscarPorNome(String nome){
        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from dept where dept.dname = ? ");

            pstmt.setString(1, nome);

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

    public ResultSet buscarPorLocal(String loc){

        try{
            //Abrindo conexao com o banco
            conectar();

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement("select * from dept where dept.loc = ?");

            pstmt.setString(1, loc);

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

} //Fim Classe Conexao

