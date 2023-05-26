package root;

public class Position
{
    private int x;
    private int y;
    private int h;

    public Position(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    /**
     * Calcola distanza euclidea
     * @param p1 punto di partenza
     * @param p2 punto di arrivo
     * @return distanza tra i due punti
     */
    public static double distanzaEuclidea(Position p1, Position p2)
    {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY()-p1.getY(),2));
    }

    /**
     * Calcola la differenza tra le due altezze
     * @param p1 punto di partenza
     * @param p2 punto di arrivo
     * @return differenza delle altezze
     */

    public static int differenzaH(Position p1, Position p2)
    {
        return Math.abs(p1.getH()-p2.getH());
    }
}
