import java.io.IOException;

class Main {

    private static final long MEGABYTE = 1024L * 1024L;

    private static int f0 = 0;
    private static int f1 = 1;
    private static int f = 1;
    private static int i = 3;

    private static void fibonacci() {

        int f_prev = f;
        f = f + f1;
        f1 = f_prev;

        System.out.println("F" + i + ": " + f);
        i++;

        if ( f < 10000 ) {
            fibonacci();
        }
    }

    public static void main(String[] args) throws IOException {

//        int f0 = 0;
//        int f1 = 1;
//        int f = 1;
//        int f_prev =  0;
//        int i = 3;
//
//        while (f < 10000) {
//
//            f_prev = f;
//            f = f + f1;
//            f0 = f1;
//            f1 = f_prev;
//
//            System.out.println("F" + i +  ": " + f);
//            i++;
//        }

        fibonacci();

    }

}