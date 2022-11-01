public class Task3 {
    public static void main(String[] args) {

        System.out.println(solutions(1,0,-1));
        System.out.println(solutions(1,0,0));
        System.out.println(solutions(1,0,1));

        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(12));

    }

    private static int solutions(int a, int b, int c) {

        int d = b*b - 4 * a * c;

        if (d == 0) {
            return 1;
        } else if (d > 0) {
            return 2;
        } else {
            return 0;
        }
    }

    public static boolean checkPerfect(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        if (sum == n)
            return true;
        return false;
    }




}
