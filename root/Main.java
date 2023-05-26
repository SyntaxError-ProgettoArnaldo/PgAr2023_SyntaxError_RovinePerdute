package root;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static root.Xml.leggiCitta;
import static root.Xml.scriviCitta;

public class Main
{
    public static ArrayList<Citta> lista_citta= new ArrayList<>();
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        leggiCitta();

        ArrayList<Team> lista_team = new ArrayList<>();
        lista_team.add(new Team(Costanti.NOME_TEAM1));
        lista_team.add(new Team(Costanti.NOME_TEAM2));

        for (Team team : lista_team)
        {
            Dijkstra.findShortestPath(team);
        }

        scriviCitta(lista_team);

    }
}
