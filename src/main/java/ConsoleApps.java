import t1.Task1;
import t2.Task2;
import t3.Task3;
import t4.Task4;
import t5.Task5;
import t6.Task6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleApps {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================");
            System.out.print("\nВведите номер задания или \"0\" для выхода из программы: ");

            int choice = 0;
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Некорректный ввод");
                break;
            }

            if (choice >= 1 && choice <= 6) {
                System.out.println("\n==============================\nЗадание " + choice + "\n");
            }

            switch (choice) {
                case 1:
                    new Task1();
                    break;
                case 2:
                    new Task2();
                    break;
                case 3:
                    new Task3();
                    break;
                case 4:
                    new Task4();
                    break;
                case 5:
                    new Task5();
                    break;
                case 6:
                    new Task6();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Такого задания нет");
            }
        }
    }
}
