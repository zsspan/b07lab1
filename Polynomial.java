public class Polynomial {
    double[] coeff;

    public Polynomial(){
        coeff = new double[]{0};
    }

    public Polynomial(double[] arr){
        coeff = arr;
    }

    public Polynomial add(Polynomial p){
        int length = Math.max(coeff.length, p.coeff.length);
        double[] arr = new double[length];
        for (int i = 0; i<length; i++)
        {
            double total = 0;
            if (i<coeff.length)
                total += coeff[i];
            if (i<p.coeff.length)
                total += p.coeff[i];
            arr[i] = total;
        }
        Polynomial q = new Polynomial(arr);
        return q;
    }

    public double evaluate(double x)
    {
        double eval = 0;
        for (int i = 0; i<coeff.length; i++)
        {
            eval += coeff[i] * Math.pow(x,i);
        }
        return eval;
    }
    
    public boolean hasRoot(double x)
    {
        return evaluate(x) == 0;
    }
}
