public class Table extends Thread {

    private Philosopher[] philosophers;
    private AdjacentForks[] forks;


    public Table() {
        philosophers = new Philosopher[5];
        forks = new AdjacentForks[5];
        //сделали 5 вилок!
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new AdjacentForks();
        }
        // Раздаем вилки
        for (int i = 0; i < philosophers.length; i++) {
            AdjacentForks leftFork = forks[i];
            AdjacentForks rightFork = forks[(i + 1) % forks.length];
            String name = philosopherName(i);
            philosophers[i] = new Philosopher(leftFork, rightFork, name);
        }
    }

    @Override
    public void run() {
        initTable();
    }


    private void initTable(){
        for(Philosopher p: philosophers){
            p.start();
        }
    }
    private String philosopherName(int i) {
        String[] names = new String[]{"Аристотель", "Иммануил Кант", "Платон", "Конфуций", "Сократ"};
        return names[i];
    }

}
