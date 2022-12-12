import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("----1----");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println("----2----");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("----3----");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("----4----");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
        System.out.println("---5---");
        getHashTags("How the Avocado Became the Fruit of the Global Trade");
        getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year");
        getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit");
        System.out.println("---6---");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("---7---");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println("---8---");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println("---9---");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 7 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println(formula("16 * 10 = 160 = 40 + 120"));
        System.out.println("---10---");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
    }

    //1 задание
    static int bell(int num) {
        if (num <= 1)
            return 1;
        int[] mas1 = new int[num];
        int[] mas2 = new int[num];
        mas1[0] = 1;

        for (int i = 0; i < num - 1; i++) {
            mas2[0] = mas1[i];
            for (int j = 1; j <= i + 1; j++)
                mas2[j] = mas1[j - 1] + mas2[j - 1];
            for (int j = 0; j <= i + 1; j++)
                mas1[j] = mas2[j];
        }

        return mas2[num - 1];
    }

    //2 задание
    private static String translateWord(String s) {
        final String vowels = "aeiou";
        // 1) проверяем, что в этом слове есть буквы и только они
        Pattern pattern = Pattern.compile("\\w+");
        if (!pattern.matcher(s).matches()) {
            return "";
        }
        // если первый символ - гласное, просто добавляем yay в конец
        if (vowels.indexOf(Character.toLowerCase(s.charAt(0))) != -1) {
            return s + "yay";
        }
        // ищем первую гласную в слове начинающемся с согласной.
        Pattern pattern1 = Pattern.compile("[aeiou]");
        Matcher matcher = pattern1.matcher(s);
        int indexOfVowel = -1;
        if (matcher.find()) {
            indexOfVowel = matcher.start();
        }
        // если в слове нет гласных - просто добавляем ay в конец
        if (indexOfVowel == -1) {
            return s + "ay";
        }
        // переводим слово
        String translated = s.substring(indexOfVowel) + s.substring(0, indexOfVowel) + "ay";
        // если у изначального слова первая буква была заглавной, то капитализуем новое слово
        if (Character.isUpperCase(s.charAt(0))) {
            return translated.substring(0, 1).toUpperCase() + translated.substring(1).toLowerCase();
        } else {
            return translated;
        }
    }

    //перевести на свинячий латинский предложения по правилам из translateWord
    private static String translateSentence(String s) {
        // регуляркой находим в предложении слова
        Pattern pattern = Pattern.compile("\\w+"); //находим любое слово
        Matcher matcher = pattern.matcher(s);
        // и переводи через наш переводчик
        return matcher.replaceAll(matchResult -> translateWord(matchResult.group()));
    }

    //3 задание
    private static boolean validColor(String s) {
        Integer r = null;
        Integer g = null;
        Integer b = null;
        Double a = null;
        // регулярка, которая принимает целочисленные числа и дроби
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(s);
        // по порядку находим значения r,g,b,a в строке
        while (matcher.find()) {
            try {
                if (r == null) {
                    r = Integer.parseInt(matcher.group());
                } else if (g == null) {
                    g = Integer.parseInt(matcher.group());
                } else if (b == null) {
                    b = Integer.parseInt(matcher.group());
                } else if (a == null) {
                    a = Double.parseDouble(matcher.group());
                }
            } catch (Exception e) {
                return false;
            }
        }
        // проверяем, что все значения соответствуют формату
        if (r == null || r > 255 || r < 0) {
            return false;
        }
        if (g == null || g > 255 || g < 0) {
            return false;
        }
        if (b == null || b > 255 || b < 0) {
            return false;
        }
        if (s.contains("rgba")) {
            return a != null && a <= 1 && a >= 0;
        } else return a == null && s.contains("rgb");
    }

    //4 задание
    public static String stripUrlParams(String url, String... paramsToStrip) {
        String str = "";
        if (!url.contains("?"))
            return url;
        else {
            str = url.substring(url.indexOf("?") + 1);  //отделяем от адреса параметры
            url = url.substring(0, url.indexOf("?") + 1); //оставляем адресс
        }
        char[] params = str.toCharArray();

        StringBuilder print = new StringBuilder();
        for (char param : params) {
            if (Character.isLetter(param))
                if (!(print.toString().contains(String.valueOf(param)))) {
                    if (paramsToStrip.length > 0) {
                        for (String arg : paramsToStrip) {
                            if (!(arg.contains(String.valueOf(param))))
                                print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                        }
                    } else
                        print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                }
        }
        return url + print.substring(0, print.length() - 1);
    }

    //5 задание
    static void getHashTags(String str) {
        System.out.println(str);
        String[] strs = str.split(" ");
        Arrays.sort(strs, (a, b) -> Integer.compare(b.length(), a.length()));
        int l = Math.min(strs.length, 3);
        String[] res = new String[l];
        for (int i = 0; i < l; i++) {
            if (strs[i].endsWith(","))
                res[i] = "#" + strs[i].toLowerCase().substring(0, strs[i].length() - 1).trim();
            else
                res[i] = "#" + strs[i].toLowerCase().trim();
        }
        for (int i = 0; i < 3; i++)
            System.out.println(res[i]);
    }

    //6 задание
    public static int ulam(int n) {
        int[] mas = new int[n];
        mas[0] = 1;
        mas[1] = 2;
        int len = 2, next = 3;
        while (next < Integer.MAX_VALUE && len < n) {
            int count = 0;
            for (int i = 0; i < len; i++) {
                for (int j = len - 1; j > i; j--) {
                    if (mas[i] + mas[j] == next && mas[i] != mas[j])
                        count++;
                    else if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                mas[len] = next;
                len++;
            }
            next++;
        }
        return mas[n - 1];
    }

    //7 задание
    public static String longestNonrepeatingSubstring(String str) {
        String podstr = "";
        char[] mass = str.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for (char c : mass) {
            if (!strBuilder.toString().contains(String.valueOf(c)))
                strBuilder.append(c);
            else {
                if (strBuilder.length() > podstr.length())
                    podstr = strBuilder.toString();
                strBuilder = new StringBuilder("" + c);
            }
        }
        str = strBuilder.toString();
        if (str.length() > podstr.length())
            podstr = str;
        return podstr;
    }

    //8 задание
    public static String convertToRoman (int num){
        StringBuilder roman = new StringBuilder();
        if (num < 1 || num > 3999)
            return "Введите число поменьше. ";
        while (num >= 1000) {
            roman.append("M");
            num -= 1000;        }
        while (num >= 900) {
            roman.append("CM");
            num -= 900;
        }
        while (num >= 500) {
            roman.append("D");
            num -= 500;
        }
        while (num >= 400) {
            roman.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            roman.append("C");
            num -= 100;
        }
        while (num >= 90) {
            roman.append("XC");
            num -= 90;
        }
        while (num >= 50) {
            roman.append("L");
            num -= 50;
        }
        while (num >= 40) {
            roman.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            roman.append("X");
            num -= 10;
        }
        while (num >= 9) {
            roman.append("IX");
            num -= 9;
        }
        while (num >= 5) {
            roman.append("V");
            num -= 5;
        }
        while (num >= 4) {
            roman.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            roman.append("I");
            num -= 1;
        }
        return roman.toString();
    }

    //9 задание
    public static boolean formula(String formula){
        String[] mass = formula.split(" ");
        int ans = 0;
        int expectedResult = 0;
        if (!Character.isDigit(mass[0].charAt(0))) return false;
        else ans = Integer.parseInt(mass[0]);
        int i = 1;
        while (!mass[i].equals("=")) {
            if (mass[i].equals("+")){
                ans += Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("-")){
                ans -= Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("*")){
                ans *= Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("/")){
                ans /= Integer.parseInt(mass[i + 1]);
            }
            i += 2;
        }
        i = formula.indexOf('=');
        String check = formula.substring(i + 1, formula.length());
        if (check.contains("=")) return false;
        else expectedResult = Integer.parseInt(mass[mass.length - 1]);
        return ans == expectedResult;
    }


    //10 задание
    private static boolean palindromeDescendant(int number) {
        if (number < 10) {
            return true;
        }

        while (number > 10) {
            // проверяем является ли текущая строка палиндромом
            String numberStr = "" + number;
            String reversedStr = new StringBuilder(numberStr).reverse().toString();
            if (numberStr.equals(reversedStr)) {
                return true;
            }
            // если это не палиндром, и число нечетное, то потомков создать нельзя
            if (numberStr.length() % 2 != 0) {
                return false;
            }
            // создаем потомка
            StringBuilder newStrBuilder = new StringBuilder();
            for (int i = 0; i <= numberStr.length() - 2; i += 2) {
                newStrBuilder.append(Integer.parseInt(numberStr.substring(i, i + 1)) + Integer.parseInt(numberStr.substring(i + 1, i + 2)));
            }
            number = Integer.parseInt(newStrBuilder.toString());
        }
        return false;
    }


}
