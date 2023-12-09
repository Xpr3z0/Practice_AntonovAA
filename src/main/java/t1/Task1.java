package t1;

import java.util.Scanner;

public class Task1 {
    public Task1() {
        Scanner scan = new Scanner(System.in);

        double k, n, m;

        System.out.print("Введите k: ");
        k = scan.nextDouble();

        System.out.print("Введите n (минимальное значение интервала): ");
        n = scan.nextDouble();

        System.out.print("Введите m (максимальное значение интервала): ");
        m = scan.nextDouble();

        boolean belongs = false;

        if (k > n && k <= m) {
            System.out.println(k + " принадлежит интервалу (" + n + ", " + m + "]");
            belongs = true;
        }
        if (k >= n && k < m) {
            System.out.println(k + " принадлежит интервалу [" + n + ", " + m + ")");
            belongs = true;
        }
        if (k > n && k < m) {
            System.out.println(k + " принадлежит интервалу (" + n + ", " + m + ")");
            belongs = true;
        }
        if (k >= n && k <= m) {
            System.out.println(k + " принадлежит интервалу [" + n + ", " + m + "]");
            belongs = true;
        }
        if (!belongs) {
            System.out.println(k + " не принадлежит ни одному из интервалов");
        }
    }

    public static void main(String[] args) {
        new Task1();
    }
}
