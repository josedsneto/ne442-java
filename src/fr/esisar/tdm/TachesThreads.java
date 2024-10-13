package fr.esisar.tdm;

public class TachesThreads extends Thread {
    public int tacheChiffre;
    public int delai = 2000;
    public int iterations;

    public TachesThreads(int tacheChiffre, int iterations) {
        this.tacheChiffre = tacheChiffre;
        this.iterations = iterations;
    }

    public void run() {
        for (int i = 0; i < this.iterations; i++) {
            System.out.println("Je suis tache " + this.tacheChiffre);
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TachesThreads t1 = new TachesThreads(1, 5);
        TachesThreads t2 = new TachesThreads(2, 10);
        TachesThreads t3 = new TachesThreads(3, 10);
        TachesThreads t4 = new TachesThreads(4, 20);
        TachesThreads t5 = new TachesThreads(5, 15);
        TachesThreads t6 = new TachesThreads(6, 3);

        t1.start();
        t1.join();

        t2.start();
        t3.start();
        t4.start();
        t2.join();
        t3.join();

        t5.start();
        t5.join();
        t4.join();

        t6.start();
    }

}
