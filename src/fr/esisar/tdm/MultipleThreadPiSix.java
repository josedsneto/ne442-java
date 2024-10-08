package fr.esisar.tdm;

public class MultipleThreadPiSix extends Thread {
  public int start;
  public int stop;
  public double resultat;

  public MultipleThreadPiSix(int start, int stop) {
    this.start = start;
    this.stop = stop;
  }

  public void run() {
    for (long k = start; k < stop; k++) {
      resultat += 1d / (k * k);
    }
  }

  public static void main(String[] args) throws Exception {
    int threads = 1024;
    int optperthread = Math.divideExact(2000000000, threads);

    MultipleThreadPiSix[] e = new MultipleThreadPiSix[threads];

    for (int i = 0; i < threads; i++) {
      if (i == 0) {
        e[i] = new MultipleThreadPiSix(1, optperthread);
      } else {
        e[i] = new MultipleThreadPiSix(optperthread * i, optperthread * (i + 1));
      }
    }

    long start = System.currentTimeMillis();
    for (int i = 0; i < threads; i++) {
      e[i].start();
    }
    for (int i = 0; i < threads; i++) {
      e[i].join();
    }
    long stop = System.currentTimeMillis();

    double res = 0;
    for (int i = 0; i < threads; i++) {
      res += e[i].resultat;
    }

    System.out.println("Résultat : " + res);
    System.out.println("Temps mit : " + String.valueOf(stop - start) + " ms");
  }

}
