public class Passageiro {
    private String cpf;
    private String nome;
    private int somatorioAval;
    private int qtdAval;

    public Passageiro(String cpf, String nome, int somatorioAval, int qtdAval) {
        this.cpf = cpf;
        this.nome = nome;
        this.somatorioAval = somatorioAval;
        this.qtdAval = qtdAval;
    }
    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public int getSomatorioAval() {
        return this.somatorioAval;
    }


    public int getQtdAval() {
        return this.qtdAval;
    }


}