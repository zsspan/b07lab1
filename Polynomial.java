import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    double[] coeff;
    int[] exp;

    public Polynomial() {
        coeff = new double[] { 0 };
        exp = new int[] { 0 };
    }

    public Polynomial(double[] arr1, int[] arr2) {
        if (arr1.length != arr2.length){
            coeff = new double[] { 0 };
            exp = new int[] { 0 };
        }
        coeff = arr1;
        exp = arr2;
    }

    public Polynomial(File file) {
        try {
            Scanner sc = new Scanner(file);
            String poly = sc.nextLine();
            String[] str_arr = poly.split("(?=[-+])");
            
            //yes, this long line is needed for the length.
            int actual_length = Integer.parseInt(str_arr[str_arr.length-1].split("x")[1]) + 1;
            coeff = new double[actual_length];
            System.out.println("LENGTH: " + coeff.length);
            
            exp = new int[actual_length];
            for (int i = 0; i < actual_length; i++) {
                exp[i] = i;
            }

            String first_term = str_arr[0];
            coeff[0] = Integer.parseInt(first_term);

            for (String str : str_arr){
                if (str == first_term)
                    continue;
                System.out.println(str);
                String[] temp = str.split("x");
                int temp_coeff = Integer.parseInt(temp[0]);
                int temp_exp = Integer.parseInt(temp[1]);
                coeff[temp_exp] += temp_coeff;
                System.out.println("TEMP COEFF: " + temp_coeff + " TEMP EXP: " + temp_exp);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            coeff = new double[] { 0 };
            exp = new int[] { 0 };
        }
    }

    public void saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            String text = "";
            for (int i = 0; i < coeff.length; i++) {
                if (coeff[i] == 0) 
                    continue;
                if (i != 0) {
                    if (coeff[i] > 0)
                        text += "+";
                    else 
                        text += "-";
                }
                text += Math.abs(coeff[i]);
                if (exp[i] != 0) 
                    text += "x" + exp[i];
            }
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public void display() {
        System.out.println("POLYNOMIAL:");
        for (int i = 0; i < exp.length; i++) {
            System.out.print("(" + coeff[i] + ")^" + exp[i]);
            if (i != exp.length - 1)
                System.out.print(" + ");
        }
        System.out.println();
    }

    public Polynomial add(Polynomial p) {
        int length = Math.max(coeff.length, p.coeff.length);
        double[] c_arr = new double[length];
        for (int i = 0; i < length; i++) {
            double total = 0;
            if (i < coeff.length)
                total += coeff[i];
            if (i < p.coeff.length)
                total += p.coeff[i];
            c_arr[i] = total;
        }
        // return larger exponent array
        if (exp.length > p.exp.length)
            return new Polynomial(c_arr, exp);
        // System.out.println("p.exp is larger");
        return new Polynomial(c_arr, p.exp);
    }

    public Polynomial multilply(Polynomial p) {
        int new_exp_length = exp.length + p.exp.length - 1;
        System.out.println("LENGTH: " + new_exp_length);
        int[] new_exp = new int[new_exp_length];
        // set to default values
        for (int i = 0; i < new_exp_length; i++) {
            new_exp[i] = i;
        }

        double[] new_coeff = new double[new_exp_length];
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < p.exp.length; j++) {
                int exp_val = i + j;
                double curr_product = coeff[i] * p.coeff[j];
                new_coeff[exp_val] += curr_product;
                // System.out.println("COEFFS: " + coeff[i] + ", " + p.coeff[j] + " PRODUCT: " +
                // curr_product;
            }
        }
        return new Polynomial(new_coeff, new_exp);
    }

    public double evaluate(double x) {
        double eval = 0;
        for (int i = 0; i < coeff.length; i++) {
            eval += coeff[i] * Math.pow(x, i);
        }
        return eval;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}
