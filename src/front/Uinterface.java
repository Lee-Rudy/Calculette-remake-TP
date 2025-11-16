package front;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import calcul.Calcul;
import calcul.InvalidCoefficientException;
import calcul.Validator;
import calcul.HistoryManager;

public class Uinterface extends JFrame 
{
    public JTextField champA = new JTextField(10);
    public JTextField champB = new JTextField(10);
    public JTextField champC = new JTextField(10);
    public JLabel messageUser = new JLabel("Résultat :");

    // ---------------------------------------------------------
    // Constructeur (design interface)
    public Uinterface() 
    {
        super("Calculette – Équation du second degré");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Message en haut
        messageUser.setHorizontalAlignment(SwingConstants.CENTER);
        messageUser.setFont(new Font("Segoe UI", Font.BOLD, 16));
        messageUser.setForeground(new Color(30, 30, 30));
        add(messageUser, BorderLayout.NORTH);

        // Zone centrale avec labels + champs
        JPanel center = new JPanel(new GridLayout(3, 2, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        center.setBackground(new Color(245, 245, 245));

        JLabel lblA = new JLabel("Coefficient a :");
        JLabel lblB = new JLabel("Coefficient b :");
        JLabel lblC = new JLabel("Coefficient c :");

        lblA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblC.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        center.add(lblA); center.add(champA);
        center.add(lblB); center.add(champB);
        center.add(lblC); center.add(champC);

        add(center, BorderLayout.CENTER);

        // Boutons bas
        JButton btnCalc = new JButton("Calculer");
        JButton btnHist = new JButton("Historique");

        stylizeButton(btnCalc, new Color(52, 152, 219));   // bleu
        stylizeButton(btnHist, new Color(46, 204, 113));   // vert

        btnCalc.addActionListener(this::resultSecondDegre);
        btnHist.addActionListener(e -> openHistoryFile());

        JPanel south = new JPanel();
        south.setBackground(new Color(245, 245, 245));
        south.add(btnCalc);
        south.add(btnHist);

        add(south, BorderLayout.SOUTH);

        // Taille + centrage
        setSize(420, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ---------------------------------------------------------
    // Function calculer equation second degré
    public void resultSecondDegre(ActionEvent e) 
    {
        double a = 0, b = 0, c = 0;
        Double delta = null;

        try 
        {
            a = Validator.parseAndValidateNumber(champA.getText(), "a");
            b = Validator.parseAndValidateNumber(champB.getText(), "b");
            c = Validator.parseAndValidateNumber(champC.getText(), "c");

            Validator.validateA(a);

            Calcul calcul = new Calcul(a, b, c);
            double[] resultat = calcul.calculSeconddegre();
            delta = calcul.getDelta();

            String msg;

            if (resultat == null) 
            {
                msg = "Aucune solution (delta < 0)";
                messageUser.setText(msg);
                HistoryManager.saveHistory(a, b, c, delta, msg, true);
            }
            else if (resultat.length == 1) 
            {
                msg = "Une seule solution : x = " + resultat[0];
                messageUser.setText(msg);
                HistoryManager.saveHistory(a, b, c, delta, msg, false);
            }
            else 
            {
                msg = "Deux solutions : x1 = " + resultat[0] + " , x2 = " + resultat[1];
                messageUser.setText(msg);
                HistoryManager.saveHistory(a, b, c, delta, msg, false);
            }
        } 
        catch (InvalidCoefficientException ex) 
        {
            String msg = "Erreur : " + ex.getMessage();
            messageUser.setText(msg);
            HistoryManager.saveHistory(a, b, c, delta, msg, true);
        }
        catch (Exception ex) 
        {
            String msg = "Erreur inattendue : " + ex.getMessage();
            messageUser.setText(msg);
            ex.printStackTrace();
            HistoryManager.saveHistory(a, b, c, delta, msg, true);
        }
    }

    // ---------------------------------------------------------
    // Bouton Historique – ouverture du fichier
    public void openHistoryFile() 
    {
        try 
        {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe", "history.txt");
            pb.start();
        } 
        catch (Exception e) 
        {
            messageUser.setText("Impossible d’ouvrir l’historique.");
        }
    }

    // ---------------------------------------------------------
    // Style pour les boutons
    private void stylizeButton(JButton btn, Color bgColor)
    {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
    }

    // ---------------------------------------------------------
    // Main
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(Uinterface::new);
    }
}
