import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] a={10, 4, 1, 4, -10, -50, 32, 21};
        int[] b={1, 2, 3, 4};
        int[] c={1,2,3};
        int[] d={1, 3};
        System.out.println(RepeatStr("mice", 5));
        System.out.println(RepeatStr("hello", 3));

        System.out.println(getDifference(a));

        System.out.println(average(b));
        System.out.println(average(d));

        System.out.println(Arrays.toString(Returnsum(c)));

        System.out.println(getDec("43.20"));

        System.out.println(Febonachi(3));

        System.out.println(isValid("59001"));

        System.out.println(isStrangePair("ratio", "orator"));

        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isPrefix("retrospect", "sub-"));


        System.out.println(BOXSP(0));
        System.out.println(BOXSP(1));
        System.out.println(BOXSP(2));


    }

    public static String RepeatStr(String str, int a)
    {
        String Result = "";
        for (int i = 0; i < str.length();i++)
            for (int j = 0; j < a;j++)
                Result = Result + str.charAt(i);
        return Result;
    }

    public static int getDifference(int[] a)
    {
        int min = a[0];
        int max = a[0];
        for (int i = 0;i < a.length;i++)
        {
            if (min>a[i]){
                min = a[i];
            }
        }

        for (int i = 0;i < a.length;i++)
        {
            if (max<a[i]){
                max = a[i];
            }
        }

        int k = max-min;
        return k;

    }

    public static boolean average(int[] a) {
        double average = 0;
        if (a.length > 0)
        {
            double sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
            }
            average = sum / a.length;
        }

        if ((average%1) != 0)
        {
            return false;
        }
        return true;

    }

    public static int[] Returnsum(int[] array)
    {
        int sum = 0;
        int array1[] = new int [array.length];
        for (int i = 0; i < array.length; i++)
        {
            array1[i] = sum += array[i];
        }
        return array1;
    }

    public static int getDec(String str)
    {
        int DotIndex = str.lastIndexOf(".");
        if (DotIndex == -1) {
            return 0;
        } else {
            return str.length() - DotIndex - 1;
        }
    }

    public static long Febonachi(int a)
    {
        if (a <= 1)
            return a;

        return Febonachi(a-1) + Febonachi(a-2);

    }

    public static boolean isValid(String zipCode) {
        // Принимает только цифры и длина равна 5
        if (zipCode.matches("[0-9]+") && zipCode.length() == 5 && !zipCode.isBlank())
            return true;
        else
            return false;
    }

    private static boolean isStrangePair(String str1, String str2) {
        if (str1.charAt(0) == str2.charAt(str2.length() - 1) && str1.charAt(str1.length() - 1) == str2.charAt(0))
            return true;
        else
            return false;
    }

    private static boolean isPrefix(String wrd, String pref) {

        return wrd.startsWith(pref.substring(0, pref.length() - 2));
    }


    private static boolean Suffix(String wrd, String suf) {

        return wrd.endsWith(suf.substring(1, suf.length() - 1));
    }

    private static int BOXSP(int i) {
        if (i == 0) {
            return 0;
        } else if (i % 2 == 0) {
            return BOXSP(i - 1) - 1;
        } else {
            return BOXSP(i - 1) + 3;
        }
    }

}






