package casosDeUso.servicos;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import casosDeUso.politicas.DeterminacaoVeiculo;
import casosDeUso.politicas.PrecoViagem;
import casosDeUso.repositorios.RepositorioDeBairros;
import casosDeUso.repositorios.RepositorioDeCidades;
import casosDeUso.repositorios.RepositorioDePassageiros;
import casosDeUso.repositorios.RepositorioDeViagens;
import casosDeUso.repositorios.Repositorios;
import entidades.Bairro;
import entidades.FormaPgto;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.TipoVeiculo;
import entidades.Viagem;
import entidades.Cidade;
import entidades.Roteiro.Roteiro;

public class ServicosDoPassageiro {
    private FormaPgto formaPgto;
    private TipoVeiculo categoria;
    private Passageiro passageiro;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Motorista motorista;
    private Viagem viagem;
    private int avalMotorista;

    public ServicosDoPassageiro(String cpfPassageiro, String bairroOrigem, String bairroDestino, String formaPgto,
            String categoria) throws IllegalArgumentException, FileNotFoundException {
        this.passageiro = RepositorioDePassageiros.getPassageiroByCPF(cpfPassageiro);
        this.bairroOrigem = RepositorioDeBairros.getBairroByName(bairroOrigem);
        this.bairroDestino = RepositorioDeBairros.getBairroByName(bairroDestino);
        this.formaPgto = FormaPgto.valueOf(formaPgto);
        this.categoria = TipoVeiculo.valueOf(categoria);
        this.motorista = buscaMotorista();
        this.viagem = getViagem();
        this.avalMotorista = 8;
    }

    private Motorista buscaMotorista() throws FileNotFoundException {
        List<Motorista> motoristasSelecionados = DeterminacaoVeiculo.determinaVeiculo(passageiro, formaPgto, categoria);
        int aleatorio = (int)(Math.random() * motoristasSelecionados.size());
        return motoristasSelecionados.get(aleatorio);
    }

    public Viagem getViagem(){
        int identificador = RepositorioDeViagens.getViagens().get(RepositorioDeViagens.size()-1).getIdentificador()+1;
        LocalDateTime dataHora = LocalDateTime.now();
        Cidade cidade = RepositorioDeCidades.getCidadeByName(bairroOrigem.getCidade());
        Roteiro roteiro = new Roteiro(cidade, bairroOrigem, bairroDestino);
        double custo = PrecoViagem.calculaPreco(roteiro, motorista);
        return new Viagem(identificador, dataHora, roteiro, motorista, passageiro, custo);
    }

    public void avaliarMotorista(String aval) throws IllegalArgumentException {
        int avaliacao = 8;
        try {
            avaliacao = Integer.parseInt(aval);
        } catch (Exception e) {
            throw new IllegalArgumentException("A avaliacao deve ser número inteiro");
        } 
        if (avaliacao>=0 && avaliacao<=10)
            this.avalMotorista = avaliacao;
        else
            throw new IllegalArgumentException("A avaliacao deve ser um inteiro entre 0 e 10.");
        motorista.atualizaNota(avaliacao);
    }


    @Override
    public String toString() {
        return "{" +
            " formaPgto='" + formaPgto + "'" +
            ", categoria='" + categoria + "'" +
            ", passageiro='" + passageiro + "'" +
            ", bairroOrigem='" + bairroOrigem + "'" +
            ", bairroDestino='" + bairroDestino + "'" +
            ", motorista='" + motorista + "'" +
            ", viagem='" + viagem + "'" +
            ", avalMotorista='" + avalMotorista + "'" +
            "}";
    }


}