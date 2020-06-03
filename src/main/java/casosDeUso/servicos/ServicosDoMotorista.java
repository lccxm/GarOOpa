package casosDeUso.servicos;

import java.util.List;

import casosDeUso.repositorios.RepositorioDeMotoristas;
import casosDeUso.repositorios.RepositorioDeViagens;
import entidades.Motorista;
import entidades.Viagem;

public class ServicosDoMotorista {
    private Motorista motorista;
    private String nome;
    private List<Viagem> viagens;
    private Viagem viagemSelecionada;
    private int avalPassageiro;

    public ServicosDoMotorista(String cpf) {
        this.motorista = RepositorioDeMotoristas.getMotoristaByCPF(cpf);
        this.nome = motorista.getNome();
        this.viagens = RepositorioDeViagens.getViagensByMotorista(motorista);
        avalPassageiro = 8;
    }

    public void selecionaViagem(Viagem v){
        viagemSelecionada = v;
    }


    public Viagem getViagemSelecionada() {
        return this.viagemSelecionada;
    }

    public void avaliarPassageiro(String aval) throws IllegalArgumentException {
        int avaliacao = 8;
        try {
            avaliacao = Integer.parseInt(aval);
        } catch (Exception e) {
            throw new IllegalArgumentException("A avaliacao deve ser número inteiro");
        } 
        if (avaliacao>=0 && avaliacao<=10)
            this.avalPassageiro = avaliacao;
        else
            throw new IllegalArgumentException("A avaliacao deve ser um inteiro entre 0 e 10.");
    }


    @Override
    public String toString() {
        return "{" +
            " motorista='" + motorista + "'" +
            ", nome='" + nome + "'" +
            ", viagens='" + viagens + "'" +
            ", viagemSelecionada='" + viagemSelecionada + "'" +
            ", avalPassageiro='" + avalPassageiro + "'" +
            "}";
    }


}