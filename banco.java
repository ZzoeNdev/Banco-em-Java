import java.util.Scanner;
import java.util.ArrayList;

public class banco {

    public static void Calculo(int v){

        //Crio uma lista de array vazio (é necessario importar a utilidade Array list)
        ArrayList<Integer> notas = new ArrayList<>();
        //Adiciona os valores no array
        notas.add(100);
        notas.add(50);
        notas.add(20);
        notas.add(10);
        notas.add(5);
        notas.add(2);
        notas.add(1);

        //Se valor for negativo = invalido
        if (v <= 0){
            System.out.println("Valor inválido");
        //Já deu pra entender
        }else if (v >= 1000){
            System.out.println("Limite ultrapassado");
        }else{       
            //Fala que o valor que sobra é o valor total (ponto 0) 
            int sobrado = v;

            //Passa por cada array do q a gente criou, por exemplo, 100
            for (int i = 0; i < notas.size(); i++){
                int nota = sobrado/notas.get(i); //Vai dividir por 100 e vai dar o numero de notas que vai dar
                sobrado = sobrado%notas.get(i); //vai substituir o valor do valor sobrado pelo restante da divisão por 100
                
                System.out.println("Notas de " + notas.get(i) + ": " + nota); //Printa os resultados
            }

        }
    }

    public static void main(String[] args) {

        //Crio a utilidade Scanner, que le as respostas do console (é necessario importar a utilidade Scanner)
        Scanner read = new Scanner(System.in);
        boolean sair = false;
        boolean usuarioLogado = false;
        Conta minhaConta = null;
        Conta respostaConta = null;

        ArrayList<Conta> contas = new ArrayList<> ();

        System.out.println("Deseja criar uma conta? S/N");
        String resposta = read.nextLine();
        
        if (resposta.equalsIgnoreCase("S")){

            System.out.println("Crie contas, ou escreva 'pular' para passar o processo");
            while(true){
                System.out.println("Qual o nome da nova conta?");
                resposta = read.nextLine();
                if (resposta.equalsIgnoreCase("pular")){
                    break;
                }

                respostaConta = new Conta(resposta);
                contas.add(respostaConta);
                System.out.println("Id da conta " + (respostaConta.getId()));
                respostaConta.salvarDados();
            }

            System.out.println("Digite seu ID para acessar sua conta:");
            int respostaId = read.nextInt();
            read.nextLine();

            while(!usuarioLogado){
                for (int i = 0; i < contas.size(); i++){
                    if(contas.get(i).getId() == respostaId){
                        minhaConta = contas.get(i);
                        usuarioLogado = true;
                        System.out.println("Login bem-sucedido!");
                        break;
                    }
                }

                if(!usuarioLogado){
                    System.out.println("ID não encontrado, por favor verifique e tente novamente.");
                    respostaId = read.nextInt();
                    read.nextLine();
                }
            }
            
            


        }else if (resposta.equalsIgnoreCase("N")){
            System.out.println("Encerrando...");
            read.close();
        }

        if (usuarioLogado){

        System.out.println("---Menu Inicial (Seja Bem-vindo(a) " + minhaConta.getNome() + ")---");
        //Ativação da conta
        minhaConta.ativarConta();
        //Acesso saldo da minha conta e decido o valor dele
        minhaConta.setSaldo(1000);
        System.out.println("1. Acessar Conta \n2. Sair");
        resposta = read.nextLine();

        while(!sair){
        
        if (resposta.equalsIgnoreCase("1")){
            System.out.println("---Menu Conta--- \nSeja muito bem-vindo(a)");
            System.out.println("1. Sacar \n2.Depositar \n3.Transferir \n4.Ver Saldo \n5.Ver Extrato \n6.Ver Dados da Conta \nDigite Sair para sair");

            resposta = read.nextLine();

            if (resposta.equalsIgnoreCase("1")){
            
            System.out.println("Quanto deseja sacar?");

            //Tenta executar o codigo abaixo, se der erro vai para o catch (Para o não)
            try{
                int valorDesejado = read.nextInt();
                read.nextLine();
                Calculo(valorDesejado); 
                minhaConta.sacar(valorDesejado);
            }catch(Exception e){
                System.out.println("Escreva somente numeros");
            }

        }

        if (resposta.equalsIgnoreCase("2")){
            
            System.out.println("Quanto deseja depositar?");

            //Tenta executar o codigo abaixo, se der erro vai para o catch (Para o não)
            try{
                int valorDesejado = read.nextInt();
                read.nextLine(); 
                minhaConta.deposito(valorDesejado);
                resposta = "1";
            }catch(Exception e){
                System.out.println("Escreva somente numeros");
            }

        }
        
            if(resposta.equalsIgnoreCase("3")){
                System.out.println("Qual valor deseja transferir?");
                int valorDesejado = read.nextInt();
                read.nextLine();
                
                System.out.println("Digite o ID para quem deseja transferir: (Digite '0' para sair do modo Transferencia)");
                int destinoDesejado = read.nextInt();
                read.nextLine();

                boolean contaEncontrada = false;

                while(!contaEncontrada){

                    if(destinoDesejado == 0){
                        break;
                    }

                    for (int i = 0; i < contas.size(); i++){
                        if(contas.get(i).getId() == destinoDesejado){
                            if(minhaConta.transferir(valorDesejado, contas.get(i))){
                                contaEncontrada = true;
                            }else{
                               System.out.println("Erro ao transferir"); 
                            }
                            break;
                        }
                    }

                        if(!contaEncontrada){
                            System.out.println("Usuário não encontrado, tente novamente.");
                            destinoDesejado = read.nextInt();
                            read.nextLine();
                        }
                }
            resposta = "1";
        }

        if(resposta.equalsIgnoreCase("4")){
                System.out.println("Seu saldo é: " + minhaConta.getSaldo());
                resposta = "1";

        }

        if(resposta.equalsIgnoreCase("5")){
                minhaConta.mostrarHistorico();
                resposta = "1";

        }

        if(resposta.equalsIgnoreCase("6")){
                System.out.println("Nome: " + minhaConta.getNome() + "\nId: "+minhaConta.getId() + "\nSaldo: R$" +minhaConta.getSaldo());

        }

        if (resposta.equalsIgnoreCase("sair")){
                sair = true;
        }
        }

        }

    }else{
        System.out.println("Usuário não encontrado");
    }
    }
    }


