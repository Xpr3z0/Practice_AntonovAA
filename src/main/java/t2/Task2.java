package t2;

import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    ArrayList<Student> students;
    public Task2() {
        Scanner scan = new Scanner(System.in);
        students = new ArrayList<>();
        addStudents();

        System.out.print("a) Введите факультет: ");
        String selectedFaculty = scan.nextLine();

        for (Student student : students) {
            if (student.getFaculty().equals(selectedFaculty)) {
                System.out.println(student.getStudentInfo());
            }
        }


        System.out.print("\nb) Введите курс: ");
        int selectedCourse = scan.nextInt();

        for (Student student : students) {
            if (student.getCourse() == selectedCourse) {
                System.out.println(student.getStudentInfo());
            }
        }


        System.out.print("\nc) Введите год рождения: ");
        int selectedBirthAge = scan.nextInt();

        for (Student student : students) {
            if (Integer.parseInt(student.getBirthDate().substring(student.getBirthDate().length() - 4)) > selectedBirthAge) {
                System.out.println(student.getStudentInfo());
            }
        }

        System.out.print("\nd) Введите группу: ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scan.nextLine();
        String selectedGroup = scan.nextLine();

        for (Student student : students) {
            if (student.getGroup().equals(selectedGroup)) {
                System.out.println(student.getStudentInfo());
            }
        }
    }

    private void addStudents() {
        students.add(new Student(1, "Селезнев", "Никита", "Германович", "10.10.2004", "Западная ул., д. 20 кв.116", "+78002874068", "Информационные технологии", 4, "ИТ 20-1"));
        students.add(new Student(2, "Иванов", "Александр", "Игоревич", "05.03.2003", "Центральная ул., д. 15 кв.45", "+78005553535", "Медицина", 3, "М-19-2"));
        students.add(new Student(3, "Петров", "Екатерина", "Александровна", "20.08.2002", "Северная ул., д. 7 кв.21", "+78001234567", "Экономика", 2, "Э-18-3"));
        students.add(new Student(4, "Сидоров", "Ольга", "Дмитриевна", "15.11.2001", "Восточная ул., д. 12 кв.32", "+78001112233", "Инженерия", 1, "И-17-4"));
        students.add(new Student(5, "Морозов", "Иван", "Петрович", "03.06.2000", "Южная ул., д. 5 кв.10", "+78009998877", "Информационные технологии", 4, "ИТ 20-1"));
        students.add(new Student(6, "Васнецов", "Мария", "Сергеевна", "18.09.2000", "Северная ул., д. 10 кв.18", "+78001010101", "Медицина", 3, "М-19-2"));
        students.add(new Student(7, "Краснов", "Александр", "Николаевич", "22.02.2001", "Центральная ул., д. 25 кв.40", "+78007654321", "Экономика", 2, "Э-18-3"));
        students.add(new Student(8, "Лебедев", "Татьяна", "Александровна", "14.07.2002", "Южная ул., д. 8 кв.27", "+78009090909", "Инженерия", 1, "И-17-4"));
        students.add(new Student(9, "Соколов", "Дмитрий", "Сергеевич", "30.04.2003", "Западная ул., д. 18 кв.25", "+78005556666", "Информационные технологии", 4, "ИТ 20-1"));
        students.add(new Student(10, "Игнатьев", "Анна", "Владимировна", "11.12.2004", "Северная ул., д. 6 кв.14", "+78001113344", "Медицина", 3, "М-19-2"));
        students.add(new Student(11, "Казанцев", "Игорь", "Андреевич", "25.05.2000", "Центральная ул., д. 22 кв.38", "+78009998877", "Экономика", 2, "Э-18-3"));
        students.add(new Student(12, "Антонов", "Елена", "Петровна", "09.08.2001", "Южная ул., д. 9 кв.20", "+78007651234", "Инженерия", 1, "И-17-4"));
        students.add(new Student(13, "Никитин", "Максим", "Иванович", "17.03.2002", "Восточная ул., д. 14 кв.30", "+78005550123", "Информационные технологии", 4, "ИТ 20-1"));
        students.add(new Student(14, "Поляков", "Алиса", "Сергеевна", "02.06.2003", "Центральная ул., д. 20 кв.36", "+78001231234", "Медицина", 3, "М-19-2"));
        students.add(new Student(15, "Григорьев", "Станислав", "Александрович", "28.11.2000", "Южная ул., д. 11 кв.22", "+78007778888", "Экономика", 2, "Э-18-3"));
    }

    public static void main(String[] args) {
        new Task2();
    }
}
