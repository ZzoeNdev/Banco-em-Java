import java.util.ArrayList;

//POO da Conta (Classe)
public class Conta{
    private String nome;
    private boolean contaAtivada;
    //Cria a variavel do saldo privado
    private int saldo;
    ArrayList<Transferencia> historico = new ArrayList<> ();

    public Conta(String nomeDaConta){
        this.nome = nomeDaConta;
        this.contaAtivada = false;
        this.saldo = 0;
    }

    public String getNome(){
        return this.nome;
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

    public void deposito(int valorDeposito){
        if(contaAtivada){
            if(valorDeposito >= 0){
            this.saldo += valorDeposito;
            }else{
                
            }
        }else{
            System.out.println("A sua conta não esta ativada");
        }
    }

    public void Transferir(int valorDeTransferencia, Conta destinoTransferencia){
        if (valorDeTransferencia <= this.saldo && contaAtivada && valorDeTransferencia > 0){
            this.saldo -= valorDeTransferencia;
            destinoTransferencia.setSaldo(destinoTransferencia.getSaldo()+valorDeTransferencia);
            System.out.println("Transferencia executada com sucesso! \n");
            Transferencia t = new Transferencia(valorDeTransferencia, "PIX enviado", this, destinoTransferencia);
            this.historico.add(t);
            Transferencia td = new Transferencia(valorDeTransferencia, "PIX recebido", this, destinoTransferencia);
            destinoTransferencia.historico.add(td);
            System.out.println(this.historico);
            System.out.println(destinoTransferencia.historico);
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
}