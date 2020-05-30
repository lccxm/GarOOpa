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

    public Ponto getPSupDir() {
        return pSupDir;
    }

    public Ponto getPInfEsq() {
        return pInfEsq;
    }


    @Override
    public String toString() {
        return "{" +
            " pSupDir='" + pSupDir + "'" +
            ", pInfEsq='" + pInfEsq + "'" +
            "}";
    }

}
