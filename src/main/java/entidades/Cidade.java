<<<<<<< HEAD
package entidades;

=======
>>>>>>> f856073fea6f9567ed26f5e8b10c8886239fdeef
import java.util.List;

public class Cidade {
    private String nome;
    private List<Bairro> bairros;

    public Cidade(String nome, List<Bairro> bairros) {
        this.nome = nome;
        this.bairros = bairros;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Bairro> getBairros() {
        return this.bairros;
    }

}
