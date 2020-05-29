package entidades.Roteiro;

public class Cohen {
    public static void main(String[] args) {
        Ponto p1 = new Ponto(7,9);
        Ponto p2 = new Ponto(11,4);
        System.out.println(cohenSutherlandClip(p1, p2));
    }

    public static int INSIDE = 0;  //0000
    public static int LEFT = 1;    // 0001
    public static int RIGHT = 2;   // 0010
    public static int BOTTOM = 4;  //0100
    public static int TOP = 8;     //1000

    public static double xMax = 10.0;
    public static double yMax = 8.0;
    public static double xMin = 4.0;
    public static double yMin = 4.0;
    


    public static int computeCode(Ponto p){
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

    public static SituacaoReta cohenSutherlandClip(Ponto p1, Ponto p2){
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
