package entidades.Roteiro;

public class Cohen {

    public static int INSIDE = 0;  //0000
    public static int LEFT = 1;    // 0001
    public static int RIGHT = 2;   // 0010
    public static int BOTTOM = 4;  //0100
    public static int TOP = 8;     //1000

    public double xMax;
    public double yMax;
    public double xMin;
    public double yMin;

    private Area a;
    private Reta r;


    public Cohen(Area a, Reta r) {
        this.a = a;
        this.r = r;
        xMax = a.getPSupDir().getX();
        yMax = a.getPSupDir().getY();
        xMin = a.getPInfEsq().getX();
        yMin = a.getPInfEsq().getY();
    }

     public int computeCode(Ponto p) {
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

    public SituacaoReta cohenSutherlandClip() {
        Ponto p1 = r.getP1();
        Ponto p2 = r.getP2();
        int code1 = computeCode(p1);
        int code2 = computeCode(p2);
        boolean accept = false;

        while (true) {
            if (code1 == 0 && code2 == 0) {
                accept = true;
                break;
            } else if ((code1 & code2) != 0)
                break;
            else {
                double x = 1.0;
                double y = 1.0;
                int codeOut;
                if (code1 != 0)
                    codeOut = code1;
                else
                    codeOut = code2;
                if ((codeOut & TOP) != 0) {
                    x = p1.getX() + (p2.getX() - p1.getX()) * (yMax - p1.getY()) / (p2.getY() - p1.getY());
                    y = yMax;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = p1.getX() + (p2.getX() - p1.getX()) * (yMin - p1.getY()) / (p2.getY() - p1.getY());
                    y = yMin;
                } else if ((codeOut & RIGHT) != 0) {
                    y = p1.getY() + (p2.getY() - p1.getY()) * (xMax - p1.getX()) / (p2.getX() - p1.getX());
                    x = xMax;
                } else if ((codeOut & LEFT) != 0) {
                    y = p1.getY() + (p2.getY() - p1.getY()) * (xMin - p1.getX()) / (p2.getX() - p1.getX());
                    x = xMin;
                }
                if (codeOut == code1) {
                    p1.setX(x);
                    p1.setY(y);
                    code1 = computeCode(p1);
                } else {
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
