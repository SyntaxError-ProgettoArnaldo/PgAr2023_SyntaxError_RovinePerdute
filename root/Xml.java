package root;

import UnibsLib.AnsiColors;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public final class Xml
{
    public static final int NUMERO_TEAM = 2;
    static XMLInputFactory xmlif = null;
    static XMLStreamReader xmlr = null;
    static XMLOutputFactory xmlof = null;
    static XMLStreamWriter xmlw = null;

    /**
     * @param filename path del file con cui inizializzare
     */
    public static void inizializzaXMLLettura(String filename) throws FileNotFoundException, XMLStreamException {
        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
    }
    /**
     * @param filename path del file con cui inizializzare
     */
    public static void inizializzaXMLScrittura(String filename)
    {
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), "utf-8"); xmlw.writeStartDocument("utf-8", "2.0");
        } catch (Exception e) {
            System.out.println(Costanti.ERR_INIZ_WRITER);
            System.out.println(e.getMessage());
        }

    }


    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     */
    public static void leggiCitta() throws XMLStreamException, FileNotFoundException {
        inizializzaXMLLettura(Costanti.PATH_INPUT);

        int id = -1;
        String nome = "";
        Position p = new Position();
        ArrayList<Integer> link = new ArrayList<>();

        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_CITTA:
                        {


                           for (int i=0; i<xmlr.getAttributeCount();i++)
                           {
                                if(xmlr.getAttributeLocalName(i).equals("id"))
                                {
                                    id = Integer.parseInt(xmlr.getAttributeValue(i));
                                }
                               if(xmlr.getAttributeLocalName(i).equals("name"))
                               {
                                   nome = xmlr.getAttributeValue(i);
                               }
                               if(xmlr.getAttributeLocalName(i).equals("x"))
                               {
                                   p.setX(Integer.parseInt(xmlr.getAttributeValue(i)));
                               }
                               if(xmlr.getAttributeLocalName(i).equals("y"))
                               {
                                   p.setY(Integer.parseInt(xmlr.getAttributeValue(i)));
                               }
                               if(xmlr.getAttributeLocalName(i).equals("h"))
                               {
                                   p.setH(Integer.parseInt(xmlr.getAttributeValue(i)));
                               }
                           }

                            break;
                        }

                        case Costanti.TAG_LINK:
                        {
                            link.add(Integer.parseInt(xmlr.getAttributeValue(0)));
                        }

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                {
                    if(xmlr.getLocalName().equals(Costanti.TAG_CITTA))
                    {
                        Main.lista_citta.add(new Citta(id,p,nome,link));
                        link = new ArrayList<>();
                        p = new Position();
                    }

                }
            }
            xmlr.next();
        }
        xmlr.close();
        System.out.println(AnsiColors.GREEN+"Lettura file XML completata"+AnsiColors.RESET);
    }

    /**
     * Scrive su un file xml
     */
    public static void scriviCitta(ArrayList<Team> lista_team)
    {
        try { // blocco try per raccogliere eccezioni
        inizializzaXMLScrittura(Costanti.NOME_FILE_OUTPUT_XML);
        xmlw.writeStartElement(Costanti.TAG_ROUTES); // scrittura del tag radice <programmaArnaldo>

            for (int i = 0; i < NUMERO_TEAM; i++)
            {
                xmlw.writeStartElement(Costanti.TAG_ROUTE);
                xmlw.writeAttribute(Costanti.ATT_NOME,String.valueOf(lista_team.get(i).getNome()));
                xmlw.writeAttribute(Costanti.ATT_COST,String.valueOf(lista_team.get(i).getCarburante()));
                xmlw.writeAttribute(Costanti.ATT_CITIES,String.valueOf(lista_team.get(i).getPercorso().size()));
                for (int j = 0; j < lista_team.get(i).getPercorso().size(); j++) {
                    xmlw.writeStartElement(Costanti.TAG_CITTA);
                    xmlw.writeAttribute(Costanti.ATT_ID,String.valueOf(lista_team.get(i).getPercorso().get(j).getId()));
                    xmlw.writeAttribute(Costanti.ATT_NOME_CITTA,String.valueOf(lista_team.get(i).getPercorso().get(j).getNome()));
                    xmlw.writeEndElement();

                }
                xmlw.writeEndElement(); // chiusura di </programmaArnaldo>

            }

            xmlw.writeEndElement();
            xmlw.writeEndDocument(); // scrittura della fine del documento
            xmlw.flush();// svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        }
        catch (Exception e)
        {   // se c’è un errore viene eseguita questa parte
            System.out.println(Costanti.ERR_SCRITTURA);
        }
        System.out.println(AnsiColors.GREEN+"Scrittura file XML completata"+AnsiColors.RESET);
    }




}
