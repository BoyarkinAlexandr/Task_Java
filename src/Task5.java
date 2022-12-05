import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Task5 {

    public static void main(String[] args) {
        System.out.println("----1----");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println("----2----");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("----3----");
        System.out.println(canComplate("butl", "beautiful"));
        System.out.println(canComplate("butlz", "beautiful"));
        System.out.println(canComplate("tulb", "beautiful"));
        System.out.println(canComplate("bbutl", "beautiful"));
        System.out.println("----4----");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("----5----");
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));
        System.out.println("----6----");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("----7----");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println("----8----");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println("----9----");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("----10----");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
    }

    private static int[] encrypt(String s) {
        char last = 0;
        int[] cryp = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            cryp[i] = chars[i] - last;
            last = chars[i];
        }
        return cryp;
    }

    /**
     * 1 задание:
     *
     * @return расшифровать последовательность, полученную с помощью encrypt
     */
    private static String decrypt(int[] s) {
        char last = 0;
        char[] chars = new char[s.length];
        for (int i = 0; i < s.length; i++) {
            chars[i] = (char) (last + s[i]);
            last = chars[i];
        }
        return new String(chars);
    }


    public static boolean canMove(String figure, String from, String to) {
        int x1 = from.charAt(0) - 'A'; //координаты фигуры
        int y1 = from.charAt(1) - '0';
        int x2 = to.charAt(0) - 'A';  //координаты цели
        int y2 = to.charAt(1) - '0';
        //Если координаты зашли за пределы поля или ход на одном месте
        if ((x2 == 0 && x2 > 8) || (y2 == 0 && y2 > 8)
                || (x1 == x2 && y1 == y2))
            return false;
        if (figure == "Rook")  //координаты для фигуры ладья
            return x1 == x2 || y1 == y2;  //по диагонали
        else if (figure == "Knight") {  //координаты для фигуры коня
            int rx = Math.abs(x1 - x2); //находим расстояние
            int ry = Math.abs(y1 - y2);
            return (rx == 1 && ry == 2) || (rx == 2 && ry == 1); //конь ходит 2по х и 1 по y, и наоборот
        } else if (figure == "Bishop") //координаты для фигуры слона
            return Math.abs(x1 - x2) == Math.abs(y1 - y2);  //слон ходит только по прямым
        else if (figure == "Queen")   //координаты для фигуры королевы
            return x2 == x1 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2); //по прямым и по диагонали
        else if (figure == "King")   //координаты для фигуры короля
            return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;    //король ходит по всем направлениям на 1 коорд
        else if (figure == "Pawn")   //координаты для фигуры пешки
            return Math.abs(y2 - y1) == 1; //только вперёд на 1 клетку

        return false;
    }

    static boolean canComplate(String a, String b) {
        int index = 0; //хранится количество повторений
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(index) == b.charAt(i))
                index++;
        }
        return index == a.length();
    }

    public static int sumDigProd(int... numbers) {
        int sum = 0;
        //Подсчитываем сумму чисел
        for (int i = 0; i < numbers.length; i++)
            sum += numbers[i];
        if (sum == 0) return 0;
        int res = 1;
        do {
            res = 1;
            //Находим произведение цифр
            while (sum > 0) {
                res *= sum % 10; //делим на разряды
                sum /= 10;
            }
            sum = res; //из за того, sum = 0, перезаписывае 1
        } while (res >= 10);
        return res;
    }



    //5 задание:

    private static String[] sameVowelGroup(String[] str) {
        // получаем множество гласных из первого слова, удалив из слова все согласные
        Set<Character> vowels = new HashSet<>();
        String check_first = str[0].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
        for (char c : check_first.toCharArray()) {
            vowels.add(c);
        }

        // задаем массив результата
        ArrayList<String> result = new ArrayList<>();
        result.add(str[0]);

        // аналогично первому слову получаем множества гласных других слов и добавляем по возможности в массив результата
        for (int i = 1; i < str.length; i++) {
            Set<Character> currentVowels = new HashSet<>();
            String it = str[i].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
            for (char c : it.toCharArray()) {
                currentVowels.add(c);
            }
            if (currentVowels.equals(vowels)) {
                result.add(str[i]);
            }
        }
        return result.toArray(new String[]{});
    }


    //6 задание
    public static boolean validateCard(long num) {
        String snum = Long.toString(num);
        if (snum.length() < 14 && snum.length() > 19)
            return false;
        long check_digit = num % 10, sum = 0; //удаляем последнюю цифру, для проверки
        for (int i = snum.length() - 2; i >= 0; i--) {  //начинает цикл с конца
            //Т.к. индексы начинаются с 0, а не 1
            //то * 2 четные позиции
            if (i % 2 == 0) {
                long n = (snum.charAt(i) - '0') % 10 * 2;
                if (n >= 10)
                    sum += n % 10 + (n / 10) % 10;
                else sum += n;
            } else sum += snum.charAt(i) - '0';  //если чётная, то прибавляем к сумме
        }
        return 10 - (sum % 10) == check_digit; //сравниваем с цифрой дял проверки
    }

    // 7 задание: Первые двадцать чисел на английском языке
    static final String[] First_20 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    //Десятки на английском языке (десять, двадцать, тридцать и т.д.)
    static final String[] Tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    //Функция принимает число и возвращает
    //это число в виде строки на английском языке
    //от 0 до 999
    private static String numToEng(int num) {
        if (num == 0) return "zero";
        return parser(num);
    }

    //Дополнительная функция для numToEng
    private static String parser(int num) {
        if (num == 0) return "";
        else if (num < 20)
            return First_20[num] + " ";
        else if (num < 100)
            return Tens[num / 10] + " " + First_20[num % 10];
        else
            return First_20[num / 100] + " hundred " + parser(num % 100); //высчитываем сотни + десятки и сотни

    }


    //8 задание
    private static String getSha256Hash(String str) {
        try {
            // шифруем в байты строку через MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            // переводим байты в хеш
            StringBuilder hexString = new StringBuilder();
            for (byte bt : hash) {
                String hex = Integer.toHexString(0xff & bt);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) { //проверка на ошибку
            return null;
        }
    }



    //9 задание
    public static String correctTitle(String string){
        String title = "";
        String[] words = string.split(" ");
        for (String word: words){
            word = word.toLowerCase();
            if(!(word.contains("and") ||word.contains("the") ||word.contains("of"))){
                title += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
            }
            else{
                title += word + " ";
            }
        }
        return title;
    }


    //10 задание
    public static String hexLattice(int n){
        String ans = "";
        int temp = 1;  //сохраняет кол-во точек
        int l = 1;     //считает количество кругов
        while (temp < n ){    //считается кол-во точек и циклов
            temp = temp + (l * 6);
            l++;
        }
        if (temp != n)return "Invalid";
        for (int i = l ; i <= 2 * l  - 1; i++){
            ans += repeat(" ", 2 * l - i);  //Подсчитываем количество кругов в верхней строке и середине
            for (int j = 0; j < i; j++){
                ans += " o";
            }
            ans += "\n";
        }
        for (int i = 2 * l  - 2 ; i >= l ; i--){ //аналогично подсчитываем нижнюю строку
            ans += repeat(" ", 2 * l - i);
            for (int j = 0; j < i; j++){
                ans += " o";
            }
            ans += "\n";
        }
        return ans;
    }


    //вспомогательный метод, который потворяет символ в строке n раз
    public static String repeat(String string, int n) {
        String ans = "";
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < n; j++) { //проходимся по слову и после по каждой букве записывая n раз
                ans += string.charAt(i); //в ans и выводим ans
            }
        }
        return ans;
    }
}
