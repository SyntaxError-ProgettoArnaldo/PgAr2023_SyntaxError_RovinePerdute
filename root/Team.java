package root;


import java.util.ArrayList;


public class Team
{
    private final String nome;
    private double carburante;
    private final ArrayList<Citta> percorso = new ArrayList<>();
    private final MatriceCitta mat = new MatriceCitta();
    public Team(String nome) {
        this.nome = nome;
        this.mat.creaMatrice(this);
    }
    public String getNome() {
        return nome;
    }

    public MatriceCitta getMat() {
        return mat;
    }


    public double getCarburante() {
        return carburante;
    }

    public ArrayList<Citta> getPercorso() {
        return percorso;
    }

    public void setCarburante(double carburante) {
        this.carburante = carburante;
    }

    /**
     * Setta il percorso migliore nel team
     * @param path percorso migliore calcolato con dijkstra
     */

    public void setPercorso(ArrayList<Integer> path)
    {
        for (Integer integer : path) {
            for (int j = 0; j < Main.lista_citta.size(); j++)
            {
                if (Main.lista_citta.get(j).getId() == integer)
                {
                    this.percorso.add(Main.lista_citta.get(j));
                }
            }
        }
    }
}
