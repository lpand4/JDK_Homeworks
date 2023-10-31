public class Table extends Thread {

    private Philosopher[] philosophers;
    private Object[] forks;


    public Table() {
        philosophers = new Philosopher[5];
        forks = new Object[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            String name = philosopherName(i);
            if (i != philosophers.length - 1){
                philosophers[i] = new Philosopher(leftFork, rightFork, name);
            }else {
                philosophers[i] = new Philosopher(rightFork, leftFork, name);
            }
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
