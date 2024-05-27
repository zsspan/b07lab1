import java.io.File;

public class Driver {
    public static void main(String[] args) 
    {
        Polynomial p = new Polynomial();
        //System.out.println(p.evaluate(3));

        double[] c1 = { 6, 0, 0, 5 };
        int[] e1 = { 0, 1, 2, 3 };
        Polynomial p1 = new Polynomial(c1, e1);

        double[] c2 = { 0, -2, 0, 0, -9 };
        int[] e2 = { 0, 1, 2, 3, 4 };
        Polynomial p2 = new Polynomial(c2, e2);
        //Polynomial s = p1.add(p2);
        //s.display();

        Polynomial multiply = p1.multilply(p2);
        //multiply.display();

        Polynomial f = new Polynomial(new File("test.txt"));
        f.display();
        f.saveToFile("test1");
    }
}