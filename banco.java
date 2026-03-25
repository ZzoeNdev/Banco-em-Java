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
        boolean contaCriada = false;
        Conta minhaConta = null;
        Conta itaroConta = new Conta("Itaro");

        System.out.println("Deseja criar uma conta? S/N");
        String resposta = read.nextLine();
        
        if (resposta.equalsIgnoreCase("S")){
            //Crio a minha conta no banco
            minhaConta = new Conta("Enzzo");
            contaCriada = true;
        }else if (resposta.equalsIgnoreCase("N")){
            System.out.println("Encerrando...");
            read.close();
        }

        if (contaCriada){

        System.out.println("---Menu Inicial---");
        //Ativação da conta
        minhaConta.ativarConta();
        //Acesso saldo da minha conta e decido o valor dele
        minhaConta.setSaldo(1000);
        System.out.println("1. Acessar Conta \n2. Sair");
        minhaConta.Transferir(100, itaroConta);
        minhaConta.mostrarHistorico();
        itaroConta.mostrarHistorico();
        resposta = read.nextLine();

        while(!sair){
        
        if (resposta.equalsIgnoreCase("1")){
            System.out.println("---Menu Conta--- \nSeja muito bem-vindo(a)");
            System.out.println("1. Sacar \n2.Depositar \n3.Ver Saldo \nDigite Sair para sair");

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
        
            if(resposta.equalsIgnoreCase("3")){
                System.out.println("Seu saldo atual é: " + minhaConta.getSaldo());
                resposta = "1";
        }

        }

        if (resposta.equalsIgnoreCase("sair") || resposta.equalsIgnoreCase("2")){
                sair = true;
        }

        }

    }else{
        System.out.println("A conta não foi criada");
    }
    }
    }

//POO da Conta (Classe)
class Conta{

    private ArrayList<String> historico = new ArrayList<> ();
    public String nome;
    private boolean contaAtivada;
    //Cria a variavel do saldo privado
    private int saldo;

    public Conta(String nomeDaConta){
        this.nome = nomeDaConta;
        this.contaAtivada = false;
        this.saldo = 0;
    }

    //Metodo (função) de definir o saldo pegando o valor da variavel que passei la em cima
    public void setSaldo(int valorSaldo){
        if (contaAtivada){
            if (valorSaldo >= 0){
                //Falo que o saldo é oq esta presente nessa classe
                this.saldo = valorSaldo;
            }else{
                System.out.println("O valor tem q ser positivo");
            }
        }else{
            System.out.println("Sua conta "+this.nome+" não esta ativa");
        }
    }

    //Aqui é so para pegar o saldo, ele nn usa void somente o tipo, pois ele retornara algo, no caso, o saldo
    public int getSaldo(){
        return this.saldo;
    }
    //Usamos o void quando apenas queremos executar calculos ou algo direto, como abaixo
    //Usamos esse metodo (função) para fazer o desconto do valor que foi desejado no saldo
    public void sacar(int valorSaque){
        if (contaAtivada){
            if (valorSaque <= this.saldo){
                this.saldo -= valorSaque;
                System.out.println("Saque efetuado");
            }else{
                System.out.println("Não há saldo");
            }
        }else{
            System.out.println("Sua conta não esta ativa");
        }
    }

    //Ativação da conta
    public void ativarConta(){
        contaAtivada = true;
        System.out.println("Sua conta foi ativada com sucesso! \n");
    }

    public void mostrarHistorico(){
        System.out.println(this.historico);
    }

    public void Transferir(int valorTranferencia, Conta contaDestinatario){
        if (valorTranferencia <= this.saldo && contaAtivada){
            this.saldo -= valorTranferencia;
            contaDestinatario.setSaldo(contaDestinatario.getSaldo()+valorTranferencia);
            System.out.println("Tranferencia executada com sucesso! \n");
            this.historico.add("Você enviou R$" + valorTranferencia + " para " + contaDestinatario.nome + "\n");
            contaDestinatario.historico.add("Você recebeu R$" + valorTranferencia + " de " + this.nome + "\n");
        }
    }

}



