package entidades.Roteiro;

public class Ponto {
    private double x;
    private double y;

    public Ponto(int x,int y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public String toString() {
        return "Ponto [x=" + x + ", y=" + y + "]";
    }
}
