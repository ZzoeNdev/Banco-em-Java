//POO da Transferencia (Classe)
public class Transferencia{
    private int valor;
    private String tipo;
    private String remetente;
    private String destino;

    public Transferencia(int valorDeTransferencia, String tipoDeTransferencia, Conta remetenteTransferencia, Conta destinoTransferencia){
        this.valor = valorDeTransferencia;
        this.tipo = tipoDeTransferencia;
        this.remetente = remetenteTransferencia.nome;
        this.destino = destinoTransferencia.nome;
    }

    @Override
    public String toString(){
        if(this.tipo.equalsIgnoreCase("PIX enviado")){
            return this.tipo + " de: R$" + this.valor + " ao " + this.destino;
        }else{
            return this.tipo + " de: R$" + this.valor + " de " + this.remetente;
        }
        
    }
    
}