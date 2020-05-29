package entidades.Roteiro;

public class Area {
    private Ponto pSupDir, pInfEsq;

    public Area(Ponto pSupDir, Ponto pInfEsq) throws IllegalArgumentException {
        if ((pInfEsq.getX() >= pSupDir.getX()) || (pSupDir.getY() <= pInfEsq.getY())) {
            throw new IllegalArgumentException("O retangulo deve ser definido pela diagonal principal");
        } else {
            this.pSupDir = pSupDir;
            this.pInfEsq = pInfEsq;
        }
    }

    public Ponto getPSupEsq() {
        return pSupDir;
    }

    public Ponto getpInfEsq() {
        return pInfEsq;
    }
}
