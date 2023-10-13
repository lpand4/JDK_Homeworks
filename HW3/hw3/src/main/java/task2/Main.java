package task2;

public class Main {
    /*
    Напишите обобщенный метод compareArrays(),
    который принимает два массива и возвращает true, если они одинаковые,
    и false в противном случае. Массивы могут быть любого типа данных,
    но должны иметь одинаковую длину и содержать элементы одного типа.
     */
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4};
        Integer[] arr2 = new Integer[]{1, 2, 3, 4};
        Integer[] arr3 = new Integer[]{1, 2, 3, 5};
        Double[] arr4 = new Double[]{1.0, 2.0, 3.0, 4.0};
        String[] arr5 = new String[]{"1", "2", "3", "4"};

        System.out.println(compareArrays(arr1, arr2)); //true
        System.out.println(compareArrays(arr1, arr3)); //false
        //System.out.println(compareArrays(arr1,arr4)); //false + ошибка компиляции
        //System.out.println(compareArrays(arr1,arr5)); //Аналогично

        //Если надо чтобы можно было сравнить массивы любых типов, то уберем extends Comparable<T>
    }

    public static <T extends Comparable<T>> boolean compareArrays(T[] firstArr, T[] secondArr) {
        if (firstArr.length == secondArr.length) {
            for (int i = 0; i < firstArr.length; i++) {
                if (!firstArr[i].equals(secondArr[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
