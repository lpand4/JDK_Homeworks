package task1;

public class Calculator {
    /*
    Написать класс Калькулятор (необобщенный),
    который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
    Параметры этих методов – два числа разного типа (но необязательно разного между собой),
    над которыми должна быть произведена операция.
     */

    public <T extends Number, U extends Number> Double sum(T a, U b) {
        return a.doubleValue() + b.doubleValue();
    }

    public <T extends Number, U extends Number> Double multiply(T a, U b) {
        return a.doubleValue() * b.doubleValue();
    }

    public <T extends Number, U extends Number> Double divide(T a, U b) {
        return a.doubleValue() / b.doubleValue();
    }

    public <T extends Number, U extends Number> Double subtract(T a, U b) {
        return a.doubleValue() - b.doubleValue();
    }
}

