package fr.esisar.tdm;

public class PiSix {
  public double resultat;

  public static void main(String[] args) {
    PiSix psix = new PiSix();

    double K = 2000000000;

    long start = System.currentTimeMillis();
    double res = psix.calcul(K);
    long stop = System.currentTimeMillis();

    System.out.println("Résultat : " + res);
    System.out.println("Temps mit : " + String.valueOf(stop - start) + " ms");
  }

  public double calcul(double K) {
    double result = 0;
    for (long k = 1; k < K; k++) {
      result += 1d/(k*k);
    }
    return result;
  }
}
