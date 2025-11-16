package calcul;

public class Validator 
{

    // Verifie que les entrés sont numériques
    public static double parseAndValidateNumber(String input, String fieldName) throws InvalidCoefficientException 
    {
        if (input == null || input.trim().isEmpty()) 
        {
            throw new InvalidCoefficientException("Le champ " + fieldName + " est vide.");
        }

        try 
        {
            String normalized = input.trim().replace(',', '.');
            return Double.parseDouble(normalized);
        } 
        catch (NumberFormatException e) 
        {
            throw new InvalidCoefficientException("Le coefficient " + fieldName + " doit être un nombre.");
        }
    }

    // Valide que le coefficient a n'est pas égal à 0
    public static void validateA(double a) throws InvalidCoefficientException 
    {
        if (a == 0) 
        {
            throw new InvalidCoefficientException("Le coefficient a ne peut pas être égal à 0 (pas une équation du second degré)");
        }
    }
}
