
import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public static void main(String[] args) {
        System.out.println("Первое");
        System.out.println(Bessy(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println("Второе");
        System.out.println(Arrays.toString(split("()()()")));
        System.out.println(Arrays.toString(split("((()))")));
        System.out.println(Arrays.toString(split("((()))(())()()(()())")));
        System.out.println(Arrays.toString(split("((())())(()(()()))")));
        System.out.println("Третье");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println("Четвёртое");
        System.out.println(overTime(new double[] {9, 17, 30, 1.5}));
        System.out.println(overTime(new double[] {16, 18, 30, 1.8}));
        System.out.println(overTime(new double[] {13.25, 15, 30, 1.5}));
        System.out.println("Пятое");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println("Шестое");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println("Седьмое");
        System.out.println(toStartShorthand("abbccc"));
        System.out.println(toStartShorthand("77777geff"));
        System.out.println(toStartShorthand("abc"));
        System.out.println("Восьмое");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println("Девятое");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println("Десятое");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
    }

    /**
     * 1 задание
     * @return отформатированную строку в список строк так, чтобы в каждой строке было не больше символов чем charsInRow
     * параметр wordsCount в условии задачи есть но ни на что не влияет.
     */

    public static String Bessy(int n, int k , String str)
    {
        String[] text = str.split(" "); //разделяет строку на массив до пробелов
        str = "";
        String finaltxt = ""; //записываем финальный результат
        for (int i = 0; i < n; i++)
        {
            if (str.length() + text[i].length() > k)
            {
                finaltxt = finaltxt.trim() + "\r\n" + text[i] + " "; //удаляем пробелы в начале и конце+переход на след строку + пробел
                str = text[i];
            } else
            {
                finaltxt += text[i] + " ";
                str += text[i];
            }
        }
        return finaltxt.trim();
    }

    /**
     * 2 задание:
     * @return из строки со скобками выделяются кластеры скобок и возвращаются в массиве
     * */
    public static String[] split(String string) { //task32
        String result = "";
        int open = 0;
        int close = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') open++;
            if (string.charAt(i) == ')') close++;
            result += string.charAt(i);
            if (open == close) result += " ";
        }
        return result.split(" ");
    }

    /**
     * 3 задание:
     * @return модифицированная строка переведенная из snake_case в camel_case.
     * */
    //`Проходимся по строке удаляя _ и меняем на врехний Регистр след. элемент
    public static String toCamelCase(String str) {
        while (str.contains("_")) {
            char temp = str.charAt(str.indexOf("_") + 1);
            str = str.replaceFirst("_" + temp, String.valueOf(Character.toUpperCase(temp)));
        }
        return str;
    }

    /**
     * 3 задание:
     * @return модифицированная строка переведенная из camel_case в snake_case.
     * */
    /*проходимся по строке и если есть буквы в верхнем регистре, то уменьшаем их и двигаем подставляя нижнее подчеркивание*/
    public static String toSnakeCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                str = str.replace((String.valueOf(str.charAt(i))),
                        "_" + Character.toLowerCase(str.charAt(i)));
            }
        }
        return str;
    }

    /**
     * 4 задание
     * @return оплата работнику в соответствии с отработанными часами
     * */
    public static String overTime(double[] stat) {
        double hours = 0;  //проработал чистыми
        double overtime = 0; //сверх урочные
        //Кол-во часов без сверхурочных
        if(stat[1] <= 17)
            hours = stat[1] - stat[0];
            //со сверхурочными
        else{
            hours = 17 - stat[0];
            overtime = stat[1] - 17;
        }
        //Расчет зарплаты за день
        return "$" + String.format("%.2f", stat[2] * (hours + overtime * stat[3]));
    }


    /**
     * 5 задание
     * @return ИМТ округленный до десятой
     * */
    private static String BMI(String weight, String height) {
        //Функция принимает вес в киллограмах или фунтах и высоту в метрах или дюймах.
        //Возвращает строку с индексом массы тела и категорией
            //val_si разделения на значение и единицу измерения
            String[] val_half = weight.split(" "); //разделили на значение и измерение
            double new_weight = Double.parseDouble(weight.split(" ")[0]);
            //конвертация из фунтов в киллограмы
            if (val_half[1].equals("pounds")) new_weight /= 2.205;
            val_half = height.split(" ");
            double new_height = Double.parseDouble(val_half[0]);
            //конвертация из дюймов в метры
            if (val_half[1].equals("inches")) new_height /= 39.37;

            //Рассчет индекса массы тела
            double BMI = new_weight / (new_height * new_height);

            //Получение результата
            String res = String.format("%.1f", BMI);
            if (BMI < 18.5) res += " Underweight";
            else if (BMI > 25) res += " Overweight";
            else res += " Normal weight";
            return res;
    }

    /**
     * 6 задание
     * @return мультипликативное постоянство (количество раз, которое нужно умножить цифры числа чтобы получить 1 цифру
     * */
    private static int bugger(int number) {
        // используем рекурсию. Если текущее число < 10, то можно перестать умножать
        if (number < 10) {
            return 0;
        }
        // получаем произведение цифр числа
        int multiplies = 1;
        for (char c : ("" + number).toCharArray()) {
            multiplies *= Integer.parseInt(String.valueOf(c));
        }
        // повторяем алгоритм для полученного произведения
        return 1 + bugger(multiplies);
    }

    /**
     * 7 задание
     * @return модифицированная строка, в которой повторения символов заменяются на фрагменты вида "a*n"
     * проверяем предыдущую букву и если она не совпадает с текущей i или = концу строки то прибавляем счетчик, и пока
     * не будет другая буква прибавляем счетчик, и потом "умножаем" слово на обороты счетчика*/
    /*проверяем предыдущую букву и если она не совпадает с текущей i или = концу строки то прибавляем счетчик, и пока
     * не будет другая буква прибавляем счетчик, и потом "умножаем" слово на обороты счетчика*/
    public static String toStartShorthand(String string) {
        String ans = "";
        char temp = string.charAt(0);
        int n = 1;
        for (int i = 1; i < string.length(); i++) {
            if (temp != string.charAt(i) || i == string.length() - 1) {
                ans += temp;
                if (n > 1) ans += "*" + n;
                n = 1;
                temp = string.charAt(i);
            } else {
                n++;
            }
        }
        return ans;
    }


    /**
     * 8 задание:
     * @return true если гласные в последнем слова предложений одинаковые и идут в одинаковом порядке
     * */
    /*проверяем предпоследню букву в первом и втором предложении(с учетом точки) и сравниваем их*/
