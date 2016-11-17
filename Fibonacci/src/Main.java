import java.io.IOException;

class Main {

    private static int f1 = 1;
    private static int f = 1;
    private static int i = 2;

    private static void fibonacci() {

        System.out.println("F" + i + ": " + f);      // it's important to output values at first, then perform addition

        int f_prev = f;
        f = f + f1;
        f1 = f_prev;

        i++;

        if ( f < 10000 ) {
            fibonacci();        //run Fibonacci algorithm recursively if condition is true
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Fibonacci numbers:");

        System.out.println("F0: " + 0);     //output first two initial values of values
        System.out.println("F1: " + f1);

        fibonacci();  //Uncomment for recursive method

        //comment below code if using recursive method to avoid duplicated calculations
//        int f0 = 0;
//        int f1 = 1;
//        int f = 1;
//        int f_prev =  0;
//        int i = 2;
//
//        while (f < 10000) {
//
//            System.out.println("F" + i +  ": " + f); // it's important to output values at first, then perform addition
//
//            f_prev = f;
//            f = f + f1;
//            f0 = f1;
//            f1 = f_prev;
//
//            i++;
//        }



    }

}