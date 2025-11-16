package front;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import calcul.Calcul;
import calcul.InvalidCoefficientException;
import calcul.Validator;

public class Uinterface extends JFrame 
{
    public JTextField champA = new JTextField(10);
    public JTextField champB = new JTextField(10);
    public JTextField champC = new JTextField(10);
    public JLabel messageUser = new JLabel("résultat :");

//----------------------------------------------------------------------------
    //window propriety
    public Uinterface() 
    {
        super("Calcul d'équation de second degré");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageUser.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageUser, BorderLayout.NORTH);
        // add(messageUser, BorderLayout.SOUTH);


        //3 champs : a, b, c
        JPanel center = new JPanel(new GridLayout(3, 2, 6, 6));
        center.add(new JLabel("a :"));
        center.add(champA);
        center.add(new JLabel("b :"));
        center.add(champB);
        center.add(new JLabel("c :"));
        center.add(champC);
        add(center, BorderLayout.CENTER);

        //bouton action
        JButton btn = new JButton("Calculer");
        btn.addActionListener(this::resultSecondDegre);
        JPanel south = new JPanel();
        south.add(btn);
        add(south, BorderLayout.SOUTH);

        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setVisible(true);

       // taille en petite
       //window au milieu
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
//----------------------------------------------------------------------------

//FUNCTION
// ------------------------------------------------------------------------------
    //Function calculer operation delta second degre
    public void resultSecondDegre(ActionEvent e) 
    {
        try 
        {
            double a = Validator.parseAndValidateNumber(champA.getText(), "a");
            double b = Validator.parseAndValidateNumber(champB.getText(), "b");
            double c = Validator.parseAndValidateNumber(champC.getText(), "c");

            Validator.validateA(a);

            Calcul calcul = new Calcul(a, b, c);
            double[] resultat = calcul.calculSeconddegre();

            if (resultat == null) 
            {
                messageUser.setText("Aucune solution (delta < 0)");
            } 
            else if (resultat.length == 1) 
            {
                messageUser.setText("Une seule solution : x = " + resultat[0]);
            } 
            else 
            {
                messageUser.setText("Deux solutions : x1 = " + resultat[1] + " , x2 = " + resultat[2]);
            }
        } 
        catch (InvalidCoefficientException ex) 
        {
            messageUser.setText("Erreur : " + ex.getMessage());
        }
        catch (Exception ex) 
        {
            messageUser.setText("Erreur inattendue.");
            ex.printStackTrace();
        }
    }

        
// -----------------------------------------------------------------------------

    // Function Parse text -> chiffre

    public Integer parseInt(String text) 
    {
        if (text == null) throw new NumberFormatException("null");
        String norm = text.trim().replace(',', '.');
        if (norm.isEmpty()) throw new NumberFormatException("null");
        return Integer.parseInt(norm);
    }


//======================================================
    //Main
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(Uinterface::new);
    }

    //test 2
}
