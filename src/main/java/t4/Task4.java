package t4;

import java.util.List;
import java.util.Scanner;

public class Task4 {
    public Task4() {
        Scanner scanner = new Scanner(System.in);
        Notepad notepad = new Notepad();

        // Добавление нескольких записей на разные даты
        addNotes(notepad);

        // Вывод всех заметок с указанием даты
        System.out.println("Все заметки:");
        notepad.printAllEntries();

        // Запрос ввода даты для отдельного вывода заметок
        System.out.print("\nВведите дату для отдельного вывода заметок (например, 2023-11-23): ");
        String requestedDate = scanner.nextLine();

        // Вывод заметок для указанной даты
        List<String> entriesOnDate = notepad.getEntries(requestedDate);
        System.out.println("\nЗаметки на " + requestedDate + ":");
        for (String entry : entriesOnDate) {
            System.out.println(entry);
        }
    }

    public void addNotes(Notepad notepad) {
        notepad.addEntry("2023-11-23", "Заметка 1");
        notepad.addEntry("2023-11-23", "Заметка 2");
        notepad.addEntry("2023-11-24", "Заметка 3");
        notepad.addEntry("2023-11-25", "Заметка 4");
        notepad.addEntry("2023-11-25", "Заметка 5");
        notepad.addEntry("2023-11-26", "Заметка 6");
        notepad.addEntry("2023-11-27", "Заметка 7");
        notepad.addEntry("2023-11-28", "Заметка 8");
        notepad.addEntry("2023-11-28", "Заметка 9");
        notepad.addEntry("2023-11-29", "Заметка 10");
    }


    public static void main(String[] args) {
        new Task4();
    }
}
