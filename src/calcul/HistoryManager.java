package calcul;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryManager 
{

    private static final String FILE_NAME = "history.txt";
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

     //Enregistre tous les actions : success ou tous les erreurs ou faute de saisie
     
    public static void saveHistory(double a, double b, double c,
                                   Double delta,
                                   String resultMessage,
                                   boolean isError) 
    {
        String timestamp = LocalDateTime.now().format(FORMAT);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(timestamp).append("] ");

        if (!isError) 
        {
            sb.append("SUCCESS | ");
        } else 
        {
            sb.append("ERROR   | ");
        }

        sb.append("a=").append(a)
          .append(", b=").append(b)
          .append(", c=").append(c);

        if (delta != null) 
        {
            sb.append(" | delta=").append(delta);
        }

        sb.append(" | message=").append(resultMessage);

        sb.append("\n--------------------------------------------------\n");

        // Écriture dans le fichier
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) 
        {
            writer.write(sb.toString());
        } 
        catch (IOException e) 
        {
            System.err.println("Erreur d'écriture dans l'historique : " + e.getMessage());
        }
    }
}
