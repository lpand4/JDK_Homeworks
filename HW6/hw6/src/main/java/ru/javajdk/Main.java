package ru.javajdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса
Монти Холла (Парадокс Монти Холла — Википедия ) и
наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).

Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям
 */
public class Main {
    static Random rnd = new Random();
    static int COUNT_DOORS = 3;
    static String AUTO = "AUTO";
    static String NOTHING = "NOTHING";
    static String WIN = "WIN";
    static String LOSE = "LOSE";
    static int COUNT_GAMES = 1_000;


    public static void main(String[] args) {
        HashMap<Integer, String> resultWithChanges = new HashMap<>();
        HashMap<Integer, String> resultWithoutChanges = new HashMap<>();
        int counter = 1;
        while (counter <= COUNT_GAMES){
            if(selectionWithChanges(initDoors())){
                resultWithChanges.put(counter,WIN);
            }else{
                resultWithChanges.put(counter,LOSE);
            }
            if (selectionWithoutChanges(initDoors())){
                resultWithoutChanges.put(counter,WIN);
            }else {
                resultWithoutChanges.put(counter,LOSE);
            }

            counter++;
        }

        System.out.println("С изменением выбора\n" + printResult(resultWithChanges));
        System.out.println("-----------------------------\t");
        System.out.println("Без изменения выбора\n" + printResult(resultWithoutChanges));


    }








    //Инициируем рандомное заполнение дверей
    static String[] initDoors(){
        String[] doors = new String[COUNT_DOORS];
        int doorWithAuto = rnd.nextInt(3);
        for (int i = 0; i < COUNT_DOORS; i++) {
            if (i == doorWithAuto){
                doors[i] = AUTO;
            }else {
                doors[i] = NOTHING;
            }
        }
        return doors;
    }


    // Выбор двери, первый и окончательный(Если человек не меняет выбор)
    static boolean selectionWithoutChanges(String[] doors){
        String selection = doors[rnd.nextInt(COUNT_DOORS)];
        return selection.equals(AUTO);
    }

    // Выбор с последующим изменением двери
    static boolean selectionWithChanges(String[] doors){
        int numOfSelection = rnd.nextInt(COUNT_DOORS);
        String selection = doors[numOfSelection];
        //Если выбор изначально был на машине,
        //то перевыбрав он в любом случае попадает на пустую дверь и наоборот
        return !selection.equals(AUTO);
    }

    static String printResult(HashMap<Integer, String> hashMap){
        int winCount = 0;
        for (Map.Entry<Integer,String> e: hashMap.entrySet()) {
            if (e.getValue().equals(WIN)){
                winCount++;
            }
        }
        double probability = (double) winCount/COUNT_GAMES*100;

        StringBuilder sb = new StringBuilder();
        sb.append("Количество выигрышей составило: ")
                .append(winCount)
                .append("\nВероятность выигрыша равна:")
                .append(probability)
                .append("%");

        return sb.toString();
    }


}