//    public static boolean doesRhyme(String string1, String string2) {
//        char t1 = string1.charAt(string1.length() - 1), t2 = string2.charAt(string2.length() - 1);
//        if (!Character.isAlphabetic(string1.charAt(string1.length() - 1)))
//            t1 = string1.charAt(string1.length() - 2);
//        if (!Character.isAlphabetic(string2.charAt(string2.length() - 1)))
//            t2 = string2.charAt(string2.length() - 2);
//        return t1 == t2;
//    }

    public static boolean doesRhyme(String str1, String str2) {
        str1 = str1.substring(str1.lastIndexOf(" ") + 1);
        str2 = str2.substring(str2.lastIndexOf(" ") + 1);
        String let = "aeiouyAEIOUY";
        String res1 = "";
        String res2 = "";
        int i;
        for(i = 0; i < str1.length(); ++i) {
            if (let.indexOf(str1.charAt(i)) != -1) {
                res1 = res1 + str1.charAt(i);
            }
        }
        for(i = 0; i < str2.length(); ++i) {
            if (let.indexOf(str2.charAt(i)) != -1) {
                res2 = res2 + str2.charAt(i);
            }
        }
        if (res1.toLowerCase().equals(res2.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 9 задание:
     * @return true если есть такая цифра, которая ровно 3 раза повторяется в d1 и 2 раза повторяется в d2
     *
    /*вводим числа, и по каждому числу пробегаемся, если какое то число встречается чаще constant то значение true
     * так же и со вторым, если оба boolean в конце == true то возвращем True*/
    public static boolean trouble(long num1, long num2){
        return isValid(num1, 3) && isValid(num2, 2);
    }
    //Функция возвращает true если в числе
    //любая цифра повторяется count раз подряд
    public static boolean isValid(long num, int count){
        long c = 1, //счётчик повторений
                prevD = -1, //предыдущее число в итерации
                digit = 0; //число которое сравниваем
        while(num > 0){
            digit = num % 10;
            if(digit == prevD) ++c;
            else {
                prevD = digit;
                c = 1;
            }
            if(c == count) return true;
            num /= 10;
        }
        return false;
    }



    /**
     * 10 задание:
     * @return количество уникальных символов, встречающихся в открытых книгах
     * */
    //Функция возращает общее кол-во уникальных символов
    //Между всеми парами концов книги.
    public static int countUniqueBooks(String text, char symbol) {
        String uniqChars = "";
        int index = 0;
        int count = 0;
        for (int i = 1; i < text.length() && i != 0; i++) {
            index = text.indexOf(symbol, i);
            for (int j = i; j < index; j++) {
                int q = uniqChars.indexOf(text.charAt(j));
                if (q == -1) {
                    uniqChars += text.charAt(j);
                    count++;
                }
            }
            i = text.indexOf(symbol, index + 1);
        }
        return count;
    }

}