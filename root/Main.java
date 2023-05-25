package root;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.time.*;

import static root.Xml.*;

public class Main
{
    public static ArrayList<Citta> lista_citta= new ArrayList<>();
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        leggiCitta();

        ArrayList<Team> lista_team = new ArrayList<>();
        lista_team.add(new Team("Tonatiuh"));
        lista_team.add(new Team("Metztli"));

        for (int i = 0; i < lista_team.size(); i++) {
            Dijkstra.findShortestPath(lista_team.get(i));
        }



        scriviCitta(lista_team);

    }
}
