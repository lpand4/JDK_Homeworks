import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDirectory {
        /*
Создать справочник сотрудников
    Необходимо:
    Создать класс справочник сотрудников,
    который содержит внутри коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
            Табельный номер
            Номер телефона
            Имя
            Стаж
    Добавить метод, который ищет сотрудника по стажу (может быть список)

    Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)

    Добавить метод, который ищет сотрудника по табельному номеру

    Добавить метод добавление нового сотрудника в справочник
     */

    private List<Employee> employeeList;

    public EmployeeDirectory(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void add(Employee employee) {
        if (employee != null) {
            employeeList.add(employee);
        }
    }

    //Добавить метод, который ищет сотрудника по стажу (может быть список)
    public void findByExperience(int experience) {
        List<Employee> resultEmployees = new ArrayList<>();
        for (Employee e : employeeList) {
            if (e.getExperience() == experience) {
                resultEmployees.add(e);
            }
        }
        if (resultEmployees.isEmpty()) {
            System.out.println("Нет сотрудников с таким стажем работы" + experience);
        } else {
            System.out.println("Сотрудники со стажем работы " + experience + ":");
            printList(resultEmployees);
        }
    }

    //  Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
    public void findByName(String name) {
        List<Employee> resultEmployees = new ArrayList<>();
        for (Employee e : employeeList) {
            if (e.getName().equals(name)) {
                resultEmployees.add(e);
            }
        }
        if (resultEmployees.isEmpty()) {
            System.out.println("Нет сотрудников с таким именем");
        } else {
            System.out.println("Номера сотрудника(ов) с именем " + name + ":");
            printPhones(resultEmployees);
        }
    }

    //Добавить метод, который ищет сотрудника по табельному номеру
    public void findByPersonnelNumber(int personnelNum) {
        Employee resEmployee = null;
        for (Employee e : employeeList) {
            if (e.getPersonnelNumber() == personnelNum) {
                resEmployee = e;
            }
        }
        if (resEmployee == null) {
            System.out.println("Нет сотрудников с таким табельным номером");
        } else {
            System.out.println("Сотрудник с табельным номером " + personnelNum + ":" + resEmployee);

        }
    }

    public void printEmployeeDirectory() {
        printList(employeeList);
    }

    private void printList(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void printPhones(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.printf("|ID_%d +%s|\n", e.getPersonnelNumber(), e.getPhoneNumber());
        }
    }

}
