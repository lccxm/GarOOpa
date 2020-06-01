package casosDeUso.politicas;

import casosDeUso.repositorios.RepositorioDeMotoristas;
import entidades.FormaPgto;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.TipoVeiculo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DeterminacaoVeiculo {
    public static void main(String[] args) {

    }

    public static List<Motorista> determinaVeiculo(Passageiro p, FormaPgto f, TipoVeiculo t) throws FileNotFoundException {
        RepositorioDeMotoristas motoras = new RepositorioDeMotoristas();
        List<Motorista> motoristas = motoras.getMotoristas();
        List<Motorista> motoristasSelecionados = new ArrayList<Motorista>();
        for (Motorista m: motoristas) {
            if(m.getFormasPgto().contains(f)){
                motoristasSelecionados.add(m);
            }
        }
        motoristasSelecionados.removeIf(m -> m.getVeiculo().getTipo() != t);
        motoristasSelecionados.removeIf(m -> (m.getNota() - p.getNota()) > 4);
        if(motoristasSelecionados.isEmpty()){
            throw new IllegalArgumentException("Sem mototristas disponiveis");
        }
        return motoristasSelecionados;
    }
}
