package entidades;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import casosDeUso.RepositorioDeBairros;
import casosDeUso.RepositorioDeCidades;
import casosDeUso.RepositorioDePassageiros;
import casosDeUso.RepositorioDeViagens;
import entidades.Roteiro.IllegalArgumentException;
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
        Random r = new Random();
        int aleatorio = r.nextInt(motoristasSelecionados.size());
        return motoristasSelecionados.get(aleatorio);
    }

    private Viagem getViagem(){
        int identificador = RepositorioDeViagens.getViagens().get(RepositorioDeViagens.size()).getIdentificador()+1;
        LocalDateTime dataHora = LocalDateTime.now();
        Cidade cidade = RepositorioDeCidades.getCidadeByName(bairroOrigem.geCidade());
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
        if (avaliacao>0 && avaliacao<10)
            this.avalMotorista = avaliacao;
        else
            throw new IllegalArgumentException("A avaliacao deve ser um inteiro entre 0 e 10.");
    }


}