package entidades.Roteiro;

import entidades.Roteiro.Area;
import entidades.Roteiro.Ponto;
import entidades.Roteiro.Reta;
import entidades.Roteiro.SituacaoReta;


public class Cohen {
    public static void main(String[] args) throws IllegalArgumentException {
        Ponto p1 = new Ponto(5,5);
        Ponto p2 = new Ponto(7,7);
        Reta rTest = new Reta(p1, p2);
        Ponto p3 = new Ponto(10, 8);
        Ponto p4 = new Ponto(4,4);
        Area aTest = new Area(p3, p4);
        Cohen c = new Cohen(aTest, rTest);
        System.out.println(c.cohenSutherlandClip());
    }

    public static int INSIDE = 0;
    public static int LEFT = 1;
    public static int RIGHT = 2;
    public static int BOTTOM = 4;
    public static int TOP = 8;

    public double xMax;
    public double yMax;
    public double xMin;
    public double yMin;

    private Area a;
    private Reta r;
    

    public Cohen(Area a, Reta r) {
        this.a = a;
        this.r = r;
        xMax = a.getPSupEsq().getX();
        yMax = a.getPSupEsq().getY();
        xMin = a.getpInfEsq().getX();
        yMin = a.getpInfEsq().getY();
    }


    public int computeCode(Ponto p){
        int code = INSIDE;
        if (p.getX() < xMin)
            code |= LEFT;
        else if (p.getX() > xMax)
            code |= RIGHT;
        if (p.getY() < yMin)
            code |= BOTTOM;
        else if (p.getY() > yMax)
            code |= TOP;

        return code;
    }

    public SituacaoReta cohenSutherlandClip(){
        Ponto p1 = r.getP1();
        Ponto p2 = r.getP2();
        int code1 = computeCode(p1);
        int code2 = computeCode(p2);
        boolean accept = false;

        while (true){
            if (code1 ==0 && code2 ==0){
                accept = true;
                break;
            }
            else if ((code1 & code2) != 0)
                break;
            else {
                double x = 1.0;
                double y = 1.0;
                int codeOut;
                if (code1 != 0)
                    codeOut = code1;
                else
                    codeOut = code2;
                if ((codeOut & TOP) != 0){
                    x = p1.getX() + (p2.getX() - p1.getX()) * (yMax - p1.getY()) / (p2.getY() - p1.getY());;
                    y = yMax;
                } else if ((codeOut & BOTTOM) != 0){
                    x = p1.getX() + (p2.getX() - p1.getX()) * (yMin - p1.getY()) / (p2.getY() - p1.getY());;
                    y = yMin;
                } else if ((codeOut & RIGHT) != 0){
                    y = p1.getY() + (p2.getY() - p1.getY()) * (xMax - p1.getX()) / (p2.getX() - p1.getX());;
                    x = xMax;
                } else if ((codeOut & LEFT) != 0){
                    y = p1.getY() + (p2.getY() - p1.getY()) * (xMin - p1.getX()) / (p2.getX() - p1.getX());;
                    x = xMin;
                }
                if (codeOut == code1){
                    p1.setX(x);
                    p1.setY(y);
                    code1 = computeCode(p1);
                }
                else{
                    p2.setX(x);
                    p2.setY(y);
                    code2 = computeCode(p2);
                }
            }
        }
        if (accept)
            return SituacaoReta.INTERSECTA;
        else
            return SituacaoReta.TODA_FORA;
    }

        
}
