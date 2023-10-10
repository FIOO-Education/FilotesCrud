import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        boolean iniciarConexao;

        do {

            System.out.println("CONEXÃO:");
            System.out.println("""
                    \n1 - PROSSEGUIR COM A CONEXÃO

                    0 - CANCELAR""");
            System.out.print("\n: ");
            int iniciarResp = entrada.nextInt();

            if (iniciarResp == 1) {
                iniciarConexao = true;
            } else {
                iniciarConexao = false;
            }

            if (iniciarConexao) {
                Conexao conexao1 = new Conexao();

                if (conexao1.conectar()) {
                    System.out.println("Conexão realizada com sucesso.");
                    System.out.println("MENU - CONTROLE DO BANCO");
                    System.out.println("""
                            0. DESCONECTAR
                            1. INSERIR REGISTRO DO GUARDIÃO
                            2. INSERIR REGISTRO DO ESTUDANTE
                            3. BUSCAR ALUNO
                            4. APAGAR CONTA                                                                             
                            """);
                    System.out.println(": ");
                    int escolhaMenu = entrada.nextInt();
                    entrada.skip("((?<!\\R)\\s)*"); //Pular os espaços em branco, até uma nova linha, pois nextInt() não termina de ler uma linha

                    if (escolhaMenu == 0) {
                        iniciarConexao = false;
                    } else {
                        if (escolhaMenu == 1) {
                            if (conexao1.insertGuardian("Enzo", "enzo.rocha", "12345678910", "11967277226", "Pai", "vetoretin")) {
                                System.out.println("Registro do pai foi inserido com sucesso!");
                            } else {
                                System.out.println("Erro! Registro do pai não foi inserido");
                            }
                        } else {
                            if (escolhaMenu == 2) {

                                if (conexao1.insertStudent("Thales", "3 ano", "Alto", "https://i.redd.it/xevid3fyws151.png")) {
                                    System.out.println("Registro do estudante foi inserido com sucesso!");
                                } else {
                                    System.out.println("Erro! Registro do estudante não foi inserido");
                                }

                            } else {
                                if (escolhaMenu == 3) {
                                    ResultSet rs = conexao1.searchStudent(false);
                                    try {
                                        while (rs.next()) {
                                            String lista = ("ID: " + rs.getInt("codstudent") + " -" + " Nome: " + rs.getString("username") + " -" + " Level educacional: " + rs.getString("educationallevel") + " -" + " Level cognitivo: " + rs.getString("cognitivelevel") + " -" + " ID Guardião: " + rs.getInt("codguardian"));
                                            System.out.println(lista);
                                        }
                                    } catch (SQLException sqle) {
                                        sqle.printStackTrace();
                                    }
                                } else {
                                    if(escolhaMenu == 4){
                                        System.out.println("Digite o seu código de guardião para confirmar a exclusão da conta: ");
                                        int codGuardiao = entrada.nextInt();
                                        entrada.skip("((?<!\\R)\\s)*"); //Pular os espaços em branco, até uma nova linha, pois nextInt() não termina de ler uma linha

                                        System.out.println("Digite o código do aluno para confirmar a exclusão da conta: ");
                                        int codAluno = entrada.nextInt();
                                        entrada.skip("((?<!\\R)\\s)*"); //Pular os espaços em branco, até uma nova linha, pois nextInt() não termina de ler uma linha

                                        System.out.println("Tem certeza que deseja continuar [S/N]: ");
                                        String confirmacao = entrada.nextLine();

                                        if(confirmacao.toLowerCase().trim().equals("s") || confirmacao.toLowerCase().trim().equals("n")){
                                            if(confirmacao.toLowerCase().trim().equals("s")){
                                                if(conexao1.deleteStudent(codAluno)){
                                                    if(conexao1.deleteGuardian(codGuardiao)){
                                                        System.out.println("Conta removida.");
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Ufa! Procedimento cancelado.");
                                            }
                                        }else{
                                            System.out.println("Valor inválido.");
                                        }

                                    }else{
                                        conexao1.desconectar();
                                        iniciarConexao = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                System.out.println("Erro! Conexão não sucedida.");
                iniciarConexao = false;
            }
        } while (iniciarConexao);
    }
}
