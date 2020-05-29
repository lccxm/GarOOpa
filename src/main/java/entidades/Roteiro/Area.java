package entidades.Roteiro;

public class Area {
    private Ponto pSupEsq, pInfDir;

    public Area(Ponto pSupEsq, Ponto pInfDir) {
        if ((pSupEsq.getX() >= pInfDir.getX()) || (pSupEsq.getY() <= pInfDir.getY())) {
            throw new IllegalArgumentException("O retangulo deve ser definido pela diagonal principal");
        } else {
            this.pSupEsq = pSupEsq;
            this.pInfDir = pInfDir;
        }
    }

    public Ponto getPSupEsq() {
        return pSupEsq;
    }
}
