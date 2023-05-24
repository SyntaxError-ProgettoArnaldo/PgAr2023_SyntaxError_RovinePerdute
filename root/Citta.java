package root;

import java.util.ArrayList;

public class Citta
{
    private int id;
    private Position coordinate;
    private String nome;
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

    public ArrayList<Integer> getId_link() {
        return id_link;
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
    @Override
    public String toString() {
        return "Città{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", nome='" + nome + '\'' +
                ", id_link=" + id_link +
                '}';
    }
}
