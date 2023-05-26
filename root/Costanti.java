package root;

import UnibsLib.AnsiColors;

public class Costanti
{

    public static final int INFINITY = Integer.MAX_VALUE;

    //Path
    public static final String PATH_INPUT = "test_file/PgAr_Map_5.xml";

    //Stringhe
    public static final String ERR_INIZ_WRITER = "Errore nell inizializzazione del writer" ;
    public static final String NOME_FILE_OUTPUT_XML = "Routes.xml";
    public static final String ERR_SCRITTURA = "Errore nella scrittura";
    public static final String STAMPA_PERCORSO = AnsiColors.GREEN + "Percorso" + AnsiColors.RED + " %s"+ AnsiColors.RESET + AnsiColors.GREEN + " calcolato correttamente" + AnsiColors.RESET + "\n";
    public static final String LETTURA_FILE_COMPLETATA = AnsiColors.GREEN + "Lettura file XML completata" + AnsiColors.RESET;
    public static final String SCRITTURA_XML_COMPLETATA = AnsiColors.GREEN + "Scrittura file XML completata" + AnsiColors.RESET;
    public static final String NOME_TEAM1 = "Tonatiuh";
    public static final String NOME_TEAM2 = "Metztli";

    //TAG XML
    public static final String TAG_CITTA = "city";
    public static final String TAG_LINK = "link";
    public static final String TAG_ROUTES = "routes";
    public static final String TAG_ROUTE = "route";

    //ATTRIBUTI TAG XML
    public static final String ATT_NOME = "team";
    public static final String ATT_CITIES = "cities";
    public static final String ATT_COST = "cost";
    public static final String ATT_ID = "id";
    public static final String ATT_NOME_CITTA = "name";
}
