package entidades;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import casosDeUso.RepositorioDeBairros;
import casosDeUso.RepositorioDePassageiros;
import entidades.Roteiro.IllegalArgumentException;

public class ServicosDoPassageiro {
    private FormaPgto formaPgto;
    private TipoVeiculo categoria;
    private Passageiro passageiro;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;

    public ServicosDoPassageiro(String cpfPassageiro, String bairroOrigem, String bairroDestino, String formaPgto,
            String categoria) throws IllegalArgumentException, FileNotFoundException {
        this.passageiro = RepositorioDePassageiros.getPassageiroByCPF(cpfPassageiro);
        this.bairroOrigem = RepositorioDeBairros.getBairroByName(bairroOrigem);
        this.bairroDestino = RepositorioDeBairros.getBairroByName(bairroDestino);
        this.formaPgto = FormaPgto.valueOf(formaPgto);
        this.categoria = TipoVeiculo.valueOf(categoria);
        buscaMotorista();
    }

    private Motorista buscaMotorista() throws FileNotFoundException {
        List<Motorista> motoristasSelecionados = DeterminacaoVeiculo.determinaVeiculo(passageiro, formaPgto, categoria);
        Random r = new Random();
        int aleatorio = r.nextInt(motoristasSelecionados.size());
        return motoristasSelecionados.get(aleatorio);
    }

    public Viagem criaViagem(){

    }


}