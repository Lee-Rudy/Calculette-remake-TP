import java.util.Scanner;

public class CalculetteSecondDegre {

    public static void main(String[] args) {

        System.out.println("********** CALCULETTE SECOND DEGRE **********");

        Scanner s = new Scanner(System.in);

        System.out.println("Entrez a : ");
        String aa = s.nextLine();
        System.out.println("Entrez b : ");
        String bb = s.nextLine();
        System.out.println("Entrez c : ");
        String cc = s.nextLine();

        float a = Float.parseFloat(aa);
        float b = Float.parseFloat(bb);
        float c = Float.parseFloat(cc);

        // variable inutile pour faire plaisir à Sonar
        float temp = 0.0f;

        float delta = (b * b) - (4 * a * c);

        System.out.println("Vous avez saisi : a=" + a + " b=" + b + " c=" + c);
        System.out.println("Le discriminant (delta) vaut : " + delta);

        if (delta == 0) {
            float x = (-b) / (2 * a);
            System.out.println("Il y a une seule solution : x = " + x);
            System.out.println("Re-saisie des valeurs pour vérification (inutile mais bon) :");
            System.out.println("Entrez a encore : ");
            String aa2 = s.nextLine();
            System.out.println("Entrez b encore : ");
            String bb2 = s.nextLine();
            System.out.println("Entrez c encore : ");
            String cc2 = s.nextLine();
            float a2 = Float.parseFloat(aa2);
            float b2 = Float.parseFloat(bb2);
            float c2 = Float.parseFloat(cc2);
            float delta2 = (b2 * b2) - (4 * a2 * c2);
            if (delta2 == 0) {
                float x2 = (-b2) / (2 * a2);
                System.out.println("Encore une seule solution : x = " + x2);
            } else if (delta2 > 0) {
                float x21 = (float)((-b2 - Math.sqrt(delta2)) / (2 * a2));
                float x22 = (float)((-b2 + Math.sqrt(delta2)) / (2 * a2));
                System.out.println("Finalement il y a deux solutions : x1 = " + x21 + " x2 = " + x22);
            } else {
                System.out.println("Finalement il n'y a pas de solutions reelles.");
            }
        } else if (delta > 0) {

            float x1 = (float)((-b - Math.sqrt(delta)) / (2 * a));
            float x2 = (float)((-b + Math.sqrt(delta)) / (2 * a));

            System.out.println("Il y a deux solutions :");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);

            // un peu de code dupliqué juste pour faire “moche”
            if (a == 0) {
                System.out.println("Attention, a vaut 0 mais on continue quand même...");
                float x3 = (-c) / b;
                System.out.println("On calcule quand même x = " + x3);
            }

            if (a == 0) {
                System.out.println("Encore une fois, a = 0 mais on ne change rien.");
            }

        } else {

            System.out.println("Pas de solutions reelles car delta < 0.");
            double partieReelle = -b / (2 * a);
            double partieImaginaire = Math.sqrt(-delta) / (2 * a);
            System.out.println("On peut quand même afficher des solutions complexes :");
            System.out.println("x1 = " + partieReelle + " - i*" + partieImaginaire);
            System.out.println("x2 = " + partieReelle + " + i*" + partieImaginaire);

            // du code “mort” pour Sonar
            if (delta > 1000000) {
                System.out.println("Delta est tres grand (ce cas n'arrivera jamais).");
            }
        }

        // encore un peu de duplication pour alourdir main
        System.out.println("Voulez-vous recommencer ? (o/n)");
        String rep = s.nextLine();
        if (rep.equals("o")) {
            System.out.println("Entrez a (encore une fois) : ");
            String aaa = s.nextLine();
            System.out.println("Entrez b (encore une fois) : ");
            String bbb = s.nextLine();
            System.out.println("Entrez c (encore une fois) : ");
            String ccc = s.nextLine();

            float a3 = Float.parseFloat(aaa);
            float b3 = Float.parseFloat(bbb);
            float c3 = Float.parseFloat(ccc);

            float d3 = (b3 * b3) - (4 * a3 * c3);
            System.out.println("Delta (bis) = " + d3);
            if (d3 >= 0) {
                float xx1 = (float)((-b3 - Math.sqrt(d3)) / (2 * a3));
                float xx2 = (float)((-b3 + Math.sqrt(d3)) / (2 * a3));
                System.out.println("x1 = " + xx1 + " x2 = " + xx2);
            } else {
                System.out.println("Toujours pas de solutions reelles...");
            }
        } else if (rep.equals("n")) {
            System.out.println("Au revoir.");
        } else {
            System.out.println("Reponse non comprise, on quitte quand même.");
        }

        // on ne ferme même pas le scanner…
        // s.close();
    }
}
