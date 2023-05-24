package root;

import UnibsLib.PrettyStrings;

import java.util.ArrayList;


public class Team
{
    private String nome;
    private double carburante;
    private ArrayList<Citta> percorso;
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
                        //this.mat[i][j] = CalcoloRotta.distanzaEuclidea(Main.lista_citta.get(i).getCoord(),Main.lista_citta.get(j).getCoord());
                        this.mat[i][j] = i+1;
                    }
                    else {
                        this.mat[i][j] = 0;
                    }
                }
                else
                {
                    if(Main.lista_citta.get(i).sonoCollegate(Main.lista_citta.get(j).getId()))
                    {
                        //this.mat[i][j] = CalcoloRotta.differenzaH(Main.lista_citta.get(i).getCoord(),Main.lista_citta.get(j).getCoord());
                        this.mat[i][j]= i+3;
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









    static int minDistance(double[] dist, Boolean[] sptSet)
    {
        // Initialize min value
        double min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < Main.lista_citta.size(); v++)
            if (sptSet[v] == false && dist[v] <= min ) {
                min = dist[v];
                min_index = v;

            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    static void printSolution(double[] dist, int n)
    {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < Main.lista_citta.size(); i++)
            System.out.println(i + " tt " + dist[i]);
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    static void dijkstra(double graph[][], int src)
    {
        double dist[] = new double[Main.lista_citta.size()]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[Main.lista_citta.size()];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < Main.lista_citta.size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;



        // Find shortest path for all vertices
        for (int count = 0; count < Main.lista_citta.size() - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < Main.lista_citta.size(); v++)
            {

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
            }

        }

        // print the constructed distance array
        printSolution(dist, Main.lista_citta.size());

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
