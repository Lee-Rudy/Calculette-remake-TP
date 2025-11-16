package calcul;

public class Calcul 
{

    private double champA;
    private double champB;
    private double champC;

    public Calcul(double champA, double champB, double champC) 
    {
        this.champA = champA;
        this.champB = champB;
        this.champC = champC;
    }

    public double getA() 
    {
        return champA;
    }
    public void setA(double champA) 
    {
        this.champA = champA;
    }
    public double getB() {
        return champB;
    }
    public void setB(double champB) 
    {
        this.champB = champB;
    }
    public double getC() 
    {
        return champC;
    }
    public void setC(double champC) 
    {
        this.champC = champC;
    }
    
    public double[] calculSeconddegre()
    {
        double delta = champB*champB-(4*champA*champC);

        if(delta>0)
        {
            double solution1 = (-champB+Math.sqrt(delta))/(2.0*champA);
            double solution2 = (-champB-Math.sqrt(delta))/(2.0*champA);
            System.out.println("deux solutions reels : x1 = " + solution1 + ", x2 = " + solution2);
            return new double[] {delta, solution1, solution2};
        } else if(delta==0)
        {
            double solution = -champB/2.0*champA;
            System.out.println("une seule solution reelle : x = " + solution);
            return new double[] {delta,solution};
        } else
        {
            System.out.println("aucune solution (delta < 0)");
             return null;
        }
    }
}