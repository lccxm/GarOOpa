package entidades;

import java.util.List;

import casosDeUso.RepositorioDeMotoristas;
import casosDeUso.RepositorioDeViagens;

public class ServicosDoMotorista {
    private Motorista motorista;
    private String nome;
    private List<Viagem> viagens;
    private Viagem viagemSelecionada;

    public ServicosDoMotorista(String cpf) {
        this.motorista = RepositorioDeMotoristas.getMotoristaByCPF(cpf);
        this.nome = motorista.getNome();
        this.viagens = RepositorioDeViagens.getViagensByMotorista(motorista);
    }

    public void selecionaViagem(Viagem v){
        viagemSelecionada = v;
    }


}