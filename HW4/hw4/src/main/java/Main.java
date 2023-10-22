import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
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
    public static void main(String[] args) {
        Controller c = new Controller(30);
        c.start();
    }

}
