package fr.esisar.tdm;

public class ThreadPiSix extends Thread {
  public int start ;
  public int stop;
  public double resultat;

  public ThreadPiSix (int start, int stop) {
    this.start = start;
    this.stop = stop;
  }

  public void run() {
    for (long k = start; k < stop; k++) {
      resultat += 1d/(k*k);
    }
  }

  public static void main(String[] args) throws Exception {
    ThreadPiSix e1 = new ThreadPiSix(1, 500000000);
    ThreadPiSix e2 = new ThreadPiSix(500000000, 1000000000);
    ThreadPiSix e3 = new ThreadPiSix(1000000000, 1500000000);
    ThreadPiSix e4 = new ThreadPiSix(1500000000, 2000000000);


    long start = System.currentTimeMillis();
    e1.start();
    e2.start();
    e3.start();
    e4.start();
    e1.join();
    e2.join();
    e3.join();
    e4.join();
    double res = e1.resultat + e2.resultat + e3.resultat + e4.resultat;
    long stop = System.currentTimeMillis();

    System.out.println("Résultat : " + res);
    System.out.println("Temps mit : " + String.valueOf(stop - start) + " ms");
  }

}
