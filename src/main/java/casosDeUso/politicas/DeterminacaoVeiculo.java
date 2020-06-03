package casosDeUso.politicas;

import casosDeUso.repositorios.RepositorioDeMotoristas;
import casosDeUso.repositorios.RepositorioDePassageiros;
import entidades.FormaPgto;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.TipoVeiculo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DeterminacaoVeiculo {
    public static void main(String[] args) throws FileNotFoundException {
        RepositorioDeMotoristas r = new RepositorioDeMotoristas();
        RepositorioDePassageiros r1 = new RepositorioDePassageiros();
        determinaVeiculo(RepositorioDePassageiros.getPassageiroByCPF("00011122233"),FormaPgto.Dinheiro,TipoVeiculo.LUXO);
    }

    public static List<Motorista> determinaVeiculo(Passageiro p, FormaPgto f, TipoVeiculo t) throws FileNotFoundException {
        RepositorioDeMotoristas motoras = new RepositorioDeMotoristas();
        List<Motorista> motoristas = motoras.getMotoristas();
        List<Motorista> motoristasSelecionados = new ArrayList<Motorista>();
        for (Motorista m: motoristas) {
            if(m.getFormasPgto().contains(f) && m.getVeiculo().getTipo().equals(t) && ((p.getNota() - m.getNota() <= 4) || m.getNota() - p.getNota() <= 4)){
                motoristasSelecionados.add(m);
            }
        }
/*         System.out.println(motoristasSelecionados);
        motoristasSelecionados.removeIf(m -> m.getVeiculo().getTipo() != t);
        System.out.println(motoristasSelecionados);
        motoristasSelecionados.removeIf(m -> ((p.getNota() - m.getNota() >= 4) || m.getNota() - p.getNota() >= 4));
        System.out.println(motoristasSelecionados); */
        if(motoristasSelecionados.isEmpty()){
            throw new IllegalArgumentException("Sem mototristas disponiveis");
        } 
        System.out.println(motoristasSelecionados);
        return motoristasSelecionados;
    }


}
