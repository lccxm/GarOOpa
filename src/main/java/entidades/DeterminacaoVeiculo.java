package entidades;

import casosDeUso.RepositorioDeMotoristas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DeterminacaoVeiculo {
    public static void main(String[] args) {

    }

    public List<Motorista> determinaVeiculo(Passageiro p, FormaPgto f, TipoVeiculo t) throws FileNotFoundException {
        RepositorioDeMotoristas motoras = new RepositorioDeMotoristas();
        List<Motorista> motoristas = motoras.getMotoristas();
        List<Motorista> motoristasSelecionados = new ArrayList<Motorista>();
        for (Motorista m: motoristas) {
            if(m.getFormasPgto().contains(f)){
                motoristasSelecionados.add(m);
            }
        }
        if(motoristasSelecionados.isEmpty()){
            System.out.println("errouuuu");
        }
        motoristasSelecionados.removeIf(m -> m.getVeiculo().getTipo() != t);
        if(motoristasSelecionados.isEmpty()){
            System.out.println("nao vai dar nao");
        }
        motoristasSelecionados.removeIf(m -> (m.getNota() - p.getNota()) > 4);
        if(motoristasSelecionados.isEmpty()){
            System.out.println("deu bret");
        }
        return motoristasSelecionados;
    }
}
