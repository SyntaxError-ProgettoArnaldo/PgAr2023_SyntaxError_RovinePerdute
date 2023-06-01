package root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static root.Costanti.INFINITY;

public final class Dijkstra
{

    /**
     * Algoritmo di dijkstra per calcolare il percorso migliore
     * @param team Il Team per cui calcolare il percorso migliore
     */

    public static void findShortestPath(Team team)
    {
        MatriceCitta mat1 = team.getMat();   //matrice citta
        int indice_rovine = mat1.getMat().length-1;   //indice di arrivo
        int n = mat1.getMat().length;   //numero citta

        double[] distanze = new double[n];
        Arrays.fill(distanze, INFINITY);  // riempie array con INFINITY
        distanze[0] = 0.0;   //la prima in 0,0

        int[] indiciCitta = new int[n];
        Arrays.fill(indiciCitta, -1);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> distanze[a]));
        queue.offer(0);

        //finche la coda non è vuota continua
        while (!queue.isEmpty())
        {
            int currentCityIndex = queue.poll();

            if (currentCityIndex == indice_rovine)
            {
                break;
            }

            for (int i = 0; i < n; i++)
            {
                double nextDistance = mat1.getDistanza(currentCityIndex,i);


                if (nextDistance == 0.0)
                {
                    continue;  // Ignore self-loops
                }

                double totalDistance = distanze[currentCityIndex] + nextDistance;

                if (totalDistance < distanze[i])
                {
                    distanze[i] = totalDistance;
                    indiciCitta[i] = currentCityIndex;
                    queue.offer(i);
                }
            }
        }
        //crea il percorso con le citta effettive
        ArrayList<Integer> percorso = creaPercorso(indiciCitta, indice_rovine);
        //calcola carburante e lo setta
        team.setCarburante(distanze[indice_rovine]);
        //setta il percorso ottimale al team
        team.setPercorso(percorso);
        //stampa il percorso ottimale
        System.out.printf(Costanti.STAMPA_PERCORSO, team.getNome());
    }

    /**
     * Converte la lista di indici delle citta in una lista con gli oggetti Citta
     * @param previousCities lista degli id delle città
     * @param lastCityIndex indice dell'ultima città
     * @return Arraylist che contiene gli indici delle città per il percorso migliore
     */

    private static ArrayList<Integer> creaPercorso(int[] previousCities, int lastCityIndex)
    {
        ArrayList<Integer> percorso = new ArrayList<>();
        int currentIndex = lastCityIndex;

        while (currentIndex != -1)
        {
            percorso.add(0, currentIndex);
            currentIndex = previousCities[currentIndex];
        }
        return percorso;
    }
}
