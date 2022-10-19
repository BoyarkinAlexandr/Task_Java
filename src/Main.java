public class Main {
    public static void main(String[] args) {
        int[] a={5};
        System.out.println(delete(5,10));
        System.out.println(squareTriangle(7,8));
        System.out.println(farm(2,5,4));
        System.out.println(checkresult(5,6,7));
        System.out.println(checkresult1(4,2,5));
        System.out.println(returnASCII('A'));
        System.out.println(returnSum(6));
        System.out.println(maxLengthTriangle(10,11));
        System.out.println(sumcube(a));
        System.out.println(checkDelC(3,6,9));

    }


    public static int delete(int i,int j)
    {
        return i % j;
    }

    public static double squareTriangle(int i, int j)
    {
        return 1/2*i*j;
    }

    public static int farm(int chick,int cow,int pigs)
    {
        return chick*2+cow*4+pigs*4;
    }

    public static boolean checkresult(double prob,double prize, double pay) {
        if (prob * prize > pay)
            return true;
        else
            return false;
    }

    public static String checkresult1(int a,int b,int c)
    {
        if (c == a + b)
        {
            return "added";
        }
        else if (c == a - b) {
            return "subtracted";
        } else if (c == a / b) {
            return "divided";
        } else if (c == a * b) {
            return "multiplied";
        } else
            return "none";
    }

    public static int returnASCII(char i)
    {
        int charInt = i;
        return charInt;
    }

    public static int returnSum(int lastnum) {
        int sum = 0;
        for (int i = 1; i <= lastnum; i++) {
            sum += i;
        }
        return sum;
    }

    public static int maxLengthTriangle(int i, int j)
    {
        return i + j - 1;
    }

    public static int sumcube(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i], 3);
        }
        return sum;
    }

    public static boolean checkDelC(int a, int b, int c) {
        for (int i = 0; i < b; i++) {
            a *= 2;
        }
        if (a % c == 0)
            return true;
        else
            return false;
    }


}