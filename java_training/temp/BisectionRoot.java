package temp;

public class BisectionRoot {
    private static double f(double x) {return x*x-1;}
    public static void main(String[] args){
        double lB = 0;
        double uB = 5;
        double sol = (uB + lB)/2;
        double error = 0.1;
		
        while (f(uB)>0 && f(lB)<0 && Math.abs(f(sol)) > error) {
            if (f(sol) < 0) {
                lB = sol;
            } else {
                uB = sol;
            }
            sol = (lB + uB)/2;
        }
        System.out.println(sol);
    }
}
