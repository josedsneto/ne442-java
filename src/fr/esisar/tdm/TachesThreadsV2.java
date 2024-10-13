package fr.esisar.tdm;

public class TachesThreadsV2 extends Thread {
    public int tacheChiffre;
    public int delai = 2000;
    public int iterations;

    public TachesThreadsV2(int tacheChiffre, int iterations) {
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
        TachesThreadsV2 t1 = new TachesThreadsV2(1, 5);
        TachesThreadsV2 t2 = new TachesThreadsV2(2, 10);
        TachesThreadsV2 t3 = new TachesThreadsV2(3, 10);
        TachesThreadsV2 t4 = new TachesThreadsV2(4, 20);
        TachesThreadsV2 t5 = new TachesThreadsV2(5, 15);
        TachesThreadsV2 t6 = new TachesThreadsV2(6, 3);
        TachesThreadsV2 t7 = new TachesThreadsV2(6, 7);

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
