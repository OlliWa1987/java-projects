public class Recursion {

    public static void main(String[] args) {
        countdown(10);
        countup(10);
        int[] a = {1, 0, 2, 1}; //34
        System.out.println(hornerRecursive(a, 3));
        System.out.println(gNaive(10));
        System.out.println(gTail(10));

    }

    public static void countdown(int n){

        if (n == 0){
            System.out.println(n);
        }else {
            System.out.println(n);
            countdown(n - 1);
        }

    }

    public static void countup(int n){

        if (n == 0){
            System.out.println(n);
        }else {
            countup(n - 1);
            System.out.println(n);
        }

    }

    public static int hornerHelper(int counter, int[] digits, int base) {

        if (counter == 0) {

            return digits[0];

        } else {

            int subtotal = hornerHelper(counter - 1, digits, base) * base + digits[counter];
            return subtotal;

        }

    }

    public static int hornerRecursive(int[] digits, int base) {

        int counter = digits.length -1;
        int total = hornerHelper(counter, digits, base);
        return total;

    }

    public static long gNaive(int i){

        if(i == 1){
            return 1;

        } else if (i == 0){
            return 0;

        } else {
            return  (2 * gNaive(i - 1 ) + 5 * gNaive(i - 2));
        }

    }

    public static long gTailHelper(int i, long giMinusOne, long giMinusTwo){

        long e = 2 * giMinusOne + 5 * giMinusTwo;

        if (i == 0){
            return e;
        } else {
            return gTailHelper(i - 1, e, giMinusOne);
        }

    }

    public static long gTail(int i){

        if(i == 1) {
            return 1;

        } else if (i == 0){
            return 0;

        } else {
            return gTailHelper(i,i,i);
        }

    }

}
