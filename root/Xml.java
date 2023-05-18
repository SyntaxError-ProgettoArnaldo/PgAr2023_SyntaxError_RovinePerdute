package root;

import UnibsLib.AnsiColors;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
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
    public static void leggiPersone(ArrayList<Persona> listaPersone) throws XMLStreamException, FileNotFoundException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_PERSONE);
        int id=0;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_PERSONA:
                        {
                            id = Integer.parseInt(xmlr.getAttributeValue(0)); //id attributo è la posizione nell array listaPersone
                            listaPersone.add(new Persona()); //creare la persona
                        }
                        break;
                        case Costanti.TAG_NOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)  //vai fino a caratteri
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone.get(id).setNome(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_COGNOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone.get(id).setCognome(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_SESSO:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone.get(id).setSesso(xmlr.getText().charAt(0));
                            }

                            break;
                        }
                        case Costanti.TAG_COMUNE_NASCITA:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone.get(id).setLuogo(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_DATA_NASCITA:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaPersone.get(id).setDataDiNascita(LocalDate.parse(xmlr.getText()));
                            }

                            break;
                        }

                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();
        id++;
        System.out.println(AnsiColors.GREEN+"Sono state acquisite "+AnsiColors.RED+id+AnsiColors.GREEN+" persone correttamente"+AnsiColors.RESET);
    }

    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     */
    public static void leggiComuni(ArrayList<Comune> listaComuni) throws XMLStreamException, FileNotFoundException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_COMUNI);
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_COMUNE:
                        {
                            id++;
                            listaComuni.add(new Comune());
                        }
                        break;
                        case Costanti.TAG_NOME:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaComuni.get(id).setNomeComune(xmlr.getText());
                            }

                            break;
                        }
                        case Costanti.TAG_CODICE:
                        {
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaComuni.get(id).setCodiceComune(xmlr.getText());
                            }

                            break;
                        }


                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();
        id++;
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+id+AnsiColors.GREEN+" comuni correttamente"+AnsiColors.RESET);
    }
    /**
     * Legge il file xml e crea gli oggetti inserendoli nell array
     */
    public static void leggiCF(ArrayList<CodiceFiscale> listaCF) throws XMLStreamException, FileNotFoundException {
        inizializzaXMLLettura(Costanti.PATH_INPUT_CF);
        int id=-1;
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT:
                    switch(xmlr.getLocalName())
                    {
                        case Costanti.TAG_CODICE:
                        {
                            id++;
                            listaCF.add(new CodiceFiscale());
                            while(xmlr.hasNext() && xmlr.getEventType() != XMLStreamConstants.CHARACTERS)
                                xmlr.next();
                            if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                            {
                                listaCF.get(id).setNome(xmlr.getText());
                            }
                        }
                        break;

                    }
                    break;

            }
            xmlr.next();
        }
        xmlr.close();
        id++;
        System.out.println(AnsiColors.GREEN+"Sono stati acquisiti "+AnsiColors.RED+id+AnsiColors.GREEN+" codici fiscali correttamente"+AnsiColors.RESET);

    }

    /**
     * Scrive su un file xml
     */
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


}
