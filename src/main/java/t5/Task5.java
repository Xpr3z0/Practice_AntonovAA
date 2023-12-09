package t5;

import java.util.Scanner;

public class Task5 {
    private static final String TEMPLATE_TEXT =
            "В начале июля, в чрезвычайно жаркое время, под вечер, " +
                    "один молодой человек вышел из своей каморки, которую нанимал " +
                    "от жильцов в С-м переулке, на улицу и медленно, как бы в нерешимости, " +
                    "кружил каменным полом.";

    public Task5() {
        Scanner scanner = new Scanner(System.in);

        // Выбор ввода текста: пользователь или шаблон
        System.out.println("Выберите способ ввода текста:");
        System.out.println("1. Ввести текст самостоятельно");
        System.out.println("2. Использовать шаблон");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Считываем лишний перевод строки после ввода числа

        String text;

        if (choice == 1) {
            // Ввод текста пользователем
            System.out.println("Введите текст на русском языке:");
            text = scanner.nextLine();
        } else if (choice == 2) {
            // Использование шаблона
            text = TEMPLATE_TEXT;
            System.out.println("\nИспользуемый шаблон текста:\n");
            System.out.println(text);
        } else {
            System.out.println("Некорректный выбор. Завершение программы.");
            scanner.close();
            return;
        }

        // Ввод номера буквы для замены
        System.out.print("\nВведите номер буквы для замены (k): ");
        int k = scanner.nextInt();

        // Ввод символа для замены
        System.out.print("\nВведите символ для замены: ");
        char replacementChar = scanner.next().charAt(0);

        // Разбиваем текст на слова
        String[] words = text.split("\\s+");

        // Заменяем буквы в каждом слове
        for (int i = 0; i < words.length; i++) {
            if (k <= words[i].length()) {
                // Получаем k-ю букву в слове (индексация с 0)
                int charIndex = k - 1;

                // Заменяем букву
                char[] wordChars = words[i].toCharArray();
                wordChars[charIndex] = replacementChar;

                // Обновляем слово в массиве слов
                words[i] = new String(wordChars);
            }
        }

        // Формируем измененный текст
        String modifiedText = String.join(" ", words);

        // Выводим измененный текст
        System.out.println("\nИзмененный текст:");
        System.out.println("\n" + modifiedText);

//        scanner.close();
    }

    public static void main(String[] args) {
        new Task5();
    }
}

