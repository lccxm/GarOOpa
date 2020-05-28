public class Motorista {

    private String cpf;
    private String nome;
    private int somaAvaliacoes;
    private int quantAvaliacoes;
    private Veiculo veiculo;
    private enum formaDePagamento{Dinheiro, Debito, Credito};

    public Motorista(String cpf, String nome, Veiculo veiculo){
        this.cpf = cpf;
        this.nome = nome;
        this.veiculo = veiculo;
    }


}
