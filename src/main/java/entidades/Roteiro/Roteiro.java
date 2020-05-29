package entidades.Roteiro;

import entidades.Bairro;
import entidades.Cidade;


public class Roteiro {
    private Cidade cidade;
    private Bairro bairroOrigem;
    private Bairro bairroDestino;

    public Roteiro(Cidade cidade, Bairro bairroOrigem, Bairro bairroDestino) {
        this.cidade = cidade;
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public Bairro getBairroOrigem() {
        return this.bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return this.bairroDestino;
    }



}