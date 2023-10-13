package task1;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println(calc.sum(3.2, 21));
        System.out.println(calc.multiply(3.2, 4));
        System.out.println(calc.divide(23, 2.2f));
        System.out.println(calc.subtract(3.2, 21));
    }
}
