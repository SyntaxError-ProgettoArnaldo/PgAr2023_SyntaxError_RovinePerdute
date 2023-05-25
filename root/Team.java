package root;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import static root.Costanti.*;
import static root.Position.*;


public class Team
{
    private String nome;
    private double carburante;
    private ArrayList<Citta> percorso = new ArrayList<>();
    private MatriceCitta mat = new MatriceCitta();

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

    public void setPercorso(ArrayList<Integer> path)
    {
        for (Integer integer : path) {
            for (int j = 0; j < Main.lista_citta.size(); j++) {
                if (Main.lista_citta.get(j).getId() == integer) {
                    this.percorso.add(Main.lista_citta.get(j));
                }
            }
        }

    }

    public Team(String nome, double carburante, ArrayList<Citta> percorso) {
        this.nome = nome;
        this.carburante = carburante;
        this.percorso = percorso;
    }

    public Team(String nome) {
        this.nome = nome;
        this.mat.creaMatrice(this);
    }










    @Override
    public String toString() {
        return "Team{" +
                "nome='" + nome + '\'' +
                ", carburante=" + carburante +
                ", percorso=" + percorso +
                '}';
    }



}
