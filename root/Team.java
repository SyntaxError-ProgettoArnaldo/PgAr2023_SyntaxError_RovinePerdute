package root;

import UnibsLib.PrettyStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static root.Costanti.*;


public class Team
{
    private String nome;
    private double carburante;
    private ArrayList<Citta> percorso = new ArrayList<>();
    private double mat[][] = new double[Main.lista_citta.size()][Main.lista_citta.size()];

    public String getNome() {
        return nome;
    }

    public double getCarburante() {
        return carburante;
    }

    public ArrayList<Citta> getPercorso() {
        return percorso;
    }

    public double[][] getMat() {
        return mat;
    }

    public void setPercorso(ArrayList<Integer> path)
    {
        for (int i = 0; i < path.size(); i++) {
            for (int j = 0; j < Main.lista_citta.size(); j++) {
                if(Main.lista_citta.get(j).getId()==path.get(i))
                {
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
        creaMatrice();
    }



    public void creaMatrice()
    {
        for (int i = 0; i < Main.lista_citta.size(); i++) {
            for (int j = 0; j < Main.lista_citta.size(); j++) {
                if(this.nome.equals("Tonatiuh"))
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))
                    {
                        this.mat[i][j] = CalcoloRotta.distanzaEuclidea(Main.lista_citta.get(i).getCoord(),Main.lista_citta.get(j).getCoord());
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
                        this.mat[i][j] = CalcoloRotta.differenzaH(Main.lista_citta.get(i).getCoord(),Main.lista_citta.get(j).getCoord());
                        //this.mat[i][j]= i+3;
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
            }
        }
    }

    public void scriviMat()
    {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.printf("%10.2f \t",mat[i][j]);
                //System.out.printf("%4.8s",PrettyStrings.center(String.valueOf(mat[i][j]),20));

            }
            System.out.print("\n");
        }
    }


    public ArrayList<Integer> findShortestPath() {

        double[][] mat1 = this.mat;
        int indice_rovine = mat1.length-1;
        int n = mat1.length;

        double[] distanze = new double[n];
        Arrays.fill(distanze, INFINITY);
        distanze[0] = 0.0;

        int[] indiciCitta = new int[n];
        Arrays.fill(indiciCitta, -1);

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Double.compare(distanze[a], distanze[b]));
        queue.offer(0);

        while (!queue.isEmpty()) {
            int currentCityIndex = queue.poll();

            if (currentCityIndex == indice_rovine) {
                break;
            }

            for (int i = 0; i < n; i++) {
                double nextDistance = mat1[currentCityIndex][i];

                if (nextDistance == 0.0) {
                    continue;  // Ignore self-loops
                }

                double totalDistance = distanze[currentCityIndex] + nextDistance;

                if (totalDistance < distanze[i]) {
                    distanze[i] = totalDistance;
                    indiciCitta[i] = currentCityIndex;
                    queue.offer(i);
                }
            }
        }

        ArrayList<Integer> percorso = creaPercorso(indiciCitta, indice_rovine);
        this.carburante=distanze[indice_rovine];

        this.setPercorso(percorso);


        return percorso;
    }

    private static ArrayList<Integer> creaPercorso(int[] previousCities, int lastCityIndex) {
        ArrayList<Integer> percorso = new ArrayList<>();

        int currentIndex = lastCityIndex;
        while (currentIndex != -1) {
            percorso.add(0, currentIndex);
            currentIndex = previousCities[currentIndex];
        }

        return percorso;
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
