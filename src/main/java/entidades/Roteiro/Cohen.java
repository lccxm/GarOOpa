package entidades.Roteiro;

public class Cohen {
    public static void main(String[] args) {
        Ponto pMax = new Ponto(5, 7);
        Ponto pMin = new Ponto(7, 5);
        Area a = new Area(pMax, pMin);
        Cohen c = new Cohen(a);
        Ponto p1 = new Ponto(5,7);
        Ponto p2 = new Ponto(7,5);
        System.out.println(c.cohenSutherlandClip(p1, p2));
    }

    public static int INSIDE = 0;
    public static int LEFT = 1;
    public static int RIGHT = 2;
    public static int BOTTOM = 4;
    public static int TOP = 8;

    private Area area;
    private int xMax; 
    private int yMax; 
    private int xMin; 
    private int yMin; 
    
    public Cohen(Area area){
        this.area = area;
        xMax = (int)area.getPSupEsq().getX();
        yMax = (int)area.getPSupEsq().getY();
        xMin = (int)area.getpInfDir().getX();
        yMin = (int)area.getpInfDir().getY();
        System.out.printf("XMax: %d, xMin: %d\nyMax: %d, yMin: %d\n", xMax, xMin, yMax, yMin);
    }

    public int computeCode(Ponto p){
        int code = INSIDE;
        if (p.getX() < xMin)
            code |= LEFT; //System.out.println("1");
        else if (p.getX() > xMax)
            code |= RIGHT; //System.out.println("2");
        if (p.getY() < yMin)
            code |= BOTTOM; //System.out.println("3");
        else if (p.getY() > yMax)
            code |= TOP; //System.out.println("4");

        return code;
    }

    public SituacaoReta cohenSutherlandClip(Ponto p1, Ponto p2){
        System.out.printf("p1.x: %f, p1.y: %f\np2.x: %f, p2.y: %f\n", p1.getX(), p1.getY(), p2.getX(), p2.getY());
        int code1 = computeCode(p1);
        int code2 = computeCode(p2);
        boolean accept = false;

        while (true){
            if (code1 ==0 && code2 ==0){
                accept = true;
                break;
            }
            else
                break;
        } 
        if (accept)
            return SituacaoReta.INTERSECTA;
        else
            return SituacaoReta.TODA_FORA;
    }

        
}
