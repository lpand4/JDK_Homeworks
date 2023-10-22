import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private EmployeeDirectory employeeDirectory;

    public Controller(int count) {
        this.employeeDirectory = new EmployeeDirectory(generateList(count));
    }


    public void start() {
        String choise;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                printMenu();
                choise = sc.nextLine();
                switch (choise) {
                    case "1":
                        printEmployees();
                        break;
                    case "2":
                        findByExperience();
                        break;
                    case "3":
                        findByName();
                        break;
                    case "4":
                        findByPersonnelNum();
                        break;
                    case "5":
                        addNewEmployee();
                        break;
                    case "0":
                        System.out.println("Всего доброго");
                        break;
                    default:
                        System.out.println("Введенное значение не подходит.");
                        break;
                }
            } while (!choise.equals("0"));
        } catch (InputMismatchException a) {
            System.out.println("Введено неверное значение!");
        }
    }

    private void printEmployees() {
        employeeDirectory.printEmployeeDirectory();
    }

    private void addNewEmployee() {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        String name;
        int experience;
        System.out.print("Введите имя нового сотрудника: ");
        name = sc.nextLine();
        System.out.print("Введите номер телефона нового сотрудника: ");
        phoneNumber = sc.nextLine();
        System.out.print("Введите стаж нового сотрудника: ");
        try {
            experience = Integer.parseInt(sc.nextLine());
            employeeDirectory.add(new Employee(phoneNumber, name, experience));
        } catch (Exception e) {
            System.out.println("Вы ввели неверное значение!");
        }

    }

    private void findByPersonnelNum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите искомый табельный номер: ");
        int personnelNum;
        try {
            personnelNum = Integer.parseInt(sc.nextLine());
            employeeDirectory.findByPersonnelNumber(personnelNum);
        } catch (Exception e) {
            System.out.println("Вы ввели неверное значение!");
        }
    }

    private void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите искомое имя: ");
        String name = sc.nextLine();
        employeeDirectory.findByName(name);
    }

    private void findByExperience() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите необходимый стаж: ");
        String experience;
        int exp;
        experience = sc.next();
        try {
            exp = Integer.parseInt(experience);
            employeeDirectory.findByExperience(exp);
        } catch (Exception e) {
            System.out.println("Вы ввели неверное значение!");
        }
    }

    private void printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n1. Показать список сотрудников\n")
                .append("2. Показать сотрудника(ов) по стажу\n")
                .append("3. Показать номер телефона сотрудника по имени\n")
                .append("4. Показать сотрудника по табельному номеру\n")
                .append("5. Добавление нового сотрудника в справочник\n")
                .append("0. Выход\n");
        System.out.println(sb);
    }

    private ArrayList<Employee> generateList(int count) {
        String[] names = new String[]{"Ксения", "Арина", "Дарья", "Злата", "Владимир", "Николай",
                "Дмитрий", "Руслан", "Марина", "Давид", "Яна", "Полина", "Макар", "Роман", "Марианна",
                "Серафима", "Виктория", "София", "Юлиана", "Милана", "Алексей", "Анна", "Анастасия",
                "Богдан", "Никита", "Есения", "Михаил", "Даниил", "Владислав", "Елизавета", "Софья",
                "Артём", "Марк", "Надежда", "Валерия", "Ярослав", "Оливия", "Кристина", "Евгения",
                "Артемий", "Андрей", "Мария", "Илья", "Тимур", "Вера", "Всеволод", "Дарина", "Теона",
                "Луиза", "Ирина"};
        Random random = new Random();
        ArrayList<Employee> employeeList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            employeeList.add(new Employee("79" + String.valueOf(random.nextInt(1000, 9999))
                    + String.valueOf(random.nextInt(10000, 99999))
                    , names[random.nextInt(names.length)],
                    random.nextInt(1, 30)));
        }
        return employeeList;
    }

}
