//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner entrada = new Scanner(System.in);
//
//        boolean iniciarConexao;
//
//        do{
//
//            System.out.println("CONEXÃO:");
//            System.out.println("""
//                    \n1 - PROSSEGUIR COM A CONEXÃO
//
//                    0 - CANCELAR""");
//            System.out.print("\n: ");
//            int iniciarResp = entrada.nextInt();
//
//            if(iniciarResp == 1){
//                iniciarConexao = true;
//            }else{
//                iniciarConexao = false;
//            }
//
//            if(iniciarConexao){
//                Conexao conexao1 = new Conexao();
//
//                if(conexao1.conectar()){
//                    System.out.println("Conexão realizada com sucesso.");
//                    System.out.println("MENU - CONTROLE DO BANCO");
//                    System.out.println("""
//                        0. DESCONECTAR
//                        1. INSERIR REGISTRO DO GUARDIÃO
//                        2. INSERIR REGISTRO DO ESTUDANTE
//                        3.
//                        """);
//                    System.out.println(": ");
//                    int escolhaMenu = entrada.nextInt();
//                    entrada.skip("((?<!\\R)\\s)*"); //Pular os espaços em branco, até uma nova linha, pois nextInt() não termina de ler uma linha
//
//                    if(escolhaMenu == 0){
//                        iniciarConexao = false;
//                    }else{
//                        if(escolhaMenu == 1){
//                            if(conexao1.insertGuardian("Enzo", "enzo.rocha", "12345678910", "11967277226", "Pai", "vetoretin")){
//                                System.out.println("Registro do pai foi inserido com sucesso!");
//                            }else{
//                                System.out.println("Erro! Registro do pai não foi inserido");
//                            }
//                        }else{
//                            if(escolhaMenu == 2){
//
//                                if(conexao1.insertStudent("Thales", "3 ano", "Alto", "https://i.redd.it/xevid3fyws151.png")){
//                                    System.out.println("Registro do estudante foi inserido com sucesso!");
//                                }else{
//                                    System.out.println("Erro! Registro do estudante não foi inserido");
//                                }
//
//                            }else{
//                                if(escolhaMenu == 3){
//                                    System.out.println("Número do departamento que deseja atualizar sua localização: ");
//                                    int numDepto = entrada.nextInt();
//                                    entrada.skip("((?<!\\R)\\s)*");
//                                    System.out.println("Nome do novo local: ");
//                                    String nomeDepto = entrada.nextLine();
//
//                                    if(conexao1.alterarLocal(numDepto, nomeDepto)){
//                                        System.out.println("Localização do departamento alterado.");
//                                    }else{
//                                        System.out.println("Não foi possível alterar a localização do departamento.");
//                                    }
//                                }else{
//                                    if(escolhaMenu == 4){
//                                        System.out.println("Número do departamento que deseja remover: ");
//                                        int numDepto = entrada.nextInt();
//
//                                        if(conexao1.remover(numDepto)){
//                                            System.out.println("Departamento de código " + numDepto + " foi removido.");
//                                        }else{
//                                            System.out.println("Erro. Não foi possível remover departamento.");
//                                        }
//                                    }else{
//                                        if(escolhaMenu == 5){
//                                            System.out.println("Código do departamento para realizar a busca: ");
//                                            int numDepto = entrada.nextInt();
//                                            ResultSet rsNumero = conexao1.buscarPorNumero(numDepto);
//
//                                            try {
//                                                if(!rsNumero.isBeforeFirst()){
//                                                    System.out.println("Número departamento não encontrado.");
//                                                }else{
//                                                    while(rsNumero.next()){
//                                                        System.out.println("Número "+ rsNumero.getInt("deptno") + " corresponde ao departamento " + rsNumero.getString("dname"));
//                                                    }
//                                                }
//                                            }catch(SQLException sqle){
//                                                sqle.printStackTrace();
//                                            }
//
//                                        }else{
//                                            if(escolhaMenu == 6){
//                                                System.out.println("Nome do departamento para realizar a busca: ");
//                                                String nomeDepto = entrada.nextLine();
//                                                ResultSet rsNome = conexao1.buscarPorNome(nomeDepto);
//
//                                                try {
//                                                    if (!rsNome.isBeforeFirst()) {
//                                                        System.out.println("Nome de departamento não encontrado.");
//                                                    }else{
//                                                        while(rsNome.next()){
//                                                            System.out.println("Número: "+ rsNome.getInt("deptno") + " corresponde ao departamento " + rsNome.getString("dname"));
//                                                        }
//                                                    }
//                                                }catch(SQLException sqle){
//                                                    sqle.printStackTrace();
//                                                }
//                                            }else{
//                                                if(escolhaMenu == 7){
//                                                    System.out.println("Localização do departamento para realizar a busca: ");
//                                                    String locDepto = entrada.nextLine();
//                                                    ResultSet rsLoc = conexao1.buscarPorLocal(locDepto);
//
//                                                    try{
//                                                        if(!rsLoc.isBeforeFirst()){
//                                                            System.out.println("Localização de departamento não encontrado.");
//                                                        }else{
//                                                            while(rsLoc.next()){
//                                                                System.out.println("Número " + rsLoc.getInt("deptno") + " corresponde ao departamento " + rsLoc.getString("dname"));
//                                                            }
//                                                        }
//                                                    }catch(SQLException sqle){
//                                                        sqle.printStackTrace();
//                                                    }
//                                                }else{
//                                                    if(escolhaMenu == 8){
//                                                        ResultSet rs = conexao1.searchStudent(false);
//                                                        try {
//                                                            while (rs.next()) {
//                                                                String lista = ("ID: " + rs.getInt("codstudent") + " -" + " Nome: " + rs.getString("username") + " -" + " Level educacional: " + rs.getString("educationallevel") + " -" + " Level cognitivo: " + rs.getString("cognitivelevel") + " -" + " ID Guardião: " + rs.getInt("codguardian"));
//                                                                System.out.println(lista);
//                                                            }
//                                                        }catch(SQLException sqle){
//                                                            sqle.printStackTrace();
//                                                        }
//                                                    }else{
//                                                        conexao1.desconectar();
//                                                        iniciarConexao = false;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                }else{
//                    System.out.println("Erro! Conexão não sucedida.");
//                    iniciarConexao = false;
//                }
//            }
//
//        }while(iniciarConexao);
//        }
//}