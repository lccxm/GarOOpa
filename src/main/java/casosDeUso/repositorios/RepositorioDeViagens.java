package casosDeUso.repositorios;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entidades.Bairro;
import entidades.Cidade;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.Viagem;
import entidades.Roteiro.Roteiro;

public class RepositorioDeViagens {
    private static List<Viagem> viagens;

    
    public RepositorioDeViagens() throws FileNotFoundException, IllegalArgumentException {
        viagens = new ArrayList<>();
        carregaViagens();
    }

    public static List<Viagem> getViagens() {
        return viagens;
    }

    // To DO: tirar list do contrutor de cidades (inicia vazia)
    // e inserir metodo addBairro 
    private void carregaViagens() throws FileNotFoundException, IllegalArgumentException {
        List<String[]> lst = GetRawData.fromCSV("viagens.dat");

        for (String[] data: lst){
            int identificador = Integer.parseInt(data[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(data[1], formatter);
            Bairro bairroOrigem = RepositorioDeBairros.getBairroByName(data[2]);
            Bairro bairroDestino = RepositorioDeBairros.getBairroByName(data[3]);
            Cidade cidade = RepositorioDeCidades.getCidadeByName(data[4]);
            Motorista mot = RepositorioDeMotoristas.getMotoristaByCPF(data[5]);
            Passageiro pas = RepositorioDePassageiros.getPassageiroByCPF(data[6]);
            double custo = Double.parseDouble(data[7]);
            Roteiro roteiro = new Roteiro(cidade, bairroOrigem, bairroDestino);
            viagens.add(new Viagem(identificador, dateTime, roteiro, mot, pas, custo));
        }
    }

    public static Viagem getViagemById(int id){
        for (Viagem viagem : viagens)
            if (viagem.getIdentificador() == id)
                return viagem;
        throw new IllegalArgumentException("id inexistente");
    }

    public static void add(Viagem viagem){
        viagens.add(viagem);
    }
    
    public static int size(){
        return viagens.size();
    }

    public static List<Viagem> getViagensByMotorista(Motorista motorista){
        List<Viagem> viagensSelecionadas = new LinkedList<>();
        for (Viagem v : viagens)
            if (v.getMotorista().getCpf().equals(motorista.getCpf()))
                viagensSelecionadas.add(v);
        return viagensSelecionadas;

    }

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
        new RepositorioDeViagens();
    }
}