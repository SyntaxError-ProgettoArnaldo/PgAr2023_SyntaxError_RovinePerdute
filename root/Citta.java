package root;

import java.util.ArrayList;

public class Citta
{
    private int id;
    private Position coord;
    private String nome;
    ArrayList<Integer> id_link;

    public Citta(int id, Position coord, String nome, ArrayList<Integer> id_link) {
        this.id = id;
        this.coord = coord;
        this.nome = nome;
        this.id_link = id_link;
    }

    public int getId() {
        return id;
    }

    public Position getCoord() {
        return coord;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Integer> getId_link() {
        return id_link;
    }

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
        return "Citta{" +
                "id=" + id +
                ", coord=" + coord +
                ", nome='" + nome + '\'' +
                ", id_link=" + id_link +
                '}';
    }
}
