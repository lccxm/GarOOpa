package entidades;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import casosDeUso.RepositorioDePassageiros;

public class ServicoBuscarMotorista {
    private String cpfPassageiro;
    private String bairroOrigem;
    private String bairroDestino;
    private String formaPgto;
    private String categoria;

    public ServicoBuscarMotorista(String cpfPassageiro, String bairroOrigem, String bairroDestino, String formaPgto, String categoria) {
        this.cpfPassageiro = cpfPassageiro;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        this.formaPgto = formaPgto;
        this.categoria = categoria;
    }

    public Motorista buscaMotorista() throws FileNotFoundException {
        Passageiro p = RepositorioDePassageiros.getPassageiroByCPF(cpfPassageiro);
        FormaPgto fPgto = FormaPgto.valueOf(formaPgto);
        TipoVeiculo tVeiculo = TipoVeiculo.valueOf(categoria);
        List<Motorista> motoristasSelecionados = DeterminacaoVeiculo.determinaVeiculo(p, fPgto, tVeiculo);
        Random r = new Random();
        int aleatorio = r.nextInt(motoristasSelecionados.size());
        return motoristasSelecionados.get(aleatorio);
    }


}