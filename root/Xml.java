package root;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public final class Xml
{
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
    }

    /**
     * Scrive su un file xml
     */
    /*
    public static void scriviPersone(ArrayList<Persona> listaPersone, ArrayList<CodiceFiscale> listaCF)
    {

        inizializzaXMLScrittura(Costanti.NOME_FILE_OUTPUT_XML);
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement(Costanti.TAG_OUTPUT); // scrittura del tag radice <programmaArnaldo>
            xmlw.writeStartElement(Costanti.TAG_PERSONE);
            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(listaPersone.size()));
            for (int i = 0; i < listaPersone.size(); i++) {
                xmlw.writeStartElement(Costanti.TAG_PERSONA); // scrittura del tag autore...
                xmlw.writeAttribute(Costanti.TAG_ID, Integer.toString(i)); // ...con attributo id...
                xmlw.writeStartElement(Costanti.TAG_NOME);
                xmlw.writeCharacters(listaPersone.get(i).getNome());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_COGNOME);
                xmlw.writeCharacters(listaPersone.get(i).getCognome());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_SESSO);
                xmlw.writeCharacters(String.valueOf(listaPersone.get(i).getSesso()));
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_COMUNE_NASCITA);
                xmlw.writeCharacters(listaPersone.get(i).getLuogo());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_DATA_NASCITA);
                xmlw.writeCharacters(listaPersone.get(i).getDataDiNascita().toString());
                xmlw.writeEndElement();
                xmlw.writeStartElement(Costanti.TAG_CF);
                xmlw.writeCharacters(listaPersone.get(i).getCodiceFiscale());
                xmlw.writeEndElement();
                xmlw.writeEndElement();

            }
            xmlw.writeEndElement(); // chiusura di </programmaArnaldo>

            xmlw.writeStartElement(Costanti.TAG_CODICE);
            xmlw.writeStartElement(Costanti.TAG_INVALIDI);

            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(getNumeroInvalidi(listaCF)));
            for (CodiceFiscale codiceFiscale : listaCF) {
                if (codiceFiscale.getValiditaCF().equals(ValiditaCF.INVALIDO)) {
                    xmlw.writeStartElement(Costanti.TAG_CODICE);
                    xmlw.writeCharacters(codiceFiscale.getNome());
                    xmlw.writeEndElement();
                }
            }
            xmlw.writeEndElement();

            xmlw.writeStartElement(Costanti.TAG_SPAIATI);

            xmlw.writeAttribute(Costanti.ATT_NUMERO,String.valueOf(getNumeroSpaiati(listaCF)));
            for (CodiceFiscale codiceFiscale : listaCF) {
                if (codiceFiscale.getValiditaCF().equals(ValiditaCF.SPAIATO)) {
                    xmlw.writeStartElement(Costanti.TAG_CODICE);
                    xmlw.writeCharacters(codiceFiscale.getNome());
                    xmlw.writeEndElement();
                }
            }
            xmlw.writeEndElement();
            xmlw.writeEndElement();



            xmlw.writeEndDocument(); // scrittura della fine del documento
            xmlw.flush();// svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        }
        catch (Exception e)
        {   // se c’è un errore viene eseguita questa parte
            System.out.println(Costanti.ERR_SCRITTURA);
        }
    }
    */

}
