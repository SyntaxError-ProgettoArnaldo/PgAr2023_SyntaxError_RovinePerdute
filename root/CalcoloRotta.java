package root;

import java.lang.*;

public final class CalcoloRotta
{


    public static double distanzaEuclidea(Position p1, Position p2)
    {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY()-p1.getY(),2));
    }

    public static int differenzaH(Position p1, Position p2)
    {
        return Math.abs(p1.getH()-p2.getH());
    }




}


