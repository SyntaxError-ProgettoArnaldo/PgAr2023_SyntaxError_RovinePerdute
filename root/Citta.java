package root;

import java.util.ArrayList;

public class Citta
{
    private final int id;
    private final Position coordinate;
    private final String nome;
    ArrayList<Integer> id_link;

    public Citta(int id, Position coordinate, String nome, ArrayList<Integer> id_link) {
        this.id = id;
        this.coordinate = coordinate;
        this.nome = nome;
        this.id_link = id_link;
    }

    public int getId() {
        return id;
    }

    public Position getCoordinate() {
        return coordinate;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Serve per sapere se due città sono collegate, se si confronta una città con se stessa ritorna true
     * @param id id della città con cui si deve confrontare
     * @return true se sono collegate false altrimenti
     */
    public boolean sonoCollegate(int id)
    {
        if(this.id == id)
        {
            return true;
        }
        else
        {
            return id_link.contains(id);
        }
    }
}
