package entidades;

public class Passageiro {
    private String cpf;
    private String nome;
    private int somatorioAval;
    private int qtdAval;
    private int nota;

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

    public void atualizaNota(int avaliacao){
        if(avaliacao >= 0 && avaliacao <= 10) {
            somatorioAval += avaliacao;
            qtdAval += 1;
            nota = (int) (somatorioAval / qtdAval);
        }
    }

    public int getNota(){
        return nota;
    }

    @Override
    public String toString() {
        return "{" +
            " cpf='" + cpf + "'" +
            ", nome='" + nome + "'" +
            ", somatorioAval='" + somatorioAval + "'" +
            ", qtdAval='" + qtdAval + "'" +
            "}";
    }
    
}