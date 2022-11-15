import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Math.pow;
import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Первое");
        System.out.println(solutions(1,0,-1));
        System.out.println(solutions(1,0,0));
        System.out.println(solutions(1,0,1));


        System.out.println("Второе");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));


        System.out.println("Третье");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(12));


        System.out.println("Четвёртое");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));


        System.out.println("Пятое");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));


        System.out.println("Шестое");
        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));
        System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1}));
        System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}));


        System.out.println("Седьмое");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));


        System.out.println("Восьмое");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));


        System.out.println("Девятое");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));

        System.out.println("Десятое");
        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));
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

    private static int findZip(String str)
    {
        Pattern pattern = Pattern.compile("zip");   //создаём объект класса Pattern. Принимаем аргумент
        Matcher matcher = pattern.matcher(str); //ищет соответствие шаблона
        // количество найденных последовательностей 'zip'
        int foundOccurrence = 0;
        while (matcher.find() && foundOccurrence < 2) {   //ищем в строке последовательность
            // если это второе вхождение, то возвращается его позиция
            if (foundOccurrence == 1) {
                return matcher.start();  //используем для получения стартового индекс
            }
            foundOccurrence += 1;
        }
        return -1;
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

    private static String flipEndChars(String s) {
        if (s.length() < 2) {
            return "incompatible";
        } else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return "Two's a pair";
        } else {
            return s.charAt(s.length() - 1) +
                    s.substring(1, s.length() - 1) +
                    s.charAt(0);
        }
    }

    private static boolean isValidHexCode(String hex) {
        {
            if (hex.charAt(0) != '#')
                return false;

            if (!(hex.length() == 7))
                return false;

            for (int i = 1; i < hex.length(); i++)
                if (!((hex.charAt(i) >= '0' && hex.charAt(i) <= '9')
                        || (hex.charAt(i) >= 'a' && hex.charAt(i) <= 'f')
                        || (hex.charAt(i) >= 'A' || hex.charAt(i) <= 'F')))
                    return false;
            return true;
        }
    }



    private static boolean same(int[] a, int[] b) {
        // локальный класс для возвращаения уникальных значений массива



        // проверяем, что у множеств элементов этих массивов одинаковые размеры
        return  build(a).size() == build(b).size();
    }


       private static Set<Integer> build(int[] array) {
            Set<Integer> s = new HashSet<>();  //храним только уникальные зачения
            for (int c : array) {
                s.add(c);
            }
            return s;
        }

    static boolean isKaprekar(int n)
    {

//        // Возвращает true, если n — число Капрекара, иначе false
//        if (n == 1)
//            return true;
//
//        // Подсчитать количество цифр в квадрате
//        int sq_n = n * n;
//        int count_digits = 0;
//        while (sq_n != 0)
//        {
//            count_digits++;
//            sq_n /= 10; //Оператор присваивания «Деление»
//        }
//
//        sq_n = n*n; // Пересчитать квадрат так, как он был изменен
//
//        // Разбиваем квадрат в разных точках и смотрим, есть ли сумма
//        // любой пары разделенных чисел равно n.
//        for (int r_digits=1; r_digits<count_digits; r_digits++)
//        {
//            int eq_parts = (int) Math.pow(10, r_digits);
//
//            // Чтобы избежать таких чисел, как 10, 100, 1000 (это не числа Капрекара).
//            if (eq_parts == n)
//                continue;
//
//            // Найдите сумму текущих частей и сравните с n
//            int sum = sq_n/eq_parts + sq_n % eq_parts;
//            if (sum == n)
//                return true;
//        }
//
//        // сравнить с исходным номером
//        return false;

        boolean isCapricorn = false;

        int square = n * n;
        int temp = square;
        int contDigits = 0;

        while (temp > 0)
        {
            contDigits++;
            temp /= 10;
        }


            int divisor = (int) Math.pow(10, (contDigits/2+1));
            int quotient = square / divisor; //делим на
            int remainder = square % divisor;
            if (quotient + remainder == n)
            {
                isCapricorn = true;
            }

        if (isCapricorn)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    private static String longestZero(String str) {
        // максимальная найденная последовательность нулей
        int maxLength = 0;
        // текущая последовательность нулей
        int currentLength = 0;
        //Перебираем символы в строке
        for (char ch: str.toCharArray()) {
            // если сейчас не 0, то сбрасыываем найденное количество нулей
            if (ch != '0') {
                currentLength = 0;
            } else {
                currentLength+=1;
            }
            // если текущее количество нулей больше максимального - перезаписываем
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        // возвращаем 0 повторенное найденное количество раз
        return "0".repeat(maxLength);
    }

    private static int nextPrime(int d) {
        class PrimeChecker {
            boolean isPrime(int number) {
                // проверяем что на промежутке от 2 до корня числа нет его множителей.
                // если это так - то оно простое
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
        PrimeChecker checker = new PrimeChecker(); //выделяем память
        // ищем среди заданного числа и следующих за ним простое.
        while (true) {
            if (checker.isPrime(d)) {
                return d;
            }
            d++;
        }
    }


    public static boolean rightTriangle(int a, int b, int c) {
        int[] numbers = new int[3];

        numbers[0] = a;
        numbers[1] = b;
        numbers[2] = c;

        // с начала нужно отсортировать эти числа (x, y, z)
        // в порядке возрастания
        Arrays.sort(numbers);


        // теперь проверяем являются ли эти числа тройкой Пифагора
        // по формуле: x^2 + y^2 = z^2
        // и выводим ответ
        if ((pow(numbers[0], 2) + pow(numbers[1], 2)) == pow(numbers[2], 2)) {
            return true;
        } else {
            return false;
        }

    }

}




