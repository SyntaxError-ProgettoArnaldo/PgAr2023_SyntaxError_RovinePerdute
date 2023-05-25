package root;

import static root.Position.differenzaH;
import static root.Position.distanzaEuclidea;

public class MatriceCitta
{
    private double mat[][] = new double[Main.lista_citta.size()][Main.lista_citta.size()];

    /**
     * Crea le matrici con le distanze calcolate o con la distanza euclidea o con la differenza delle altezze in base al team di riferimento
     */
    public void creaMatrice(Team team)
    {
        for (int i = 0; i < Main.lista_citta.size(); i++) {
            for (int j = 0; j < Main.lista_citta.size(); j++) {
                if(team.getNome().equals("Tonatiuh"))
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))
                    {
                        this.mat[i][j] = distanzaEuclidea(Main.lista_citta.get(i).getCoordinate(),Main.lista_citta.get(j).getCoordinate());
                        //this.mat[i][j] = i+1;
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
                else
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))
                    {
                        this.mat[i][j] = differenzaH(Main.lista_citta.get(i).getCoordinate(),Main.lista_citta.get(j).getCoordinate());
                        //this.mat[i][j]= i+3;
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
            }
        }
    }

    public double getDistanza(int r, int c)
    {
        return mat[r][c];
    }

    public double[][] getMat() {
        return mat;
    }
}
