import java.security.SecureRandom;

public class Employee {
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

    private static int COUNTER = 0;

    private int personnelNumber;
    private String phoneNumber;
    private String name;
    private int experience;

    public Employee(String phoneNumber, String name, int experience) {
        this.personnelNumber = COUNTER++;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "|ID_" + personnelNumber +
                " " + name +
                " +" + phoneNumber +
                " experience: " + experience + "|";
    }
}
