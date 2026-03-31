//POO da Transferencia (Classe)
public class Transferencia{
    private int valor;
    private String tipo;
    private String remetente;
    private String destino;

    public Transferencia(int valorDeTransferencia, String tipoDeTransferencia, Conta remetenteTransferencia, Conta destinoTransferencia){
        this.valor = valorDeTransferencia;
        this.tipo = tipoDeTransferencia;
        this.remetente = remetenteTransferencia.getNome();
        this.destino = destinoTransferencia.getNome();
    }

    @Override
    public String toString(){
        if(this.tipo.equalsIgnoreCase("PIX enviado")){
            return this.tipo + " de: R$" + this.valor + " ao " + this.destino;
        }else if (this.tipo.equalsIgnoreCase("PIX recebido")){
            return this.tipo + " de: R$" + this.valor + " de " + this.remetente;
        }else if (this.tipo.equalsIgnoreCase("Deposito")){
            return this.tipo + " de: R$" + this.valor;
        }else if (this.tipo.equalsIgnoreCase("Saque")){
            return this.tipo + " de: R$" + this.valor;
        }
        
    }
    
}