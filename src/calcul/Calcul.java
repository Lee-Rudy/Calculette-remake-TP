package calcul;

public class Calcul 
{

    private double champA;
    private double champB;
    private double champC;
    private double delta;

    public Calcul(double champA, double champB, double champC) 
    {
        this.champA = champA;
        this.champB = champB;
        this.champC = champC;
    }

    // champ A
    public double getA() 
    { 
        return champA; 
    }
    public void setA(double champA) 
    { 
        this.champA = champA; 
    }
    // champ B
    public double getB() 
    { 
        return champB; 
    }
    public void setB(double champB) 
    { 
        this.champB = champB; 
    }
    //Chmap C
    public double getC() 
    { 
        return champC; 
    }
    public void setC(double champC) 
    { 
        this.champC = champC; 
    }
    // Delta
    public double getDelta() 
    { 
        return delta; 
    }


    //Fonctions
    //calcul
    public double[] calculSeconddegre() 
    {
        // Calcul du discriminant
        delta = champB * champB - (4 * champA * champC);

        if (delta > 0) 
        {
            double r1 = (-champB + Math.sqrt(delta)) / (2 * champA);
            double r2 = (-champB - Math.sqrt(delta)) / (2 * champA);
            return new double[] { r1, r2 };
        }
        else if (delta == 0) 
        {
            double r = -champB / (2 * champA);
            return new double[] { r };
        }
        else 
        {
            return null; 
        }
    }
}
