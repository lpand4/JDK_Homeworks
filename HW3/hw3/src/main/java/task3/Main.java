package task3;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(1, "один");
        Pair<String, Integer> pair2 = new Pair<>("один", 1);

        System.out.println(pair1.getFirst());
        System.out.println(pair2.getSecond());

        System.out.println(pair1);
        System.out.println(pair2);
    }
}
