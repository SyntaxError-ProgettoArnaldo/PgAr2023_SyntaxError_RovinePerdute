package root;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static root.Xml.*;

public class Main
{
    public static ArrayList<Citta> lista_citta= new ArrayList<>();
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        leggiCitta(lista_citta);

        Team team1 = new Team("Tonatiuh");
        Team team2 = new Team("Metztli");

        team1.findShortestPath();
        team2.findShortestPath();

        //System.out.println(team1.toString());
        //System.out.println(team2.toString());


        System.out.println("distanza = "+team1.getCarburante());
        System.out.print("percorso: ");
        for (int i = 0; i < team1.getPercorso().size(); i++) {
            System.out.print(team1.getPercorso().get(i).getNome()+" --> ");
        }
        System.out.println("\nnumero citta: "+team1.getPercorso().size());

        System.out.println("---------------------------------------------------");

        System.out.println("distanza = "+team2.getCarburante());
        System.out.print("percorso: ");
        for (int i = 0; i < team2.getPercorso().size(); i++) {
            System.out.print(team2.getPercorso().get(i).getNome()+"-->");
        }
        System.out.println("\nnumero citta: "+team2.getPercorso().size());
















    }
}
