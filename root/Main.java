package root;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main
{
    public static ArrayList<Citta> lista_citta= new ArrayList<>();
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        Xml.leggiCitta(lista_citta);

        Team tonatiuh = new Team("Tonatiuh");
        tonatiuh.scriviMat();
        System.out.println("------------------------------");
        Team metztli = new Team("Metztli");
        metztli.scriviMat();

        Team.dijkstra(tonatiuh.getMat(),12);











    }
}
