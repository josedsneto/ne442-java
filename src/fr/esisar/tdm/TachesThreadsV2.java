package fr.esisar.tdm;

public class TachesThreadsV2 extends Thread {
    public String tacheChiffre;
    public int delai = 2000;
    public int iterations;

    public TachesThreadsV2(String tacheChiffre, int iterations) {
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
        TachesThreadsV2 t1 = new TachesThreadsV2("cebola", 5);
        TachesThreadsV2 t2 = new TachesThreadsV2("calango", 9);
        TachesThreadsV2 t3 = new TachesThreadsV2("batman", 3);
        TachesThreadsV2 t4 = new TachesThreadsV2("carro", 8);
        TachesThreadsV2 t5 = new TachesThreadsV2("tralhoto", 10);
        TachesThreadsV2 t6 = new TachesThreadsV2("playstation", 8);
        TachesThreadsV2 t7 = new TachesThreadsV2("boa vista", 7);

        t1.start();
        t1.join();

        t2.start();
        t3.start();
        t4.start();

        if (t2.isAlive() || t3.isAlive()) {
            t2.join();
            t3.join();
        } else if (!t2.isAlive() && !t3.isAlive()) {
            t5.start();
        }

        if (t4.isAlive()) {
            t4.join();
        } else {
            t6.start();
        }

        if (t5.isAlive() || t6.isAlive()) {
            t5.join();
            t6.join();
        } else if (!t5.isAlive() && !t6.isAlive()) {
            t7.start();
            t7.join();
        }
    }

}
