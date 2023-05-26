package root;

import static root.Position.differenzaH;
import static root.Position.distanzaEuclidea;

public class MatriceCitta
{
    private final double[][] mat = new double[Main.lista_citta.size()][Main.lista_citta.size()];
    public double[][] getMat() {
        return mat;
    }
    public double getDistanza(int r, int c)
    {
        return mat[r][c];
    }

    /**
     * Crea le matrici con le distanze calcolate o con la distanza euclidea o con la differenza delle altezze in base al team di riferimento
     * @param team team di riferimento
     */
    public void creaMatrice(Team team)
    {
        for (int i = 0; i < Main.lista_citta.size(); i++)
        {
            for (int j = 0; j < Main.lista_citta.size(); j++)
            {
                if(team.getNome().equals(Costanti.NOME_TEAM1))
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))      //calcolo valore solo se le due città sono collegate
                    {
                        this.mat[i][j] = distanzaEuclidea(Main.lista_citta.get(i).getCoordinate(),Main.lista_citta.get(j).getCoordinate());
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
                else
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))          //calcolo valore solo se le due città sono collegate
                    {
                        this.mat[i][j] = differenzaH(Main.lista_citta.get(i).getCoordinate(),Main.lista_citta.get(j).getCoordinate());
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
            }
        }
    }
}
