import java.util.Random;

public class Philosopher extends Thread{

    private Object leftFork,  rightFork;
    private boolean hungry;
    private String name;
    private Random rnd;
    private int countOfMeals;



    public Philosopher(Object leftFork, Object rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        rnd = new Random();
        countOfMeals = 3;
    }

    private boolean isHungry() {
        return hungry;
    }

    private void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    @Override
    public void run() {
        try {
            while (!isHungry()){
                think();
                synchronized (leftFork){
                    takeLeftFork();
                    synchronized (rightFork){
                        takeRightFork();
                        eat();
                    }
                    finishEat();
                }
                countOfMeals--;
                if (countOfMeals == 0){
                    setHungry(true);
                }
            }
            System.out.println("\u001B[31m" + name + ": is full." + "\u001B[0m");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    private void eat() throws InterruptedException {
        System.out.println("\u001B[32m" + name + ": eating. " + "\u001B[0m");
        sleep(rnd.nextInt(10, 25) * 100L);
    }

    private void think() throws InterruptedException {
        System.out.println("\u001B[34m" + name + ": thinking. " + "\u001B[0m");
        sleep(rnd.nextInt(10, 25) * 100L);
    }
    private void takeLeftFork() throws InterruptedException {
        System.out.println("\u001B[37m" + name + ": took the left fork. " + "\u001B[0m");
        sleep(rnd.nextInt(2, 5) * 100L);
    }
    private void takeRightFork() throws InterruptedException {
        System.out.println("\u001B[37m" + name + ": took the right fork. " + "\u001B[0m");
        sleep(rnd.nextInt(2, 5) * 100L);
    }
    private void finishEat() throws InterruptedException{
        System.out.println("\u001B[37m" + name + ": Finished eating and put both forks down. " + "\u001B[0m");
        sleep(rnd.nextInt(2, 5) * 100L);
    }
}
