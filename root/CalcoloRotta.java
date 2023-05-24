package root;

import java.lang.*;

public final class CalcoloRotta
{

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


