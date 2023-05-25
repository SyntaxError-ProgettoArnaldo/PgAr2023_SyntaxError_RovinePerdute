package root;

import UnibsLib.AnsiColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static root.Costanti.INFINITY;

public final class Dijkstra
{
    /**
     * Algoritmo di dijkstra per calcolare il percorso migliore
     */

    public static void findShortestPath(Team team) {

        MatriceCitta mat1 = team.getMat();
        int indice_rovine = mat1.getMat().length-1;
        int n = mat1.getMat().length;

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
                double nextDistance = mat1.getDistanza(currentCityIndex,i);


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
        team.setCarburante(distanze[indice_rovine]);

        team.setPercorso(percorso);

        System.out.println(AnsiColors.GREEN+"Percorso"+AnsiColors.RED+" "+team.getNome()+AnsiColors.RESET+AnsiColors.GREEN+" calcolato correttamente"+AnsiColors.RESET);
    }

    /**
     * @param previousCities lista degli id delle città
     * @param lastCityIndex indice dell'ultima città
     * @return Arraylist che contiene gli indici delle città per il percorso migliore
     */

    private static ArrayList<Integer> creaPercorso(int[] previousCities, int lastCityIndex) {
        ArrayList<Integer> percorso = new ArrayList<>();

        int currentIndex = lastCityIndex;
        while (currentIndex != -1) {
            percorso.add(0, currentIndex);
            currentIndex = previousCities[currentIndex];
        }

        return percorso;
    }
}
